package dqwapi.infra.repository;

import com.google.common.base.CaseFormat;
import dqwapi.domain.entity.Result;
import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.BigQueryTableType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.GradeType;
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
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

@Profile("dwh-gcp-bigquery")
@Slf4j
@RequiredArgsConstructor
@Repository("big-query-repository")
public class BigQueryKokoroCombinationRepository implements IKokoroCombinationRepository {

  @Value("${gcp.big-query.table-type}")
  private BigQueryTableType tableType;

  private final IBigQueryConnector bigQueryConnector;
  private String query = "";
  private String queryTemplate = "";

  @PostConstruct
  void init() {
    final String sql = "gcp-big-query.txt";
    final Resource resource = new ClassPathResource(sql);
    final String sqlTemplate = "gcp-big-query-template.txt";
    final Resource resourceTemplate = new ClassPathResource(sqlTemplate);
    try {
      query = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
      queryTemplate = IOUtils.toString(resourceTemplate.getInputStream(), StandardCharsets.UTF_8);
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
      final Map<Integer, List<GradeType>> exclusions,
      final int limit
  ) {
    final List<String> exclusionGrades = new ArrayList<>();
    for (final Map.Entry<Integer, List<GradeType>> entry : exclusions.entrySet()) {
      final Integer key = entry.getKey();
      for (GradeType gradeType : entry.getValue()) {
        exclusionGrades.add(key + "_" + gradeType.name());
      }
    }
    if (exclusionGrades.size() == 0) {
      exclusionGrades.add("");
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
    final String joinedExclusions = exclusionGrades.stream()
        .collect(Collectors.joining("','", "'", "'"));
    // TODO: improve
    final RaceType replacedRaceType;
    if (raceType.equals(RaceType.NONE)) {
      replacedRaceType = RaceType.ANIMAL;
    } else {
      replacedRaceType = raceType;
    }

    final String replacedQuery;
    if (tableType.equals(BigQueryTableType.CROSS)) {
      final List<String> parameters = new ArrayList<>();
      switch (attackType) {
        case SLASH, HIT:
          parameters.add("k0.op");
          parameters.add("k1.op");
          parameters.add("k2.op");
          parameters.add("k3.op");
          break;
        case SPELL:
          parameters.add("k0.os");
          parameters.add("k1.os");
          parameters.add("k2.os");
          parameters.add("k3.os");
          break;
        case PHYSICS_SPELL_SLASH, PHYSICS_SPELL_HIT:
          parameters.add("k0.op + k0.os");
          parameters.add("k1.op + k1.os");
          parameters.add("k2.op + k2.os");
          parameters.add("k3.op + k3.os");
          break;
        case BREATH:
          parameters.add("k0.op + k0.dx");
          parameters.add("k1.op + k1.dx");
          parameters.add("k2.op + k2.dx");
          parameters.add("k3.op + k3.dx");
          break;
        default:
          throw new IllegalArgumentException("Unknown AttackType: " + attackType);
      }
      final AttackType replacedAttackType;
      if (attackType.equals(AttackType.PHYSICS_SPELL_SLASH)) {
        replacedAttackType = AttackType.SLASH;
      } else if (attackType.equals(AttackType.PHYSICS_SPELL_HIT)) {
        replacedAttackType = AttackType.HIT;
      } else {
        replacedAttackType = attackType;
      }
      if (raceType.equals(RaceType.NONE) || raceType.equals(RaceType.SECRET)) {
        column = (jobType.name()
            + "_" + attributeType.name()
            + "_" + replacedAttackType.name()
            + "_damage").toLowerCase();
      } else {
        column = (jobType.name()
            + "_" + attributeType.name()
            + "_" + replacedAttackType.name()
            + "_" + replacedRaceType.name()
            + "_damage").toLowerCase();
      }
      query = query.replace("$pattern", pattern)
          .replace("$column", column)
          .replace("$cost", Integer.toString(cost))
          .replace("$joinedNonBrides", joinedNonBrides)
          .replace("$joinedExclusions", joinedExclusions)
          .replace("$limit", Integer.toString(limit));
      replacedQuery = queryTemplate.replace("{{JOB}}", jobType.name())
          .replace("{{job}}", jobType.name().toLowerCase())
          .replace("{{param0}}", parameters.get(0))
          .replace("{{param1}}", parameters.get(1))
          .replace("{{param2}}", parameters.get(2))
          .replace("{{param3}}", parameters.get(3))
          .replace("{{attack}}", replacedAttackType.name().toLowerCase())
          .replace("{{attribute}}", attributeType.name().toLowerCase())
          .replace("{{Attribute}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, attributeType.name()))
          .replace("{{race}}", replacedRaceType.name().toLowerCase())
          .replace("{{Race}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, replacedRaceType.name()))
          .replace("{{pattern}}", pattern)
          .replace("{{cost}}", Integer.toString(cost))
          .replace("{{joinedNonBrides}}", joinedNonBrides)
          .replace("{{joinedExclusions}}", joinedExclusions)
          .replace("{{column}}", column)
          .replace("{{limit}}", Integer.toString(limit));
    } else if (tableType.equals(BigQueryTableType.ONE)) {
      replacedQuery = "";
      if (raceType.equals(RaceType.NONE)) {
        column = (jobType.name()
            + "_" + attributeType.name()
            + "_" + attackType.name()
            + "_damage").toLowerCase();
        query = "SELECT k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, " + pattern
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
            + " AND CONCAT(k0id, '_', k0grade) NOT IN (" + joinedExclusions + ")"
            + " AND CONCAT(k1id, '_', k1grade) NOT IN (" + joinedExclusions + ")"
            + " AND CONCAT(k2id, '_', k2grade) NOT IN (" + joinedExclusions + ")"
            + " AND CONCAT(k3id, '_', k3grade) NOT IN (" + joinedExclusions + ")"
            + " ORDER BY " + column + " DESC"
            + " LIMIT " + limit;
      } else {
        column = (jobType.name()
            + "_" + attributeType.name()
            + "_" + attackType.name()
            + "_" + raceType.name()
            + "_damage").toLowerCase();
        query = "SELECT k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, " + pattern
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
            + " AND CONCAT(k0id, '_', k0grade) NOT IN (" + joinedExclusions + ")"
            + " AND CONCAT(k1id, '_', k1grade) NOT IN (" + joinedExclusions + ")"
            + " AND CONCAT(k2id, '_', k2grade) NOT IN (" + joinedExclusions + ")"
            + " AND CONCAT(k3id, '_', k3grade) NOT IN (" + joinedExclusions + ")"
            + " ORDER BY " + column + " DESC"
            + " LIMIT " + limit;
      }
    } else {
      throw new IllegalArgumentException("Unknown BigQuery table type: " + tableType);
    }
    log.info("{}", replacedQuery);

    final List<Result> results = new ArrayList<>();
    bigQueryConnector.query(replacedQuery).iterateAll().forEach(row -> {
      final Result result = new Result();
      result.setK0id(row.get(0).getNumericValue().intValue());
      result.setK0grade(GradeType.valueOf(row.get(1).getStringValue()));
      result.setK1id(row.get(2).getNumericValue().intValue());
      result.setK1grade(GradeType.valueOf(row.get(3).getStringValue()));
      result.setK2id(row.get(4).getNumericValue().intValue());
      result.setK2grade(GradeType.valueOf(row.get(5).getStringValue()));
      result.setK3id(row.get(6).getNumericValue().intValue());
      result.setK3grade(GradeType.valueOf(row.get(7).getStringValue()));
      result.setPattern(row.get(8).getStringValue());
      results.add(result);
    });
    log.info(results.toString());
    return results;
  }
}
