package dqwapi.domain.operator;

import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.damage.DamageResult;
import dqwapi.domain.model.damage.SimplifiedSlot;
import dqwapi.domain.model.job.JobSpecificEffect;
import dqwapi.domain.model.job.JobStatus;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.Combination;
import dqwapi.domain.model.kokoro.Damage;
import dqwapi.domain.model.kokoro.RankType;
import dqwapi.domain.model.kokoro.Slot;
import dqwapi.domain.model.weapon.JobEffect;
import dqwapi.domain.model.weapon.Skill;
import dqwapi.domain.model.weapon.Weapon;
import dqwapi.domain.service.IJobService;
import dqwapi.domain.service.IKokoroService;
import dqwapi.domain.service.IWeaponService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StopWatch;

@Slf4j
@RequiredArgsConstructor
@Component
public class DamageOperator implements IDamageOperator {

  @Value("${dqwapi.response}")
  private int response;

  private final IJobService jobService;
  private final IWeaponService weaponService;
  private final IKokoroService kokoroService;

  @Override
  public List<DamageResult> getDamages(
      final String weaponName,
      final String skillName,
      final JobType jobType,
      final int level,
      final int defence,
      final String bride,
      final Map<Integer, List<RankType>> exclusions,
      final RaceType raceType
  ) {
    List<DamageResult> damageResults = new ArrayList<>();

    final List<Weapon> weapons = weaponService.getAll();
    final int cost = jobService.getCost(level);

    final JobStatus jobStatus = jobService.getStatus(jobType, level);
    final int power = jobStatus.getParameter().getOp();

    final JobSpecificEffect jobSpecificEffect = jobService.getSpecificEffect(jobType, level, true);
    final int jobSpecificEffectPower = jobSpecificEffect.getPower();
    log.debug("{}: power+ = {}", jobType.name(), jobSpecificEffect.getPower());

    for (Weapon weapon : weapons) {
      log.debug(weapon.getName());
      final int weaponJobEffectPower;
      if (!ObjectUtils.isEmpty(weapon.getJobEffects())) {
        weaponJobEffectPower = weapon.getJobEffects().stream()
            .filter(jobEffect -> jobEffect.getJob().equals(jobType))
            .mapToInt(JobEffect::getOffensivePower)
            .distinct()
            .sum();
      } else {
        weaponJobEffectPower = 0;
      }

      for (Skill skill : weapon.getSkills()) {
        log.debug("{}: {}", weapon.getName(), skill.getName());
        if (skillName.equals(skill.getName())) {
          log.info(
              "{}: {}, {}, {}, {}, {}",
              weapon.getName(), skill.getName(), skill.getRange(),
              skill.getAttack(), skill.getAttribute(), skill.getMagnification()
          );

          final StopWatch stopWatch = new StopWatch();
          stopWatch.start("getKokoroCombinations");
          final List<Combination> combinations =
              kokoroService.getCombinations(
                  jobType, skill.getAttack(), skill.getAttribute(), raceType,
                  cost, bride, exclusions, 50
              );
          stopWatch.stop();
          log.info("getKokoroCombinations: {} ms", stopWatch.getLastTaskTimeMillis());

          final int skillMagnification = skill.getMagnification();

          stopWatch.start("loopCombinations");
          for (Combination combination : combinations) {

            int attackMagnification = 100;
            int attributeMagnification = 100;
            int raceMagnification = 100;

            final int offence = power
                + combination.getOp()
                + jobSpecificEffectPower
                + weaponJobEffectPower
                + weapon.getOffensivePower();

            log.debug(combination.getDamages().toString());

            for (Damage damage : combination.getDamages()) {
              if (skill.getAttack().equals(damage.getAttack())
                  && damage.getAttribute().equals(AttributeType.NONE)
                  && damage.getRace().equals(RaceType.NONE)
              ) {
                attackMagnification += damage.getMagnification();
              }
              if (skill.getAttack().equals(damage.getAttack())
                  && damage.getAttribute().equals(skill.getAttribute())
                  && damage.getRace().equals(RaceType.NONE)
                  || damage.getAttack().equals(AttackType.ALL)
                  && damage.getAttribute().equals(skill.getAttribute())
                  && damage.getRace().equals(RaceType.NONE)
              ) {
                attributeMagnification += damage.getMagnification();
              }
              if (!raceType.equals(RaceType.NONE) && damage.getRace().equals(raceType)) {
                raceMagnification += damage.getMagnification();
              }
            }
            log.debug(combination.getSlots().stream()
                .map(slot -> slot.getKokoro().getName())
                .collect(Collectors.toList()).toString()
            );

            if (skill.getAttack().equals(AttackType.SLASH)) {
              attackMagnification += jobSpecificEffect.getSlash();
            } else if (skill.getAttack().equals(AttackType.HIT)) {
              attackMagnification += jobSpecificEffect.getHit();
            }

            log.debug(
                "skillMagnification: {}, "
                    + "attackMagnification({}): {}, "
                    + "attributeMagnification({}): {}, "
                    + "raceMagnification({}): {}",
                skillMagnification,
                skill.getAttack(), attackMagnification,
                skill.getAttribute(), attributeMagnification,
                skill.getRace(), raceMagnification
            );

            final int basis = (int) Math.ceil(offence / 2.0 - defence / 4.0);
            final int damageValue = (int) Math.ceil(
                basis
                    * (skillMagnification / 100.0)
                    * (attackMagnification / 100.0)
                    * (attributeMagnification / 100.0)
                    * (raceMagnification / 100.0)
            );
            log.debug("{} * {} * {} * {} * {} = ",
                basis,
                (skillMagnification / 100.0),
                (attackMagnification / 100.0),
                (attributeMagnification / 100.0),
                (raceMagnification / 100.0)
            );
            log.debug("{}", damageValue);

            final DamageResult damageResult = new DamageResult();
            damageResult.setWeaponName(weapon.getName());
            damageResult.setSkillName(skill.getName());
            damageResult.setDamage(damageValue);
            damageResult.setHp(combination.getHp());
            damageResult.setMp(combination.getMp());
            damageResult.setOp(combination.getOp());
            damageResult.setDp(combination.getDp());
            damageResult.setOs(combination.getOs());
            damageResult.setDs(combination.getDs());
            damageResult.setDx(combination.getDx());
            damageResult.setSp(combination.getSp());
            damageResult.setCost(combination.getCost());
            damageResult.setSkillMagnification(skillMagnification);
            damageResult.setAttackMagnification(attackMagnification);
            damageResult.setAttributeMagnification(attributeMagnification);
            damageResult.setRaceMagnification(raceMagnification);
            final List<SimplifiedSlot> simplifiedSlots = new ArrayList<>();
            for (Slot slot : combination.getSlots()) {
              final SimplifiedSlot simplifiedSlot = new SimplifiedSlot();
              simplifiedSlot.setType(slot.getType());
              simplifiedSlot.setId(slot.getKokoro().getId());
              simplifiedSlot.setName(slot.getKokoro().getName());
              simplifiedSlot.setRank(slot.getKokoro().getRank());
              simplifiedSlot.setCost(slot.getKokoro().getCost());
              simplifiedSlot.setUp(slot.isUp());
              simplifiedSlots.add(simplifiedSlot);
            }
            damageResult.setSlots(simplifiedSlots);
            damageResults.add(damageResult);
          }
          stopWatch.stop();
          log.info("loopCombination: {} ms", stopWatch.getLastTaskTimeMillis());
        }
      }
    }
    log.info("result = {}", damageResults.size());

    final StopWatch stopWatch = new StopWatch();
    stopWatch.start("sortDamageResults");
    if (damageResults.size() > response) {
      damageResults = damageResults.stream()
          .sorted(Comparator.comparingInt(DamageResult::getDamage).reversed())
          .collect(Collectors.toList())
          .subList(0, response);
    } else {
      damageResults = damageResults.stream()
          .sorted(Comparator.comparingInt(DamageResult::getDamage).reversed())
          .collect(Collectors.toList());
    }
    stopWatch.stop();
    log.info("sortDamageResults: {} ms", stopWatch.getLastTaskTimeMillis());

    return damageResults;
  }
}
