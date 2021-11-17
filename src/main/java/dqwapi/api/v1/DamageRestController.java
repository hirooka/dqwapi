package dqwapi.api.v1;

import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.damage.DamageResult;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.GradeType;
import dqwapi.domain.operator.IDamageOperator;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RequestMapping("${dqwapi.path-prefix}v1/damages")
@RestController
public class DamageRestController {

  private final IDamageOperator damageOperator;

  @Hidden
  @GetMapping
  List<DamageResult> damages(
      @RequestParam(value = "j", required = false) JobType jobType,
      @RequestParam(value = "l", required = false) Integer level,
      @RequestParam(value = "d", required = false) Integer defence,
      @RequestParam(value = "w", required = false) String weapon,
      @RequestParam(value = "s", required = false) String skill,
      @RequestParam(value = "b", required = false) String bride,
      @RequestParam(value = "e", required = false) List<String> exclusions,
      @RequestParam(value = "r", required = false) RaceType raceType
  ) {
    log.info(
        "jobType: {}, level: {}, defence: {}, weapon: {}"
            + ", skill: {}, bride: {}, exclusions: {}, race: {}",
        jobType, level, defence, weapon, skill, bride, exclusions, raceType);
    if (ObjectUtils.isEmpty(jobType)) {
      jobType = JobType.BATTLE_MASTER;
    }
    if (ObjectUtils.isEmpty(level)) {
      level = 80;
    }
    if (ObjectUtils.isEmpty(defence)) {
      defence = 500;
    }
    if (ObjectUtils.isEmpty(weapon)) {
      weapon = "ルビスの剣";
    }
    if (ObjectUtils.isEmpty(skill)) {
      skill = "創世の光";
    }
    if (ObjectUtils.isEmpty(bride)) {
      bride = "フローラ";
    } else {
      if (!bride.equals("ビアンカ") && !bride.equals("フローラ") && !bride.equals("デボラ")) {
        throw new IllegalArgumentException("Unknown bride: " + bride);
      }
    }
    final Map<Integer, List<GradeType>> excludeMap = new HashMap<>();
    if (!ObjectUtils.isEmpty(exclusions)) {
      for (String str : exclusions) {
        final int key = Integer.parseInt(str.substring(0, str.length() - 1));
        final GradeType gradeType = GradeType.valueOf(str.substring(str.length() - 1).toUpperCase());
        if (excludeMap.containsKey(key)) {
          final List<GradeType> gradeTypes = new ArrayList<>(excludeMap.get(key));
          gradeTypes.add(gradeType);
          excludeMap.put(key, gradeTypes);
        } else {
          excludeMap.put(key, List.of(gradeType));
        }
      }
    }
    if (ObjectUtils.isEmpty(raceType)) {
      raceType = RaceType.NONE;
    }
    return damageOperator.getDamages(
        weapon, skill, jobType, level, defence, bride, excludeMap, raceType
    );
  }
}
