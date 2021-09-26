package dqwapi.domain.model.kokoro;

import dqwapi.domain.model.common.Parameter;
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
  private List<Damage> damages;
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
