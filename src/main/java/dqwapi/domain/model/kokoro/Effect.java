package dqwapi.domain.model.kokoro;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Effect {
  private UUID uuid = UUID.randomUUID();
  private int plusCost;
  private List<Damage> damages;
  private int critical;
}
