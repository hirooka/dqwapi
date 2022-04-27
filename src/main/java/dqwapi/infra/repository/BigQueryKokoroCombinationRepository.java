package dqwapi.infra.repository;

import com.google.common.base.CaseFormat;
import dqwapi.domain.entity.Result;
import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.BigQueryTableType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.job.JobParameter;
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
import org.springframework.stereotype.Repository;

@Profile("dwh-gcp-bigquery")
@Slf4j
@RequiredArgsConstructor
@Repository("big-query-repository")
public class BigQueryKokoroCombinationRepository implements IKokoroCombinationRepository {

  @Value("${gcp.project-id}")
  private String projectId;

  @Value("${gcp.big-query.dataset}")
  private String dataset;

  @Value("${gcp.big-query.table}")
  private String table;

  @Value("${gcp.big-query.table-type}")
  private BigQueryTableType tableType;

  private final IBigQueryConnector bigQueryConnector;
  private String queryTemplateDamageAttributeWithoutRace = "";
  private String queryTemplateDamageAttributeWithRace = "";
  private String queryTemplateDamageNoAttributeWithoutRace = "";
  private String queryTemplateDamageNoAttributeWithRace = "";
  private String queryTemplateHealingAll = "";

  @PostConstruct
  void init() {
    try {
      queryTemplateDamageAttributeWithoutRace = IOUtils.toString(new ClassPathResource("gcp-big-query-template-damage-attribute-without-race.txt").getInputStream(), StandardCharsets.UTF_8);
      queryTemplateDamageAttributeWithRace = IOUtils.toString(new ClassPathResource("gcp-big-query-template-damage-attribute-with-race.txt").getInputStream(), StandardCharsets.UTF_8);
      queryTemplateDamageNoAttributeWithoutRace = IOUtils.toString(new ClassPathResource("gcp-big-query-template-damage-no-attribute-without-race.txt").getInputStream(), StandardCharsets.UTF_8);
      queryTemplateDamageNoAttributeWithRace = IOUtils.toString(new ClassPathResource("gcp-big-query-template-damage-no-attribute-with-race.txt").getInputStream(), StandardCharsets.UTF_8);
      queryTemplateHealingAll = IOUtils.toString(new ClassPathResource("gcp-big-query-template-healing-all.txt").getInputStream(), StandardCharsets.UTF_8);
    } catch (IOException ex) {
      throw new IllegalStateException("Failed to read SQL template.", ex);
    }
  }

