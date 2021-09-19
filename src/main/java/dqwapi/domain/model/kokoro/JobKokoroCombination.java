package dqwapi.domain.model.kokoro;

import dqwapi.domain.model.job.JobType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobKokoroCombination {
  private JobType job;
  private List<Combination> combinations;
}
