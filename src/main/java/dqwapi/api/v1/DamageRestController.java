package dqwapi.api.v1;

import dqwapi.domain.model.damage.DamageResult;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.operator.IDamageOperator;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RequestMapping("api/v1/damages")
@RestController
public class DamageRestController {

  private final IDamageOperator damageOperator;

  @GetMapping
  List<DamageResult> damages(
      @RequestParam(value = "j", required = false) JobType jobType,
      @RequestParam(value = "l", required = false) Integer level,
      @RequestParam(value = "d", required = false) Integer defence,
      @RequestParam(value = "w", required = false) String weapon,
      @RequestParam(value = "s", required = false) String skill,
      @RequestParam(value = "b", required = false) String bride
  ) {
    if (ObjectUtils.isEmpty(jobType)) {
      jobType = JobType.BATTLE_MASTER;
    }
    if (ObjectUtils.isEmpty(level)) {
      level = 75;
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
        throw new IllegalArgumentException("Illegal Argument: set correct bride name.");
      }
    }
    return damageOperator.getDamages(weapon, skill, jobType, level, defence, bride);
  }
}
