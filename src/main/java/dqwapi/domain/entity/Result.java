package dqwapi.domain.entity;

import dqwapi.domain.model.kokoro.GradeType;
import lombok.Data;

@Data
public class Result {
  private Integer k0id;
  private GradeType k0grade;
  private Integer k1id;
  private GradeType k1grade;
  private Integer k2id;
  private GradeType k2grade;
  private Integer k3id;
  private GradeType k3grade;
  private String pattern;
}
