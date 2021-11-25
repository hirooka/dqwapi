package dqwapi.domain.model.kokoro;

import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.damage.SimplifiedSlot;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class KokoroCombinationResult {
  private AttributeType attribute;
  private AttackType attack;
  private RaceType race;
  private int value;
  private List<SimplifiedSlot> slots;
  private int hp;
  private int mp;
  private int op;
  private int dp;
  private int os;
  private int ds;
  private int sp;
  private int dx;
  private int cost;
  private int attackMag;
  private int attributeMag;
  private int raceMag;
  private int healingSkillMag;
  private int healingSpellMag;
  private int healingSpecialtyMag;
}
