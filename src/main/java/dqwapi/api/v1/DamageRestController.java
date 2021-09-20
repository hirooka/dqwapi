package dqwapi.api.v1;

import dqwapi.domain.model.damage.DamageResult;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.operator.IDamageOperator;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
      @RequestParam(value = "job", required = false) final JobType jobType,
      @RequestParam(required = false) final Integer level,
      @RequestParam(required = false) final Integer defence
  ) {
    // TODO: send param
    //damageOperator.getDamage(jobType, level, defence);
    return damageOperator.getDamages(
        "ルビスの剣", "創世の光", JobType.BATTLE_MASTER, 75, 500
    );
  }
}
