package dqwapi.domain.service;

import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.Combination;
import dqwapi.domain.model.kokoro.Kokoro;
import java.util.List;

public interface IKokoroService {
  List<Kokoro> getAll();

  List<Combination> getCombinations(final JobType jobType);

  List<Combination> getCombinations(final JobType jobType, final int cost, final String bride);
}
