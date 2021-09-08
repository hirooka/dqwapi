package dqwapi.domain.service;

import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.Combination;
import java.util.List;

public interface IKokoroService {
  List<Combination> getCombinations(final JobType jobType);
}
