package dqwapi.domain.model.kokoro;

import dqwapi.domain.model.common.KokoroType;
import dqwapi.domain.model.common.RaceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Kokoro {
  private int id;
  private String name;
  private RankType rank;
  private KokoroType type;
  private RaceType race;
  private Status status;
}
