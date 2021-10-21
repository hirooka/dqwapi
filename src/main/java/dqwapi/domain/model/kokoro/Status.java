package dqwapi.domain.model.kokoro;

import dqwapi.domain.model.common.Parameter;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Status {
  private UUID uuid = UUID.randomUUID();
  private Parameter parameter;
  private List<Effect> effects;
}
