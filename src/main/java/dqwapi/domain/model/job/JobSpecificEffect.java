package dqwapi.domain.model.job;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobSpecificEffect {
  private int power;
  private int slash;
  private int hit;
  private int offensiveMagicalPower;
  private int healingMagicalPower;
  private int spell;
  private int dexterity;
  private int breath;
}
