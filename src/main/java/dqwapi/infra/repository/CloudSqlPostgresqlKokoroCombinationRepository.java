package dqwapi.infra.repository;

import dqwapi.domain.entity.Result;
import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.RankType;
import dqwapi.domain.repository.IKokoroCombinationRepository;
import dqwapi.infra.gcp.cloudsql.ICloudSqlPostgresqlConnector;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Slf4j
@AllArgsConstructor
@Repository("cloud-sql-postgresql-repository")
public class CloudSqlPostgresqlKokoroCombinationRepository implements IKokoroCombinationRepository {

  private final ICloudSqlPostgresqlConnector cloudSqlPostgresqlConnector;

  private List<Object[]> getObjects(
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
    for (Map.Entry<Integer, List<RankType>> entry : exclusions.entrySet()) {
      final Integer key = entry.getKey();
      for (RankType rankType : entry.getValue()) {
        switch (rankType) {
          case SP -> exclusionRanks.add(key + "_0");
          case S -> exclusionRanks.add(key + "_1");
          case A -> exclusionRanks.add(key + "_2");
          case B -> exclusionRanks.add(key + "_3");
          case C -> exclusionRanks.add(key + "_4");
          case D -> exclusionRanks.add(key + "_5");
          default -> throw new IllegalArgumentException("Unknown RankType: " + rankType);
        }
      }
    }
    if (exclusionRanks.size() == 0) {
      exclusionRanks.add("");
    }

    if (raceType.equals(RaceType.NONE)) {
      final String column =
          (jobType.name() + "_" + attributeType.name() + "_" + attackType.name() + "_damage")
              .toLowerCase();
      switch (jobType) {
        case BATTLE_MASTER:
          switch (attackType) {
            case SLASH:
            case HIT:
              return cloudSqlPostgresqlConnector.findByBattleMasterOp(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case SPELL:
              return cloudSqlPostgresqlConnector.findByBattleMasterOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case PHYSICS_SPELL_SLASH:
            case PHYSICS_SPELL_HIT:
              return cloudSqlPostgresqlConnector.findByBattleMasterOpOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case BREATH:
              return cloudSqlPostgresqlConnector.findByBattleMasterOpDx(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case HEALING:
              return cloudSqlPostgresqlConnector.findByBattleMasterDs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            default:
              throw new IllegalArgumentException("Unknown AttackType: " + attackType);
          }
        case SAGE:
          switch (attackType) {
            case SLASH:
            case HIT:
              return cloudSqlPostgresqlConnector.findBySageOp(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case SPELL:
              return cloudSqlPostgresqlConnector.findBySageOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case PHYSICS_SPELL_SLASH:
            case PHYSICS_SPELL_HIT:
              return cloudSqlPostgresqlConnector.findBySageOpOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case BREATH:
              return cloudSqlPostgresqlConnector.findBySageOpDx(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case HEALING:
              return cloudSqlPostgresqlConnector.findBySageDs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            default:
              throw new IllegalArgumentException("Unknown AttackType: " + attackType);
          }
        case RANGER:
          switch (attackType) {
            case SLASH:
            case HIT:
              return cloudSqlPostgresqlConnector.findByRangerOp(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case SPELL:
              return cloudSqlPostgresqlConnector.findByRangerOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case PHYSICS_SPELL_SLASH:
            case PHYSICS_SPELL_HIT:
              return cloudSqlPostgresqlConnector.findByRangerOpOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case BREATH:
              return cloudSqlPostgresqlConnector.findByRangerOpDx(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case HEALING:
              return cloudSqlPostgresqlConnector.findByRangerDs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            default:
              throw new IllegalArgumentException("Unknown AttackType: " + attackType);
          }
        case ARMAMENTALIST:
          switch (attackType) {
            case SLASH:
            case HIT:
              return cloudSqlPostgresqlConnector.findByArmamentalistOp(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case SPELL:
              return cloudSqlPostgresqlConnector.findByArmamentalistOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case PHYSICS_SPELL_SLASH:
            case PHYSICS_SPELL_HIT:
              return cloudSqlPostgresqlConnector.findByArmamentalistOpOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case BREATH:
              return cloudSqlPostgresqlConnector.findByArmamentalistOpDx(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case HEALING:
              return cloudSqlPostgresqlConnector.findByArmamentalistDs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            default:
              throw new IllegalArgumentException("Unknown AttackType: " + attackType);
          }
        case PALADIN:
          switch (attackType) {
            case SLASH:
            case HIT:
              return cloudSqlPostgresqlConnector.findByPaladinOp(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case SPELL:
              return cloudSqlPostgresqlConnector.findByPaladinOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case PHYSICS_SPELL_SLASH:
            case PHYSICS_SPELL_HIT:
              return cloudSqlPostgresqlConnector.findByPaladinOpOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case BREATH:
              return cloudSqlPostgresqlConnector.findByPaladinOpDx(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case HEALING:
              return cloudSqlPostgresqlConnector.findByPaladinDs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            default:
              throw new IllegalArgumentException("Unknown AttackType: " + attackType);
          }
        case SUPERSTAR:
          switch (attackType) {
            case SLASH:
            case HIT:
              return cloudSqlPostgresqlConnector.findBySuperstarOp(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case SPELL:
              return cloudSqlPostgresqlConnector.findBySuperstarOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case PHYSICS_SPELL_SLASH:
            case PHYSICS_SPELL_HIT:
              return cloudSqlPostgresqlConnector.findBySuperstarOpOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case BREATH:
              return cloudSqlPostgresqlConnector.findBySuperstarOpDx(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case HEALING:
              return cloudSqlPostgresqlConnector.findBySuperstarDs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            default:
              throw new IllegalArgumentException("Unknown AttackType: " + attackType);
          }
        case PIRATE:
          switch (attackType) {
            case SLASH:
            case HIT:
              return cloudSqlPostgresqlConnector.findByPirateOp(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case SPELL:
              return cloudSqlPostgresqlConnector.findByPirateOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case PHYSICS_SPELL_SLASH:
            case PHYSICS_SPELL_HIT:
              return cloudSqlPostgresqlConnector.findByPirateOpOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case BREATH:
              return cloudSqlPostgresqlConnector.findByPirateOpDx(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case HEALING:
              return cloudSqlPostgresqlConnector.findByPirateDs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            default:
              throw new IllegalArgumentException("Unknown AttackType: " + attackType);
          }
        default:
          throw new IllegalArgumentException("Unknown JobType: " + jobType);
      }
    } else {
      final String column =
          (jobType.name()
              + "_" + attributeType.name()
              + "_" + attackType.name()
              + "_" + raceType.name() + "_damage").toLowerCase();
      switch (jobType) {
        case BATTLE_MASTER:
          switch (attackType) {
            case SLASH:
            case HIT:
              return cloudSqlPostgresqlConnector.findCombinationsByBattleMasterOp(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case SPELL:
              return cloudSqlPostgresqlConnector.findCombinationsByBattleMasterOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case PHYSICS_SPELL_SLASH:
            case PHYSICS_SPELL_HIT:
              return cloudSqlPostgresqlConnector.findCombinationsByBattleMasterOpOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case BREATH:
              return cloudSqlPostgresqlConnector.findCombinationsByBattleMasterOpDx(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case HEALING:
              return cloudSqlPostgresqlConnector.findCombinationsByBattleMasterDs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            default:
              throw new IllegalArgumentException("Unknown AttackType: " + attackType);
          }
        case SAGE:
          switch (attackType) {
            case SLASH:
            case HIT:
              return cloudSqlPostgresqlConnector.findCombinationsBySageOp(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case SPELL:
              return cloudSqlPostgresqlConnector.findCombinationsBySageOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case PHYSICS_SPELL_SLASH:
            case PHYSICS_SPELL_HIT:
              return cloudSqlPostgresqlConnector.findCombinationsBySageOpOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case BREATH:
              return cloudSqlPostgresqlConnector.findCombinationsBySageOpDx(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case HEALING:
              return cloudSqlPostgresqlConnector.findCombinationsBySageDs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            default:
              throw new IllegalArgumentException("Unknown AttackType: " + attackType);
          }
        case RANGER:
          switch (attackType) {
            case SLASH:
            case HIT:
              return cloudSqlPostgresqlConnector.findCombinationsByRangerOp(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case SPELL:
              return cloudSqlPostgresqlConnector.findCombinationsByRangerOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case PHYSICS_SPELL_SLASH:
            case PHYSICS_SPELL_HIT:
              return cloudSqlPostgresqlConnector.findCombinationsByRangerOpOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case BREATH:
              return cloudSqlPostgresqlConnector.findCombinationsByRangerOpDx(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case HEALING:
              return cloudSqlPostgresqlConnector.findCombinationsByRangerDs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            default:
              throw new IllegalArgumentException("Unknown AttackType: " + attackType);
          }
        case ARMAMENTALIST:
          switch (attackType) {
            case SLASH:
            case HIT:
              return cloudSqlPostgresqlConnector.findCombinationsByArmamentalistOp(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case SPELL:
              return cloudSqlPostgresqlConnector.findCombinationsByArmamentalistOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case PHYSICS_SPELL_SLASH:
            case PHYSICS_SPELL_HIT:
              return cloudSqlPostgresqlConnector.findCombinationsByArmamentalistOpOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case BREATH:
              return cloudSqlPostgresqlConnector.findCombinationsByArmamentalistOpDx(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case HEALING:
              return cloudSqlPostgresqlConnector.findCombinationsByArmamentalistDs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            default:
              throw new IllegalArgumentException("Unknown AttackType: " + attackType);
          }
        case PALADIN:
          switch (attackType) {
            case SLASH:
            case HIT:
              return cloudSqlPostgresqlConnector.findCombinationsByPaladinOp(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case SPELL:
              return cloudSqlPostgresqlConnector.findCombinationsByPaladinOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case PHYSICS_SPELL_SLASH:
            case PHYSICS_SPELL_HIT:
              return cloudSqlPostgresqlConnector.findCombinationsByPaladinOpOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case BREATH:
              return cloudSqlPostgresqlConnector.findCombinationsByPaladinOpDx(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case HEALING:
              return cloudSqlPostgresqlConnector.findCombinationsByPaladinDs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            default:
              throw new IllegalArgumentException("Unknown AttackType: " + attackType);
          }
        case SUPERSTAR:
          switch (attackType) {
            case SLASH:
            case HIT:
              return cloudSqlPostgresqlConnector.findCombinationsBySuperstarOp(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case SPELL:
              return cloudSqlPostgresqlConnector.findCombinationsBySuperstarOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case PHYSICS_SPELL_SLASH:
            case PHYSICS_SPELL_HIT:
              return cloudSqlPostgresqlConnector.findCombinationsBySuperstarOpOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case BREATH:
              return cloudSqlPostgresqlConnector.findCombinationsBySuperstarOpDx(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case HEALING:
              return cloudSqlPostgresqlConnector.findCombinationsBySuperstarDs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            default:
              throw new IllegalArgumentException("Unknown AttackType: " + attackType);
          }
        case PIRATE:
          switch (attackType) {
            case SLASH:
            case HIT:
              return cloudSqlPostgresqlConnector.findCombinationsByPirateOp(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case SPELL:
              return cloudSqlPostgresqlConnector.findCombinationsByPirateOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case PHYSICS_SPELL_SLASH:
            case PHYSICS_SPELL_HIT:
              return cloudSqlPostgresqlConnector.findCombinationsByPirateOpOs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case BREATH:
              return cloudSqlPostgresqlConnector.findCombinationsByPirateOpDx(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            case HEALING:
              return cloudSqlPostgresqlConnector.findCombinationsByPirateDs(
                  PageRequest.of(0, limit, Sort.Direction.DESC, column),
                  cost, nonBrides, exclusionRanks
              );
            default:
              throw new IllegalArgumentException("Unknown AttackType: " + attackType);
          }
        default:
          throw new IllegalArgumentException("Unknown JobType: " + jobType);
      }
    }
  }

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

  private List<Result> convertResult(final List<Object[]> objects) {
    final List<Result> results = new ArrayList<>();
    for (final Object[] o : objects) {
      final Result result = new Result();
      result.setK0id((Integer) o[0]);
      result.setK0rank(convertRankType((Integer) o[1]));
      result.setK1id((Integer) o[2]);
      result.setK1rank(convertRankType((Integer) o[3]));
      result.setK2id((Integer) o[4]);
      result.setK2rank(convertRankType((Integer) o[5]));
      result.setK3id((Integer) o[6]);
      result.setK3rank(convertRankType((Integer) o[7]));
      result.setPattern((String) o[8]);
      results.add(result);
    }
    return results;
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
    return convertResult(
        getObjects(jobType, attackType, attributeType, raceType, cost, nonBrides, exclusions, limit)
    );
  }
}