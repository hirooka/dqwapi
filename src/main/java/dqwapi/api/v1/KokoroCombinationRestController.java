package dqwapi.api.v1;

import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.KokoroCombinationResult;
import dqwapi.domain.model.kokoro.RankType;
import dqwapi.domain.operator.IKokoroOperator;
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
@RequestMapping("${dqwapi.path-prefix}v1/kokoro/combinations")
@RestController
public class KokoroCombinationRestController {

  private final IKokoroOperator kokoroOperator;

  @GetMapping
  List<KokoroCombinationResult> combinations(
      @RequestParam(value = "j", required = false) JobType jobType,
      @RequestParam(value = "l", required = false) Integer level,
      @RequestParam(value = "b", required = false) String bride,
      @RequestParam(value = "e", required = false) List<String> exclusions,
      @RequestParam(value = "atr", required = false) AttributeType attributeType,
      @RequestParam(value = "atk", required = false) AttackType attackType,
      @RequestParam(value = "r", required = false) RaceType raceType
  ) {
    log.info(
        "jobType: {}, level: {}, bride: {}, exclusions: {}, attribute: {}, attack: {}, race: {}",
        jobType, level, bride, exclusions, attributeType, attackType, raceType);
    if (ObjectUtils.isEmpty(jobType)) {
      jobType = JobType.BATTLE_MASTER;
    }
    if (ObjectUtils.isEmpty(level)) {
      level = 80;
    }
    if (ObjectUtils.isEmpty(bride)) {
      bride = "フローラ";
    } else {
      if (!bride.equals("ビアンカ") && !bride.equals("フローラ") && !bride.equals("デボラ")) {
        throw new IllegalArgumentException("Illegal Argument: set correct bride name.");
      }
    }
    final Map<Integer, List<RankType>> excludeMap = new HashMap<>();
    if (!ObjectUtils.isEmpty(exclusions)) {
      for (String str : exclusions) {
        final int key = Integer.parseInt(str.substring(0, str.length() - 1));
        final RankType rankType = RankType.valueOf(str.substring(str.length() - 1).toUpperCase());
        if (excludeMap.containsKey(key)) {
          final List<RankType> rankTypes = new ArrayList<>(excludeMap.get(key));
          rankTypes.add(rankType);
          excludeMap.put(key, rankTypes);
        } else {
          excludeMap.put(key, List.of(rankType));
        }
      }
    }
    if (ObjectUtils.isEmpty(attributeType)) {
      attributeType = AttributeType.DEIN;
    }
    if (ObjectUtils.isEmpty(attackType)) {
      attackType = AttackType.SLASH;
    }
    if (ObjectUtils.isEmpty(raceType)) {
      raceType = RaceType.NONE;
    }
    return kokoroOperator.getCombinations(
        jobType, level, bride, excludeMap, attributeType, attackType, raceType
    );
  }
}
