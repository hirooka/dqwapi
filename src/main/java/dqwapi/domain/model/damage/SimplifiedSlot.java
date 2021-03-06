package dqwapi.domain.model.damage;

import dqwapi.domain.model.common.KokoroType;
import dqwapi.domain.model.kokoro.GradeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SimplifiedSlot {
  private KokoroType type;
  private int number;
  private String name;
  private GradeType grade;
  private KokoroType color;
  private int cost;
  private boolean isUp;
}
