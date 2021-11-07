package dqwapi.domain.service;

import dqwapi.domain.model.common.CsvType;

public interface IDataService {
  void createCombinationCsv(final CsvType csvType);
}
