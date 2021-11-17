package dqwapi.domain.repository;

import dqwapi.domain.entity.Result;
import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.GradeType;
import java.util.List;
import java.util.Map;

public interface IKokoroCombinationRepository {

  List<Result> get(
      final JobType jobType,
      final AttackType attackType,
      final AttributeType attributeType,
      final RaceType raceType,
      final int cost,
      final List<Integer> nonBrides,
      final Map<Integer, List<GradeType>> exclusions,
      final int limit
  );

}
