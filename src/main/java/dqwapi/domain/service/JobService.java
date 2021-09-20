package dqwapi.domain.service;

import dqwapi.domain.model.common.Parameter;
import dqwapi.domain.model.job.JobSpecificEffect;
import dqwapi.domain.model.job.JobStatus;
import dqwapi.domain.model.job.JobType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JobService implements IJobService {
  @Override
  public JobStatus getStatus(final JobType jobType, final int level) {
    // TODO: implementation
    final JobStatus jobStatus = new JobStatus();
    jobStatus.setJob(jobType);
    jobStatus.setLevel(level);
    jobStatus.setParameter(new Parameter(1, 1, 250, 1, 1, 1, 1, 1));
    return jobStatus;
  }

  @Override
  public JobSpecificEffect getSpecificEffect(
      final JobType jobType, final int level, final boolean isSpecialty
  ) {
    switch (jobType) {
      case BATTLE_MASTER:
        int power = 0;
        int slash = 0;
        int hit = 0;
        if (level >= 5) {
          if (isSpecialty) {
            power += 5;
          }
        }
        if (level >= 20) {
          if (isSpecialty) {
            slash += 5;
            hit += 5;
          }
        }
        if (level >= 30) {
          power += 10;
        }
        if (level >= 45) {
          if (isSpecialty) {
            slash += 5;
            hit += 5;
          }
        }
        if (level >= 60) {
          if (isSpecialty) {
            power += 10;
          }
        }
        if (level >= 65) {
          power += 10;
        }
        if (level >= 75) {
          if (isSpecialty) {
            power += 5;
          }
        }
        final JobSpecificEffect effect = new JobSpecificEffect();
        effect.setPower(power);
        effect.setSlash(slash);
        effect.setHit(hit);
        return effect;
      case RANGER:
      case PALADIN:
      case SAGE:
      default:
        return new JobSpecificEffect();
    }
  }
}
