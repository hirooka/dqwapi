package dqwapi.domain.service;

import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.Combination;
import dqwapi.domain.model.kokoro.Kokoro;
import dqwapi.domain.model.kokoro.RankType;
import java.util.List;
import java.util.Map;

public interface IKokoroService {

  List<Kokoro> getAll();

  List<Map<String, String>> get();

  List<Combination> getCombinationsOnMemory(final JobType jobType);

  List<Combination> getCombinationsOnMemory(
      final JobType jobType,
      final int cost,
      final String bride,
      final Map<Integer, List<RankType>> exclusions
  );

  List<Combination> getCombinations(
      final JobType jobType,
      final AttackType attackType,
      final AttributeType attributeType,
      final RaceType raceType,
      final int cost,
      final String bride,
      final Map<Integer, List<RankType>> exclusions,
      final int limit
  );

  void persistKokoroFlats();

  void persistKokoros();

  void persistCombinations();
}
