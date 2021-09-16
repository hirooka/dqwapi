package dqwapi.domain.service;

import dqwapi.domain.model.job.JobSpecificEffect;
import dqwapi.domain.model.job.JobType;

public interface IJobService {
  JobSpecificEffect getSpecificEffect(
      final JobType jobType, final int level, final boolean isSpecialty
  );
}
