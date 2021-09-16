package dqwapi.domain.model.weapon;

import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.RaceType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Skill {
  private String name;
  private AttributeType attribute;
  private AttackType attack;
  private Range range;
  private int magnification;
  private RaceType race;
  private List<Pursuit> pursuits;
}
