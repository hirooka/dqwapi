package dqwapi.domain.entity;

import dqwapi.domain.model.kokoro.RankType;
import lombok.Data;

@Data
public class Result {
  private Integer k0id;
  private RankType k0rank;
  private Integer k1id;
  private RankType k1rank;
  private Integer k2id;
  private RankType k2rank;
  private Integer k3id;
  private RankType k3rank;
  private String pattern;
}
