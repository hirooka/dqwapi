package dqwapi.domain.model.kokoro;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Combination {
  private List<Slot> slots;
  private Parameter parameter;
  private List<DamageMagnification> damageMagnifications;
}
