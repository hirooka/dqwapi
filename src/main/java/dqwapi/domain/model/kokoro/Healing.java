package dqwapi.domain.model.kokoro;

import dqwapi.domain.model.common.HealingType;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Healing {
  private UUID uuid = UUID.randomUUID();
  private HealingType type;
  private int magnification;
}
