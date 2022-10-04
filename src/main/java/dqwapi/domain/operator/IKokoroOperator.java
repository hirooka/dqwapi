package dqwapi.domain.operator;

import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.KokoroCombinationResult;
import dqwapi.domain.model.kokoro.GradeType;
import java.util.List;
import java.util.Map;

public interface IKokoroOperator {

  Map<String, Object> getCombinationInfo();

  List<KokoroCombinationResult> getCombinations(
      final JobType jobType,
      int cost,
      final int level,
      final String bride,
      final Map<Integer, List<GradeType>> exclusions,
      final Map<Integer, List<GradeType>> inclusions,
      final AttributeType attributeType,
      final AttackType attackType,
      final RaceType raceType
  );

  List<KokoroCombinationResult> getCombinations(
          final String weaponName,
          final String skillName,
          final JobType jobType,
          final int level,
          final String bride,
          final Map<Integer, List<GradeType>> exclusions,
          final Map<Integer, List<GradeType>> inclusions,
          final AttributeType attributeType,
          final AttackType attackType,
          final RaceType raceType,
          final String defense
  );

}
