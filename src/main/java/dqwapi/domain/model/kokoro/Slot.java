package dqwapi.domain.model.kokoro;

import dqwapi.domain.model.common.KokoroType;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Slot {
  private UUID uuid = UUID.randomUUID();
  private KokoroType type;
  private Kokoro kokoro;
  private boolean isUp;
}
