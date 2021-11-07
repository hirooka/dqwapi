package dqwapi.domain.operator;

import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.damage.DamageResult;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.RankType;
import java.util.List;
import java.util.Map;

public interface IDamageOperator {
  List<DamageResult> getDamages(
      final String weaponName,
      final String skillName,
      final JobType jobType,
      final int level,
      final int defence,
      final String bride,
      final Map<Integer, List<RankType>> exclusions,
      final RaceType raceType
  );
}
