package dqwapi.api.v1;

import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.KokoroCombinationResult;
import dqwapi.domain.model.kokoro.GradeType;
import dqwapi.domain.operator.IKokoroOperator;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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

  @Hidden
  @GetMapping(value = "info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  Map<String, Object> info() {
    return kokoroOperator.getCombinationInfo();
  }

  Map<Integer, List<GradeType>> convert(final List<String> stringList) {
    final Map<Integer, List<GradeType>> map = new HashMap<>();
    if (!ObjectUtils.isEmpty(stringList)) {
      for (String str : stringList) {
        final int key;
        final GradeType gradeType;
        if (str.contains("sp") || str.contains("SP")) {
          key = Integer.parseInt(str.substring(0, str.length() - 2));
          gradeType = GradeType.valueOf(str.substring(str.length() - 2).toUpperCase());
        } else {
          final Pattern pattern = Pattern.compile("[1-9][0-9]*[sabcd]$", Pattern.CASE_INSENSITIVE);
          if (pattern.matcher(str).find()) {
            key = Integer.parseInt(str.substring(0, str.length() - 1));
            gradeType = GradeType.valueOf(str.substring(str.length() - 1).toUpperCase());
          } else {
            throw new IllegalArgumentException("Unknown GradeType: " + str);
          }
        }
        if (map.containsKey(key)) {
          final List<GradeType> gradeTypes = new ArrayList<>(map.get(key));
          gradeTypes.add(gradeType);
          map.put(key, gradeTypes);
        } else {
          map.put(key, List.of(gradeType));
        }
      }
    }
    return map;
  }

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

      @Parameter(description = "除外するこころのグレード (例えば、ばんごうが123でグレードがSのこころを除外したい場合は、123s)")
      @RequestParam(value = "e", required = false) List<String> exclusions,

      @Parameter(description = "必ず含むこころのグレード (例えば、ばんごうが123でグレードがSのこころを必ず含みたい場合は、123s)")
      @RequestParam(value = "i", required = false) List<String> inclusions,

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
        "jobType: {}, level: {}, bride: {}, exclusions: {}, inclusions: {}, attribute: {}, attack: {}, race: {}",
        jobType, level, bride, exclusions, inclusions, attributeType, attackType, raceType);
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
    final Map<Integer, List<GradeType>> exclusionMap = convert(exclusions);
    final Map<Integer, List<GradeType>> inclusionMap = convert(inclusions);
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
        jobType, level, bride, exclusionMap, inclusionMap, attributeType, attackType, raceType
    );
  }
}
