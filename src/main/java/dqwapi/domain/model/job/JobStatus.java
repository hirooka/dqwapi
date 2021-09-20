package dqwapi.domain.model.job;

import dqwapi.domain.model.common.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobStatus {
  private JobType job;
  private int level;
  private Parameter parameter;
}
