package dqwapi.domain.model.weapon;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Weapon {
  private String name;
  private WeaponType type;
  private int offensivePower;
  private int healingPower;
  private List<JobEffect> jobEffects;
  private List<SpecialityEffect> specialityEffects;
  private List<Skill> skills;
  private List<RaceEffect> raceEffects;
  private List<LimitBreak> limitBreaks;
}
