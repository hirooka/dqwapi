package dqwapi.infra.repository;

import dqwapi.domain.entity.Result;
import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.BigQueryTableType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.RankType;
import dqwapi.domain.repository.IKokoroCombinationRepository;
import dqwapi.infra.gcp.bigquery.IBigQueryConnector;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository("big-query-repository")
public class BigQueryKokoroCombinationRepository implements IKokoroCombinationRepository {

  @Value("${gcp.big-query.table-type}")
  private BigQueryTableType tableType;

  private final IBigQueryConnector bigQueryConnector;
  private String query = "";

  @PostConstruct
  void init() {
    final String sql = "gcp-big-query.txt";
    final Resource resource = new ClassPathResource(sql);
    try {
      query = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new IllegalStateException("Failed to read " + sql);
    }
  }

  @Override
  public List<Result> get(
      final JobType jobType,
      final AttackType attackType,
      final AttributeType attributeType,
      final RaceType raceType,
      final int cost,
      final List<Integer> nonBrides,
      final Map<Integer, List<RankType>> exclusions,
      final int limit
  ) {
    final List<String> exclusionRanks = new ArrayList<>();
    for (final Map.Entry<Integer, List<RankType>> entry : exclusions.entrySet()) {
      final Integer key = entry.getKey();
      for (RankType rankType : entry.getValue()) {
        exclusionRanks.add(key + "_" + rankType.name());
      }
    }
    if (exclusionRanks.size() == 0) {
      exclusionRanks.add("");
    }

    final String column;
    final String parameter = switch (attackType) {
      case SLASH, HIT -> "op";
      case SPELL -> "os";
      case PHYSICS_SPELL_SLASH, PHYSICS_SPELL_HIT -> "opos";
      case BREATH -> "opdx";
      default -> throw new IllegalArgumentException("Unknown AttackType: " + attackType);
    };
    final String pattern = "max_" + jobType.name().toLowerCase() + "_" + parameter + "_pattern";
    final String joinedNonBrides = nonBrides.stream()
        .map(integer -> Integer.toString(integer)).collect(Collectors.joining(","));
    final String joinedExclusions = exclusionRanks.stream()
        .collect(Collectors.joining("','", "'", "'"));

    if (tableType.equals(BigQueryTableType.CROSS)) {
      column = (jobType.name()
          + "_" + attributeType.name()
          + "_" + attackType.name()
          + "_damage").toLowerCase();
      query = query.replace("$pattern", pattern)
          .replace("$column", column)
          .replace("$cost", Integer.toString(cost))
          .replace("$joinedNonBrides", joinedNonBrides)
          .replace("$joinedExclusions", joinedExclusions)
          .replace("$limit", Integer.toString(limit));
    } else if (tableType.equals(BigQueryTableType.ONE)) {
      if (raceType.equals(RaceType.NONE)) {
        column = (jobType.name()
            + "_" + attributeType.name()
            + "_" + attackType.name()
            + "_damage").toLowerCase();
        query = "SELECT k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, " + pattern
            + " FROM `"
            + "$projectId"
            + "."
            + "$datasetName"
            + "."
            + "$tableName"
            + "`"
            + " WHERE total_cost < " + cost
            + " AND k0id NOT IN (" + joinedNonBrides + ")"
            + " AND k1id NOT IN (" + joinedNonBrides + ")"
            + " AND k2id NOT IN (" + joinedNonBrides + ")"
            + " AND k3id NOT IN (" + joinedNonBrides + ")"
            + " AND CONCAT(k0id, '_', k0rank) NOT IN (" + joinedExclusions + ")"
            + " AND CONCAT(k1id, '_', k1rank) NOT IN (" + joinedExclusions + ")"
            + " AND CONCAT(k2id, '_', k2rank) NOT IN (" + joinedExclusions + ")"
            + " AND CONCAT(k3id, '_', k3rank) NOT IN (" + joinedExclusions + ")"
            + " ORDER BY " + column + " DESC"
            + " LIMIT " + limit;
      } else {
        column = (jobType.name()
            + "_" + attributeType.name()
            + "_" + attackType.name()
            + "_" + raceType.name()
            + "_damage").toLowerCase();
        query = "SELECT k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, " + pattern
            + " FROM `"
            + "$projectId"
            + "."
            + "$datasetName"
            + "."
            + "$tableName" + "_" + jobType.name().toLowerCase()
            + "`"
            + " WHERE total_cost < " + cost
            + " AND k0id NOT IN (" + joinedNonBrides + ")"
            + " AND k1id NOT IN (" + joinedNonBrides + ")"
            + " AND k2id NOT IN (" + joinedNonBrides + ")"
            + " AND k3id NOT IN (" + joinedNonBrides + ")"
            + " AND CONCAT(k0id, '_', k0rank) NOT IN (" + joinedExclusions + ")"
            + " AND CONCAT(k1id, '_', k1rank) NOT IN (" + joinedExclusions + ")"
            + " AND CONCAT(k2id, '_', k2rank) NOT IN (" + joinedExclusions + ")"
            + " AND CONCAT(k3id, '_', k3rank) NOT IN (" + joinedExclusions + ")"
            + " ORDER BY " + column + " DESC"
            + " LIMIT " + limit;
      }
    } else {
      throw new IllegalArgumentException("Unknown BigQuery table type: " + tableType);
    }
    log.info("{}", query);

    final List<Result> results = new ArrayList<>();
    bigQueryConnector.query(query).iterateAll().forEach(row -> {
      final Result result = new Result();
      result.setK0id(row.get(0).getNumericValue().intValue());
      result.setK0rank(RankType.valueOf(row.get(1).getStringValue()));
      result.setK1id(row.get(2).getNumericValue().intValue());
      result.setK1rank(RankType.valueOf(row.get(3).getStringValue()));
      result.setK2id(row.get(4).getNumericValue().intValue());
      result.setK2rank(RankType.valueOf(row.get(5).getStringValue()));
      result.setK3id(row.get(6).getNumericValue().intValue());
      result.setK3rank(RankType.valueOf(row.get(7).getStringValue()));
      result.setPattern(row.get(8).getStringValue());
      results.add(result);
    });
    log.info(results.toString());
    return results;
  }
}
