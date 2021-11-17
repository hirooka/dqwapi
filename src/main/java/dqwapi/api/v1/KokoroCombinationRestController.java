package dqwapi.api.v1;

import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.KokoroCombinationResult;
import dqwapi.domain.model.kokoro.RankType;
import dqwapi.domain.operator.IKokoroOperator;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "こころの組み合わせ", description = "上級職に最適な4つのこころの組み合わせ")
@Slf4j
@AllArgsConstructor
@RequestMapping("${dqwapi.path-prefix}v1/kokoro/combos")
@RestController
public class KokoroCombinationRestController {

  private final IKokoroOperator kokoroOperator;

  // TODO: without MediaType.APPLICATION_JSON_UTF8_VALUE, Japanese in response on macOS, iOS is be garbled
  @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  List<KokoroCombinationResult> combinations(

      @Parameter(description = "職業",
          schema = @Schema(allowableValues = {
              "BATTLE_MASTER", "SAGE", "RANGER", "ARMAMENTALIST", "PALADIN",
              "SUPERSTAR", "PIRATE"
          }, defaultValue = "BATTLE_MASTER"))
      @RequestParam(value = "j", required = false) JobType jobType,

      @Parameter(description = "レベル",
          schema = @Schema(defaultValue = "80", minimum = "30", maximum = "80"))
      @RequestParam(value = "l", required = false) Integer level,

      @Parameter(description = "花嫁のこころ",
          schema = @Schema(allowableValues = { "ビアンカ", "フローラ", "デボラ" },
              defaultValue = "フローラ"))
      @RequestParam(value = "b", required = false) String bride,

      @Parameter(description = "除外するこころのグレード (例えば、こころIDが123でグレードがSのこころを除外したい場合は、123s)")
      @RequestParam(value = "e", required = false) List<String> exclusions,

      @Parameter(description = "属性",
          schema = @Schema(allowableValues = {
              "BAGI", "DEIN", "DORUMA", "GIRA", "HYADO", "IO", "JIBARIA", "MERA"
          }, defaultValue = "DEIN"))
      @RequestParam(value = "a", required = false) AttributeType attributeType,

      @Parameter(description = "こうげきのタイプ",
          schema = @Schema(allowableValues = {
              "SLASH", "HIT", "SPELL", "PHYSICS_SPELL_SLASH", "PHYSICS_SPELL_HIT", "BREATH"
          }, defaultValue = "SLASH"))
      @RequestParam(value = "k", required = false) AttackType attackType,

      @Parameter(description = "系統",
          schema = @Schema(allowableValues = {
              "NONE", "ANIMAL", "BIRD", "DEVIL", "DRAGON",
              "ELEMENT", "INSECT", "MACHINE", "MATERIAL", "PHANTOM",
              "PLANT", "SLIME", "WATER", "ZOMBIE"
          }, defaultValue = "NONE"))
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
        throw new IllegalArgumentException("Unknown bride: " + bride);
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
