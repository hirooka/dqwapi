package dqwapi.domain.model.damage;

import dqwapi.domain.model.common.Parameter;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DamageResult {
  private String weaponName;
  private String skillName;
  private int damage;
  private List<SimplifiedSlot> slots;
  private Parameter parameter;
  private int hp;
  private int mp;
  private int op;
  private int dp;
  private int os;
  private int ds;
  private int sp;
  private int dx;
  private int cost;
  private int skillMagnification;
  private int attackMagnification;
  private int attributeMagnification;
  private int raceMagnification;
}
