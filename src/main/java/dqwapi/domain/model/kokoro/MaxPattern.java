package dqwapi.domain.model.kokoro;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MaxPattern {
  private int value;
  private List<Integer> indexes;
}
