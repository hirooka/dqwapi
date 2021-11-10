package dqwapi.domain.operator;

import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.damage.SimplifiedSlot;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.Combination;
import dqwapi.domain.model.kokoro.Damage;
import dqwapi.domain.model.kokoro.KokoroCombinationResult;
import dqwapi.domain.model.kokoro.RankType;
import dqwapi.domain.model.kokoro.Slot;
import dqwapi.domain.service.IJobService;
import dqwapi.domain.service.IKokoroService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@RequiredArgsConstructor
@Component
public class KokoroOperator implements IKokoroOperator {

  @Value("${dqwapi.response}")
  private int response;

  private final IJobService jobService;
  private final IKokoroService kokoroService;

  @Override
  public List<KokoroCombinationResult> getCombinations(
      final JobType jobType,
      final int level,
      final String bride,
      final Map<Integer, List<RankType>> exclusions,
      final AttributeType attributeType,
      final AttackType attackType,
      final RaceType raceType
  ) {
    List<KokoroCombinationResult> results = new ArrayList<>();

    final int cost = jobService.getCost(level);

    final StopWatch stopWatch = new StopWatch();
    stopWatch.start("getKokoroCombinations");
    final List<Combination> combinations =
        kokoroService.getCombinations(
            jobType, attackType, attributeType, raceType,
            cost, bride, exclusions, 50
        );
    stopWatch.stop();
    log.info("getKokoroCombinations: {} ms",
        String.format("%,d", stopWatch.getLastTaskTimeMillis()));

    stopWatch.start("processCombinations");
    for (final Combination combination : combinations) {
      int attributeMagnification = 100;
      int attackMagnification = 100;
      int raceMagnification = 100;
      for (final Damage damage : combination.getDamages()) {
        if (attackType.equals(damage.getAttack())
            && damage.getAttribute().equals(AttributeType.NONE)
            && damage.getRace().equals(RaceType.NONE)
        ) {
          attackMagnification += damage.getMagnification();
        }
        if (attackType.equals(damage.getAttack())
            && damage.getAttribute().equals(attributeType)
            && damage.getRace().equals(RaceType.NONE)
            || damage.getAttack().equals(AttackType.ALL)
            && damage.getAttribute().equals(attributeType)
            && damage.getRace().equals(RaceType.NONE)
        ) {
          attributeMagnification += damage.getMagnification();
        }
        if (!raceType.equals(RaceType.NONE) && damage.getRace().equals(raceType)) {
          raceMagnification += damage.getMagnification();
        }
      }

      final int basis;
      switch (attackType) {
        case SLASH:
        case HIT:
          basis = combination.getOp();
          break;
        case SPELL:
          basis = combination.getOs();
          break;
        case PHYSICS_SPELL_SLASH:
        case PHYSICS_SPELL_HIT:
          basis = combination.getOp() + combination.getOs();
          break;
        case BREATH:
          basis = combination.getOp() + combination.getDx();
          break;
        case HEALING:
          basis = combination.getDs();
          break;
        default:
          throw new IllegalArgumentException("Unknown AttackType: " + attackType);
      }
      final int value = (int) Math.ceil(
          basis
              * (attackMagnification / 100.0)
              * (attributeMagnification / 100.0)
              * (raceMagnification / 100.0)
      );

      final KokoroCombinationResult result = new KokoroCombinationResult();
      result.setAttribute(attributeType);
      result.setAttack(attackType);
      result.setRace(raceType);
      result.setValue(value);
      result.setHp(combination.getHp());
      result.setMp(combination.getMp());
      result.setOp(combination.getOp());
      result.setDp(combination.getDp());
      result.setOs(combination.getOs());
      result.setDs(combination.getDs());
      result.setSp(combination.getSp());
      result.setDx(combination.getDx());
      result.setCost(combination.getCost());
      result.setAttributeMag(attributeMagnification);
      result.setAttackMag(attackMagnification);
      result.setRaceMag(raceMagnification);
      final List<SimplifiedSlot> simplifiedSlots = new ArrayList<>();
      for (final Slot slot : combination.getSlots()) {
        final SimplifiedSlot simplifiedSlot = new SimplifiedSlot();
        simplifiedSlot.setType(slot.getType());
        simplifiedSlot.setId(slot.getKokoro().getId());
        simplifiedSlot.setName(slot.getKokoro().getName());
        simplifiedSlot.setRank(slot.getKokoro().getRank());
        simplifiedSlot.setCost(slot.getKokoro().getCost());
        simplifiedSlot.setUp(slot.isUp());
        simplifiedSlots.add(simplifiedSlot);
      }
      result.setSlots(simplifiedSlots);
      results.add(result);
    }
    stopWatch.stop();
    log.info("processCombinations: {} ms",
        String.format("%,d", stopWatch.getLastTaskTimeMillis()));

    stopWatch.start("sortResults");
    if (results.size() > response) {
      results = results.stream()
          .sorted(Comparator.comparingInt(KokoroCombinationResult::getValue).reversed())
          .collect(Collectors.toList())
          .subList(0, response);
    } else {
      results = results.stream()
          .sorted(Comparator.comparingInt(KokoroCombinationResult::getValue).reversed())
          .collect(Collectors.toList());
    }
    stopWatch.stop();
    log.info("sortResults: {} ms",
        String.format("%,d", stopWatch.getLastTaskTimeMillis()));

    return results;
  }
}