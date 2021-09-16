package dqwapi.domain.model.weapon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpecialityEffect {
  private int offensivePower;
  private int offensiveMagicalPower;
  private int healingMagicalPower;
}
