package dqwapi.domain.model.kokoro;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Effect {
  private int plusCost;
  private List<Damage> damages;
  private int critical;
}
