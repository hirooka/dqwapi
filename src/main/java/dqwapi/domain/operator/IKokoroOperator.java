package dqwapi.domain.operator;

import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.KokoroCombinationResult;
import dqwapi.domain.model.kokoro.RankType;
import java.util.List;
import java.util.Map;

public interface IKokoroOperator {
  List<KokoroCombinationResult> getCombinations(
      final JobType jobType,
      final int level,
      final String bride,
      final Map<Integer, List<RankType>> exclusions,
      final AttributeType attributeType,
      final AttackType attackType,
      final RaceType raceType
  );
}
