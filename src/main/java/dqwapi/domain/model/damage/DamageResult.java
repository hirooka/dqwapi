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
  private int cost;
  private int skillMagnification;
  private int attackMagnification;
  private int attributeMagnification;
  private int raceMagnification;
}
