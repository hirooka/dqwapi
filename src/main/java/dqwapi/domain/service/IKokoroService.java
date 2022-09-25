package dqwapi.domain.service;

import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.job.JobClassType;
import dqwapi.domain.model.job.JobParameter;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.Combination;
import dqwapi.domain.model.kokoro.Kokoro;
import dqwapi.domain.model.kokoro.GradeType;
import java.util.List;
import java.util.Map;

public interface IKokoroService {

  List<Kokoro> getAll();

  List<Map<String, String>> get();

  Map<String, Object> getCombinationInfo();

  List<Map<String, String>> getJobs();

  List<Combination> getCombinationsOnMemory(final JobType jobType);

  List<Combination> getCombinationsOnMemory(
      final JobType jobType,
      final int cost,
      final String bride,
      final Map<Integer, List<GradeType>> exclusions
  );

  List<Combination> getCombinations(
      final JobType jobType,
      final JobClassType jobClassType,
      final AttackType attackType,
      final AttributeType attributeType,
      final RaceType raceType,
      final int cost,
      final JobParameter jobParameter,
      final String bride,
      final Map<Integer, List<GradeType>> exclusions,
      final Map<Integer, List<GradeType>> inclusions,
      final int limit
  );

  void persistKokoroFlats();

  void persistKokoros();

  void persistCombinations();
}
