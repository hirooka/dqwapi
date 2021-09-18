package dqwapi.domain.operator;

import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.job.JobSpecificEffect;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.Combination;
import dqwapi.domain.model.kokoro.DamageMagnification;
import dqwapi.domain.model.weapon.JobEffect;
import dqwapi.domain.model.weapon.Skill;
import dqwapi.domain.model.weapon.Weapon;
import dqwapi.domain.service.IJobService;
import dqwapi.domain.service.IKokoroService;
import dqwapi.domain.service.IWeaponService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Slf4j
@AllArgsConstructor
@Component
public class DamageOperator implements IDamageOperator {

  private final IJobService jobService;
  private final IWeaponService weaponService;
  private final IKokoroService kokoroService;

  @Override
  public void getDamage(final JobType jobType, final int level, final int defence) {

    final List<Weapon> weapons = weaponService.getAll();
    final List<Combination> combinations = kokoroService.getCombinations(jobType);

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
        log.debug(
            "{}: {}, {}, {}, {}, {}",
            weapon.getName(), skill.getName(),  skill.getRange(),
            skill.getAttack(), skill.getAttribute(), skill.getMagnification()
        );
        final int skillMagnification = skill.getMagnification();

        for (Combination combination : combinations) {

          int attackMagnification = 100;
          int attributeMagnification = 100;
          int raceMagnification = 100;

          final int offence = combination.getParameter().getOp()
              + jobSpecificEffectPower
              + weaponJobEffectPower
              + weapon.getOffensivePower();

          log.debug(combination.getDamageMagnifications().toString());

          for (DamageMagnification damageMagnification : combination.getDamageMagnifications()) {
            if (!ObjectUtils.isEmpty(skill.getAttack())
                && skill.getAttack().equals(damageMagnification.getAttack())
            ) {
              attackMagnification += damageMagnification.getMagnification();
            }
            if (!ObjectUtils.isEmpty(skill.getAttribute())
                && skill.getAttribute().equals(damageMagnification.getAttribute())
            ) {
              attributeMagnification += damageMagnification.getMagnification();
            }
            if (!ObjectUtils.isEmpty(skill.getRace())
                && !skill.getRace().equals(RaceType.NONE)
                && skill.getRace().equals(damageMagnification.getRace())
            ) {
              raceMagnification += damageMagnification.getMagnification();
            }
          }
          log.debug(combination.getSlots().stream()
              .map(slot -> slot.getKokoro().getName())
              .collect(Collectors.toList()).toString()
          );
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
          final int damage = (int) Math.ceil(
              basis
                  * (skillMagnification / 100.0)
                  * (attackMagnification / 100.0)
                  * (attributeMagnification / 100.0)
                  * (raceMagnification / 100.0)
          );
          log.debug("{}", damage);
        }
      }
    }
  }
}
