package dqwapi.domain.model.damage;

import dqwapi.domain.model.common.KokoroType;
import dqwapi.domain.model.kokoro.RankType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SimplifiedSlot {
  private KokoroType type;
  private String name;
  private RankType rank;
}
