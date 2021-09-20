package dqwapi.domain.model.damage;

import dqwapi.domain.model.kokoro.Parameter;
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
}