  @Override
  public List<Result> get(
      final JobType jobType,
      final AttackType attackType,
      final AttributeType attributeType,
      final RaceType raceType,
      final int cost,
      final JobParameter jobParameter,
      final List<Integer> nonBrides,
      final Map<Integer, List<GradeType>> exclusions,
      final Map<Integer, List<GradeType>> inclusions,
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
    final List<String> inclusionGrades = new ArrayList<>();
    for (final Map.Entry<Integer, List<GradeType>> entry : inclusions.entrySet()) {
      final Integer key = entry.getKey();
      for (GradeType gradeType : entry.getValue()) {
        inclusionGrades.add(key + "_" + gradeType.name());
      }
    }

    final int base = switch (attackType) {
      case SLASH, HIT -> jobParameter.getOp();
      case SPELL -> jobParameter.getOs();
      case PHYSICS_SPELL_SLASH, PHYSICS_SPELL_HIT -> jobParameter.getOp() + jobParameter.getOs();
      case BREATH -> jobParameter.getOp() + jobParameter.getDx();
      case HEALING_SPELL, HEALING_SPECIALTY -> jobParameter.getDs();
      default -> throw new IllegalArgumentException("Unknown AttackType: " + attackType);
    };

    final String parameter = switch (attackType) {
      case SLASH, HIT -> "op";
      case SPELL -> "os";
      case PHYSICS_SPELL_SLASH, PHYSICS_SPELL_HIT -> "opos";
      case BREATH -> "opdx";
      case HEALING_SPELL, HEALING_SPECIALTY -> "ds";
      default -> throw new IllegalArgumentException("Unknown AttackType: " + attackType);
    };
    final String pattern = "max_" + jobType.name().toLowerCase() + "_" + parameter + "_pattern";
    final String joinedNonBrides = nonBrides.stream()
        .map(integer -> Integer.toString(integer)).collect(Collectors.joining(","));
    final String joinedExclusions = exclusionGrades.stream()
        .collect(Collectors.joining("','", "'", "'"));
    final StringBuilder replacedInclusions = new StringBuilder();
    if (inclusionGrades.size() > 0) {
      for (final String str : inclusionGrades) {
        replacedInclusions.append("""
            AND (CONCAT(k0.number, '_', k0.grade) IN ('{{inclusion}}') OR CONCAT(k1.number, '_', k1.grade) IN ('{{inclusion}}') OR CONCAT(k2.number, '_', k2.grade) IN ('{{inclusion}}') OR CONCAT(k3.number, '_', k3.grade) IN ('{{inclusion}}')) 
            """.replace("{{inclusion}}", str));
      }
    }

    // To reduce resource that query uses (from)
    final String k0Order = switch (attackType) {
      case SLASH, HIT -> "op";
      case SPELL -> "os";
      case PHYSICS_SPELL_SLASH, PHYSICS_SPELL_HIT -> "op+os";
      case BREATH -> "op+dx";
      case HEALING_SPELL, HEALING_SPECIALTY -> "ds";
      default -> throw new IllegalArgumentException("Unknown AttackType: " + attackType);
    };
    final String k0JoinedExclusions = exclusionGrades.stream()
            .collect(Collectors.joining("','", "'", "'"));
    final StringBuilder k0ReplacedInclusions = new StringBuilder();
    if (inclusionGrades.size() > 0) {
      for (final String str : inclusionGrades) {
        k0ReplacedInclusions.append("""
            OR CONCAT(number, '_', grade) IN ('{{inclusion}}') 
            """.replace("{{inclusion}}", str));
      }
    }
    final int k0Limit = 50; // TODO: fix

    final String k1Order = k0Order;
    final String k1JoinedExclusions = exclusionGrades.stream()
            .collect(Collectors.joining("','", "'", "'"));
    final StringBuilder k1ReplacedInclusions = new StringBuilder();
    if (inclusionGrades.size() > 0) {
      for (final String str : inclusionGrades) {
        k1ReplacedInclusions.append("""
            OR CONCAT(number, '_', grade) IN ('{{inclusion}}') 
            """.replace("{{inclusion}}", str));
      }
    }
    final int k1Limit = 50; // TODO: fix
    // To reduce resource that query uses (to)

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
        case HEALING_SPELL, HEALING_SPECIALTY:
          parameters.add("k0.ds");
          parameters.add("k1.ds");
          parameters.add("k2.ds");
          parameters.add("k3.ds");
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

      final String column;
      if (raceType.equals(RaceType.NONE) || raceType.equals(RaceType.SECRET)) {
        switch (attackType) {
          case SLASH, HIT, SPELL, PHYSICS_SPELL_SLASH, PHYSICS_SPELL_HIT, BREATH:
            column = (jobType.name()
                + "_" + attributeType.name()
                + "_" + replacedAttackType.name()
                + "_damage").toLowerCase();
            break;
          case HEALING_SPELL, HEALING_SPECIALTY:
            column = (jobType.name()
                + "_" + replacedAttackType.name()
                + "_damage").toLowerCase();
            break;
          default:
            throw new IllegalArgumentException("Unknown AttackType: " + attackType);
        }
      } else {
        switch (attackType) {
          case SLASH, HIT, SPELL, PHYSICS_SPELL_SLASH, PHYSICS_SPELL_HIT, BREATH:
            column = (jobType.name()
                + "_" + attributeType.name()
                + "_" + replacedAttackType.name()
                + "_" + raceType.name()
                + "_damage").toLowerCase();
            break;
          case HEALING_SPELL, HEALING_SPECIALTY:
            column = (jobType.name()
                + "_" + replacedAttackType.name()
                + "_damage").toLowerCase();
            break;
          default:
            throw new IllegalArgumentException("Unknown AttackType: " + attackType);
        }
      }
      switch (attackType) {
        case SLASH, HIT, SPELL, PHYSICS_SPELL_SLASH, PHYSICS_SPELL_HIT, BREATH:
          if (attributeType.equals(AttributeType.NONE)) {
            if (raceType.equals(RaceType.NONE)) {
              replacedQuery = queryTemplateDamageNoAttributeWithoutRace.replace("{{project-id}}", projectId)
                      .replace("{{dataset}}", dataset)
                      .replace("{{table}}", table)
                      .replace("{{JOB}}", jobType.name())
                      .replace("{{job}}", jobType.name().toLowerCase())
                      .replace("{{param0}}", parameters.get(0))
                      .replace("{{param1}}", parameters.get(1))
                      .replace("{{param2}}", parameters.get(2))
                      .replace("{{param3}}", parameters.get(3))
                      .replace("{{base}}", Integer.toString(base))
                      .replace("{{attack}}", replacedAttackType.name().toLowerCase())
                      .replace("{{Attack}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, replacedAttackType.name()))
                      .replace("{{attribute}}", attributeType.name().toLowerCase())
                      .replace("{{Attribute}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, attributeType.name()))
                      .replace("{{race}}", raceType.name().toLowerCase())
                      .replace("{{Race}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, raceType.name()))
                      .replace("{{pattern}}", pattern)
                      .replace("{{cost}}", Integer.toString(cost))
                      .replace("{{joinedNonBrides}}", joinedNonBrides)
                      .replace("{{joinedExclusions}}", joinedExclusions)
                      .replace("{{inclusions}}", replacedInclusions.toString())
                      .replace("{{column}}", column)
                      .replace("{{limit}}", Integer.toString(limit))
                      .replace("{{k0Order}}", k0Order)
                      .replace("{{k0Limit}}", Integer.toString(k0Limit))
                      .replace("{{k0JoinedExclusions}}", k0JoinedExclusions)
                      .replace("{{k0Inclusions}}", k0ReplacedInclusions.toString())
                      .replace("{{k1Order}}", k1Order)
                      .replace("{{k1Limit}}", Integer.toString(k1Limit))
                      .replace("{{k1JoinedExclusions}}", k1JoinedExclusions)
                      .replace("{{k1Inclusions}}", k1ReplacedInclusions.toString());
            } else {
              replacedQuery = queryTemplateDamageNoAttributeWithRace.replace("{{project-id}}", projectId)
                      .replace("{{dataset}}", dataset)
                      .replace("{{table}}", table)
                      .replace("{{JOB}}", jobType.name())
                      .replace("{{job}}", jobType.name().toLowerCase())
                      .replace("{{param0}}", parameters.get(0))
                      .replace("{{param1}}", parameters.get(1))
                      .replace("{{param2}}", parameters.get(2))
                      .replace("{{param3}}", parameters.get(3))
                      .replace("{{base}}", Integer.toString(base))
                      .replace("{{attack}}", replacedAttackType.name().toLowerCase())
                      .replace("{{Attack}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, replacedAttackType.name()))
                      .replace("{{attribute}}", attributeType.name().toLowerCase())
                      .replace("{{Attribute}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, attributeType.name()))
                      .replace("{{race}}", raceType.name().toLowerCase())
                      .replace("{{Race}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, raceType.name()))
                      .replace("{{pattern}}", pattern)
                      .replace("{{cost}}", Integer.toString(cost))
                      .replace("{{joinedNonBrides}}", joinedNonBrides)
                      .replace("{{joinedExclusions}}", joinedExclusions)
                      .replace("{{inclusions}}", replacedInclusions.toString())
                      .replace("{{column}}", column)
                      .replace("{{limit}}", Integer.toString(limit))
                      .replace("{{k0Order}}", k0Order)
                      .replace("{{k0Limit}}", Integer.toString(k0Limit))
                      .replace("{{k0JoinedExclusions}}", k0JoinedExclusions)
                      .replace("{{k0Inclusions}}", k0ReplacedInclusions.toString())
                      .replace("{{k1Order}}", k1Order)
                      .replace("{{k1Limit}}", Integer.toString(k1Limit))
                      .replace("{{k1JoinedExclusions}}", k1JoinedExclusions)
                      .replace("{{k1Inclusions}}", k1ReplacedInclusions.toString());
            }
          } else {
            if (raceType.equals(RaceType.NONE)) {
              replacedQuery = queryTemplateDamageAttributeWithoutRace.replace("{{project-id}}", projectId)
                      .replace("{{dataset}}", dataset)
                      .replace("{{table}}", table)
                      .replace("{{JOB}}", jobType.name())
                      .replace("{{job}}", jobType.name().toLowerCase())
                      .replace("{{param0}}", parameters.get(0))
                      .replace("{{param1}}", parameters.get(1))
                      .replace("{{param2}}", parameters.get(2))
                      .replace("{{param3}}", parameters.get(3))
                      .replace("{{base}}", Integer.toString(base))
                      .replace("{{attack}}", replacedAttackType.name().toLowerCase())
                      .replace("{{Attack}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, replacedAttackType.name()))
                      .replace("{{attribute}}", attributeType.name().toLowerCase())
                      .replace("{{Attribute}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, attributeType.name()))
                      .replace("{{race}}", raceType.name().toLowerCase())
                      .replace("{{Race}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, raceType.name()))
                      .replace("{{pattern}}", pattern)
                      .replace("{{cost}}", Integer.toString(cost))
                      .replace("{{joinedNonBrides}}", joinedNonBrides)
                      .replace("{{joinedExclusions}}", joinedExclusions)
                      .replace("{{inclusions}}", replacedInclusions.toString())
                      .replace("{{column}}", column)
                      .replace("{{limit}}", Integer.toString(limit))
                      .replace("{{k0Order}}", k0Order)
                      .replace("{{k0Limit}}", Integer.toString(k0Limit))
                      .replace("{{k0JoinedExclusions}}", k0JoinedExclusions)
                      .replace("{{k0Inclusions}}", k0ReplacedInclusions.toString())
                      .replace("{{k1Order}}", k1Order)
                      .replace("{{k1Limit}}", Integer.toString(k1Limit))
                      .replace("{{k1JoinedExclusions}}", k1JoinedExclusions)
                      .replace("{{k1Inclusions}}", k1ReplacedInclusions.toString());
            } else {
              replacedQuery = queryTemplateDamageAttributeWithRace.replace("{{project-id}}", projectId)
                      .replace("{{dataset}}", dataset)
                      .replace("{{table}}", table)
                      .replace("{{JOB}}", jobType.name())
                      .replace("{{job}}", jobType.name().toLowerCase())
                      .replace("{{param0}}", parameters.get(0))
                      .replace("{{param1}}", parameters.get(1))
                      .replace("{{param2}}", parameters.get(2))
                      .replace("{{param3}}", parameters.get(3))
                      .replace("{{base}}", Integer.toString(base))
                      .replace("{{attack}}", replacedAttackType.name().toLowerCase())
                      .replace("{{Attack}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, replacedAttackType.name()))
                      .replace("{{attribute}}", attributeType.name().toLowerCase())
                      .replace("{{Attribute}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, attributeType.name()))
                      .replace("{{race}}", raceType.name().toLowerCase())
                      .replace("{{Race}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, raceType.name()))
                      .replace("{{pattern}}", pattern)
                      .replace("{{cost}}", Integer.toString(cost))
                      .replace("{{joinedNonBrides}}", joinedNonBrides)
                      .replace("{{joinedExclusions}}", joinedExclusions)
                      .replace("{{inclusions}}", replacedInclusions.toString())
                      .replace("{{column}}", column)
                      .replace("{{limit}}", Integer.toString(limit))
                      .replace("{{k0Order}}", k0Order)
                      .replace("{{k0Limit}}", Integer.toString(k0Limit))
                      .replace("{{k0JoinedExclusions}}", k0JoinedExclusions)
                      .replace("{{k0Inclusions}}", k0ReplacedInclusions.toString())
                      .replace("{{k1Order}}", k1Order)
                      .replace("{{k1Limit}}", Integer.toString(k1Limit))
                      .replace("{{k1JoinedExclusions}}", k1JoinedExclusions)
                      .replace("{{k1Inclusions}}", k1ReplacedInclusions.toString());
            }
          }
          break;
        case HEALING_SPELL, HEALING_SPECIALTY:
          replacedQuery = queryTemplateHealingAll.replace("{{project-id}}", projectId)
                  .replace("{{dataset}}", dataset)
                  .replace("{{table}}", table)
                  .replace("{{JOB}}", jobType.name())
                  .replace("{{job}}", jobType.name().toLowerCase())
                  .replace("{{param0}}", parameters.get(0))
                  .replace("{{param1}}", parameters.get(1))
                  .replace("{{param2}}", parameters.get(2))
                  .replace("{{param3}}", parameters.get(3))
                  .replace("{{base}}", Integer.toString(base))
                  .replace("{{attack}}", replacedAttackType.name().toLowerCase())
                  .replace("{{Attack}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, replacedAttackType.name()))
                  .replace("{{attribute}}", attributeType.name().toLowerCase())
                  .replace("{{Attribute}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, attributeType.name()))
                  .replace("{{race}}", raceType.name().toLowerCase())
                  .replace("{{Race}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, raceType.name()))
                  .replace("{{pattern}}", pattern)
                  .replace("{{cost}}", Integer.toString(cost))
                  .replace("{{joinedNonBrides}}", joinedNonBrides)
                  .replace("{{joinedExclusions}}", joinedExclusions)
                  .replace("{{inclusions}}", replacedInclusions.toString())
                  .replace("{{column}}", column)
                  .replace("{{limit}}", Integer.toString(limit))
                  .replace("{{k0Order}}", k0Order)
                  .replace("{{k0Limit}}", Integer.toString(k0Limit))
                  .replace("{{k0JoinedExclusions}}", k0JoinedExclusions)
                  .replace("{{k0Inclusions}}", k0ReplacedInclusions.toString())
                  .replace("{{k1Order}}", k1Order)
                  .replace("{{k1Limit}}", Integer.toString(k1Limit))
                  .replace("{{k1JoinedExclusions}}", k1JoinedExclusions)
                  .replace("{{k1Inclusions}}", k1ReplacedInclusions.toString());
          break;
        default:
          throw new IllegalArgumentException("Unknown AttackType: " + attackType);
      }
    } else if (tableType.equals(BigQueryTableType.ONE)) {
      throw new UnsupportedOperationException("Not implemented: " + tableType);
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
