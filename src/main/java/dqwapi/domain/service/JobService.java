package dqwapi.domain.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dqwapi.domain.model.common.Parameter;
import dqwapi.domain.model.job.JobSpecificEffect;
import dqwapi.domain.model.job.JobStatus;
import dqwapi.domain.model.job.JobType;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Slf4j
@Service
public class JobService implements IJobService {

  private List<Map<String, Integer>> levelCostMap = new ArrayList<>();

  @PostConstruct
  void init() {
    final String levelCostJson = "level-cost.json";
    final Resource levelCostJsonResource = new ClassPathResource(levelCostJson);
    try {
      final StopWatch stopWatch = new StopWatch();
      stopWatch.start("levelCost");
      final ObjectMapper objectMapper = new ObjectMapper();
      levelCostMap =
          objectMapper.readValue(levelCostJsonResource.getInputStream(), new TypeReference<>() {});
      stopWatch.stop();
      log.info("{} levels, {} ms", levelCostMap.size(), stopWatch.getLastTaskTimeMillis());
      log.debug(levelCostMap.toString());
    } catch (IOException ex) {
      throw new IllegalStateException("Failed to parse JSON file.", ex);
    }
  }

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

  @Override
  public int getCost(final int level) {
    for (Map<String, Integer> map : levelCostMap) {
      if (map.get("level") == level) {
        return map.get("cost");
      }
    }
    throw new IllegalArgumentException("Illegal Argument: level = " + level + ".");
  }
}
