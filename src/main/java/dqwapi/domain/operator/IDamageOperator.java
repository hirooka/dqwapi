package dqwapi.domain.operator;

import dqwapi.domain.model.job.JobType;

public interface IDamageOperator {
  void getDamage(final JobType jobType, final int level, final int defence);
}
