package dqwapi.domain.model.weapon;

import dqwapi.domain.model.job.JobType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobEffect {
  private JobType job;
  private int offensivePower;
  private int offensiveMagicalPower;
  private int healingMagicalPower;
}
