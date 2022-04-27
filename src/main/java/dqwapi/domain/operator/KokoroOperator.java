package dqwapi.domain.operator;

import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.HealingType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.damage.SimplifiedSlot;
import dqwapi.domain.model.job.JobParameter;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.Combination;
import dqwapi.domain.model.kokoro.Damage;
import dqwapi.domain.model.kokoro.Healing;
import dqwapi.domain.model.kokoro.KokoroCombinationResult;
import dqwapi.domain.model.kokoro.GradeType;
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
  public Map<String, Object> getCombinationInfo() {
    return kokoroService.getCombinationInfo();
  }

  @Override
  public List<KokoroCombinationResult> getCombinations(
      final JobType jobType,
      final int level,
      final String bride,
      final Map<Integer, List<GradeType>> exclusions,
      final Map<Integer, List<GradeType>> inclusions,
      final AttributeType attributeType,
      final AttackType attackType,
      final RaceType raceType
  ) {
    List<KokoroCombinationResult> results = new ArrayList<>();

    final int cost = jobService.getCost(level);
    final JobParameter jobParameter = jobService.getJobParameter(jobType, level, 10);

    final StopWatch stopWatch = new StopWatch();
    stopWatch.start("getKokoroCombinations");
    final List<Combination> combinations =
        kokoroService.getCombinations(
            jobType, attackType, attributeType, raceType,
            cost, jobParameter, bride, exclusions, inclusions, response * 2
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
        if (!damage.getAttribute().equals(AttributeType.NONE) && (attackType.equals(damage.getAttack()) && damage.getAttribute().equals(attributeType) && damage.getRace().equals(RaceType.NONE) || damage.getAttack().equals(AttackType.ALL) && damage.getAttribute().equals(attributeType) && damage.getRace().equals(RaceType.NONE))) {
          attributeMagnification += damage.getMagnification();
        }
        if (!raceType.equals(RaceType.NONE) && damage.getRace().equals(raceType)) {
          raceMagnification += damage.getMagnification();
        }
      }

      int healingSkillMagnification = 100;
      int healingSpellMagnification = 100;
      int healingSpecialtyMagnification = 100;
      for (final Healing healing : combination.getHealings()) {
        if (healing.getType().equals(HealingType.SKILL)) {
          healingSkillMagnification += healing.getMagnification();
        }
        if (healing.getType().equals(HealingType.SPELL)) {
          healingSpellMagnification += healing.getMagnification();
        }
        if (healing.getType().equals(HealingType.SPECIALTY)) {
          healingSpecialtyMagnification += healing.getMagnification();
        }
      }

      final int basis;
      switch (attackType) {
        case SLASH:
        case HIT:
          basis = combination.getOp() + jobParameter.getOp();
          break;
        case SPELL:
          basis = combination.getOs() + jobParameter.getOs();
          break;
        case PHYSICS_SPELL_SLASH:
        case PHYSICS_SPELL_HIT:
          basis = combination.getOp() + combination.getOs() + jobParameter.getOp() + jobParameter.getOs();
          break;
        case BREATH:
          basis = combination.getOp() + combination.getDx() + jobParameter.getOp() + jobParameter.getDx();
          break;
        case HEALING_SPELL:
        case HEALING_SPECIALTY:
          basis = combination.getDs() + jobParameter.getDs();
          break;
        default:
          throw new IllegalArgumentException("Unknown AttackType: " + attackType);
      }

      final int value;
      switch (attackType) {
        case SLASH, HIT, SPELL, PHYSICS_SPELL_SLASH, PHYSICS_SPELL_HIT, BREATH:
          value = (int) Math.ceil(
              basis
                  * (attackMagnification / 100.0)
                  * (attributeMagnification / 100.0)
                  * (raceMagnification / 100.0)
          );
          break;
        case HEALING_SPELL:
          value = (int) Math.ceil(
              basis
                  * (healingSkillMagnification / 100.0)
                  * (healingSpellMagnification / 100.0)
          );
          break;
        case HEALING_SPECIALTY:
          value = (int) Math.ceil(
              basis
                  * (healingSkillMagnification / 100.0)
                  * (healingSpecialtyMagnification / 100.0)
          );
          break;
        default:
          throw new IllegalArgumentException("Unknown AttackType: " + attackType);
      }

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
      result.setOpos(combination.getOp() + combination.getOs());
      result.setOpdx(combination.getOp() + combination.getDx());
      result.setCost(combination.getCost());
      result.setAttributeMag(attributeMagnification);
      result.setAttackMag(attackMagnification);
      result.setRaceMag(raceMagnification);
      result.setHealingSkillMag(healingSkillMagnification);
      result.setHealingSpellMag(healingSpellMagnification);
      result.setHealingSpecialtyMag(healingSpecialtyMagnification);
      final List<SimplifiedSlot> simplifiedSlots = new ArrayList<>();
      for (final Slot slot : combination.getSlots()) {
        final SimplifiedSlot simplifiedSlot = new SimplifiedSlot();
        simplifiedSlot.setType(slot.getType());
        simplifiedSlot.setNumber(slot.getKokoro().getNumber());
        simplifiedSlot.setName(slot.getKokoro().getName());
        simplifiedSlot.setGrade(slot.getKokoro().getGrade());
        simplifiedSlot.setColor(slot.getKokoro().getType());
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

  @Override
  public List<KokoroCombinationResult> getCombinations(String weaponName, String skillName, JobType jobType, int level, String bride, Map<Integer, List<GradeType>> exclusions, Map<Integer, List<GradeType>> inclusions, AttributeType attributeType, AttackType attackType, RaceType raceType, String defense) {
    return null;
  }
}
