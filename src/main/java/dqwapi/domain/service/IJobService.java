package dqwapi.domain.service;

import dqwapi.domain.model.job.JobClassType;
import dqwapi.domain.model.job.JobParameter;
import dqwapi.domain.model.job.JobSpecificEffect;
import dqwapi.domain.model.job.JobStatus;
import dqwapi.domain.model.job.JobType;

public interface IJobService {
  JobStatus getStatus(final JobType jobType, final int level);

  JobSpecificEffect getSpecificEffect(
      final JobType jobType, final int level, final boolean isSpecialty
  );

  int getCost(final int level);

  int getCost(final JobType jobType, final int level);

  JobClassType getJobClass(final JobType jobType);

  JobParameter getJobParameter(final JobType jobType, final int level, final int experience);

}
