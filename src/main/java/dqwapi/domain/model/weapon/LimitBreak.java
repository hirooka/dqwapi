package dqwapi.domain.model.weapon;

import dqwapi.domain.model.common.AttackType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LimitBreak {
  private AttackType attack;
  private int magnification;
}
