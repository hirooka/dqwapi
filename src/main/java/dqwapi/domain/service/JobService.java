package dqwapi.domain.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dqwapi.domain.model.common.Parameter;
import dqwapi.domain.model.job.JobParameter;
import dqwapi.domain.model.job.JobSpecificEffect;
import dqwapi.domain.model.job.JobStatus;
import dqwapi.domain.model.job.JobType;
import java.io.IOException;
import java.util.*;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Slf4j
@Service
public class JobService implements IJobService {

  private List<Map<String, Integer>> levelCostMap = new ArrayList<>();
  private Map<JobType, List<JobParameter>> jobBase = new HashMap<>();
  private Map<JobType, List<JobParameter>> jobSpeciality = new HashMap<>();
  private Map<JobType, List<JobParameter>> jobExperience = new HashMap<>();
  private Map<JobType, List<JobParameter>> jobMonster = new HashMap<>();

  private Map<JobType, List<JobParameter>> map(final String json) throws IOException {
    return new ObjectMapper().readValue(new ClassPathResource(json).getInputStream(), new TypeReference<>() {});
  }

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
      jobBase = map("job-base.json");
      jobSpeciality = map("job-speciality.json");
      jobExperience = map("job-experience.json");
      jobMonster = map("job-monster.json");
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
      case SAGE:
      case RANGER:
      case ARMAMENTALIST:
      case PALADIN:
      case SUPERSTAR:
      case PIRATE:
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

  private JobParameter calculateJobParameter(JobType jobType, int level, int experience) {
    final JobParameter baseParameter = jobBase.get(jobType).stream()
            .filter(p -> p.getL() == level).findFirst().orElseThrow(NoSuchElementException::new);
    final JobParameter specialityParameter = jobSpeciality.get(jobType).stream()
            .filter(p -> p.getL() == level).findFirst().orElseThrow(NoSuchElementException::new);
    final JobParameter experienceParameter = jobExperience.get(jobType).stream()
            .filter(p -> p.getL() == 10).findFirst().orElseThrow(NoSuchElementException::new);
    final JobParameter monsterParameter = jobMonster.get(jobType).stream()
            .filter(p -> p.getL() == 50).findFirst().orElseThrow(NoSuchElementException::new);
    final List<JobParameter> jobParameterList = new ArrayList<>();
    jobParameterList.add(baseParameter);
    jobParameterList.add(specialityParameter);
    jobParameterList.add(experienceParameter);
    jobParameterList.add(monsterParameter);
    final JobParameter jobParameter = new JobParameter();
    jobParameter.setL(level);
    for (final JobParameter p : jobParameterList) {
      jobParameter.setHp(jobParameter.getHp() + p.getHp());
      jobParameter.setMp(jobParameter.getMp() + p.getMp());
      jobParameter.setOp(jobParameter.getOp() + p.getOp());
      jobParameter.setDp(jobParameter.getDp() + p.getDp());
      jobParameter.setOs(jobParameter.getOs() + p.getOs());
      jobParameter.setDs(jobParameter.getDs() + p.getDs());
      jobParameter.setSp(jobParameter.getSp() + p.getSp());
      jobParameter.setDx(jobParameter.getDx() + p.getDx());
      jobParameter.setSlash(jobParameter.getSlash() + p.getSlash());
      jobParameter.setHit(jobParameter.getHit() + p.getHit());
      jobParameter.setSpell(jobParameter.getSpell() + p.getSpell());
      jobParameter.setBreath(jobParameter.getBreath() + p.getBreath());
      jobParameter.setHealing(jobParameter.getHealing() + p.getHealing());
      jobParameter.setChr(jobParameter.getChr() + p.getChr());
      jobParameter.setRar(jobParameter.getRar() + p.getRar());
    }
    return jobParameter;
  }

  @Override
  public JobParameter getJobParameter(JobType jobType, int level, int experience) {
    return calculateJobParameter(jobType, level, 10);
  }
}
