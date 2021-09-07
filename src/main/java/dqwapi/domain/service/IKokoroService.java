package dqwapi.domain.service;

import dqwapi.domain.model.kokoro.Slot;
import java.util.List;

public interface IKokoroService {
  List<List<Slot>> getCombinations();
}
