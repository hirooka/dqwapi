package dqwapi.infra.repository;

import dqwapi.domain.entity.Result;
import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.RankType;
import dqwapi.domain.repository.IKokoroCombinationRepository;
import dqwapi.infra.gcp.bigquery.IBigQueryConnector;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository("big-query-repository")
public class BigQueryKokoroCombinationRepository implements IKokoroCombinationRepository {

  private final IBigQueryConnector bigQueryConnector;

  private RankType convertRankType(final int i) {
    switch (i) {
      case 0:
        return RankType.SP;
      case 1:
        return RankType.S;
      case 2:
        return RankType.A;
      case 3:
        return RankType.B;
      case 4:
        return RankType.C;
      case 5:
        return RankType.D;
      default:
        throw new IllegalStateException("Failed to convert " + i + " to RankType.");
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
      final List<String> exclusions,
      final int limit
  ) {
    final String query;
    final String column;
    final String parameter;
    switch (attackType) {
      case SLASH:
      case HIT:
        parameter = "op";
        break;
      case SPELL:
        parameter = "os";
        break;
      case PHYSICS_SPELL_SLASH:
      case PHYSICS_SPELL_HIT:
        parameter = "opos";
        break;
      case BREATH:
        parameter = "opdx";
        break;
      default:
        throw new IllegalArgumentException("Unknown AttackType: " + attackType);
    }
    final String pattern = "max_" + jobType.name().toLowerCase() + "_" + parameter + "_pattern";
    final String joinedNonBrides = nonBrides.stream().map(integer -> Integer.toString(integer)).collect(Collectors.joining(","));
    final String joinedExclusions = exclusions.stream().collect(Collectors.joining("','", "'", "'"));
    if (raceType.equals(RaceType.NONE)) {
      column = (jobType.name() + "_" + attributeType.name() + "_" + attackType.name() + "_damage").toLowerCase();
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
      column = (jobType.name() + "_" + attributeType.name() + "_" + attackType.name() + "_" + raceType.name() + "_damage").toLowerCase();
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
    log.info("{}", query);
    final List<Result> results = new ArrayList<>();
    bigQueryConnector.query(query).iterateAll().forEach(row -> {
      final Result result = new Result();
      result.setK0id(row.get(0).getNumericValue().intValue());
      result.setK0rank(convertRankType(row.get(1).getNumericValue().intValue()));
      result.setK1id(row.get(2).getNumericValue().intValue());
      result.setK1rank(convertRankType(row.get(3).getNumericValue().intValue()));
      result.setK2id(row.get(4).getNumericValue().intValue());
      result.setK2rank(convertRankType(row.get(5).getNumericValue().intValue()));
      result.setK3id(row.get(6).getNumericValue().intValue());
      result.setK3rank(convertRankType(row.get(7).getNumericValue().intValue()));
      result.setPattern(row.get(8).getStringValue());
      results.add(result);
    });
    log.info(results.toString());
    return results;
  }
}
