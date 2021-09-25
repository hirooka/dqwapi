package dqwapi.domain.model.kokoro;

import dqwapi.domain.model.common.Parameter;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuitableCombination {
  private List<Kokoro> kokoros;
  private Parameter parameter;
}
