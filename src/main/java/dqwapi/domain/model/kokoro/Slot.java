package dqwapi.domain.model.kokoro;

import dqwapi.domain.model.common.KokoroType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Slot {
  private KokoroType type;
  private Kokoro kokoro;
  private int id;
  private String name;
}
