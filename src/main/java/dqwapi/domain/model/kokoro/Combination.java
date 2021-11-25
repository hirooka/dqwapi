package dqwapi.domain.model.kokoro;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Combination {
  private UUID uuid = UUID.randomUUID();
  private List<Slot> slots;
  private List<Damage> damages;
  private List<Healing> healings;
  private int hp;
  private int mp;
  private int op;
  private int dp;
  private int os;
  private int ds;
  private int sp;
  private int dx;
  private int cost;
}
