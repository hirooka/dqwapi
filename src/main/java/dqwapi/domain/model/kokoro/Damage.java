package dqwapi.domain.model.kokoro;

import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.common.SkillType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Damage {
  private SkillType skill;
  private AttackType attack;
  private AttributeType attribute;
  private RaceType race;
  private int magnification;
}
