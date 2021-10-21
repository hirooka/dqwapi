package dqwapi.domain.model.kokoro;

import dqwapi.domain.model.common.KokoroType;
import dqwapi.domain.model.common.RaceType;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Kokoro {
  private UUID uuid = UUID.randomUUID();
  private int id;
  private String name;
  private RankType rank;
  private KokoroType type;
  private RaceType race;
  private int cost;
  private int plusCost;
  private int hp;
  private int mp;
  private int op;
  private int dp;
  private int os;
  private int ds;
  private int sp;
  private int dx;
  private List<Damage> damages;
  private List<Healing> healings;
}
