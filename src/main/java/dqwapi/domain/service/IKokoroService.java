package dqwapi.domain.service;

import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.Combination;
import dqwapi.domain.model.kokoro.Kokoro;
import dqwapi.domain.model.kokoro.RankType;
import java.util.List;
import java.util.Map;

public interface IKokoroService {
  List<Kokoro> getAll();

  List<Combination> getCombinations(final JobType jobType);

  List<Combination> getCombinations(
      final JobType jobType,
      final int cost,
      final String bride,
      final Map<Integer, List<RankType>> exclusions
  );

  void persistKokoros();

  void persistCombinations();
}
