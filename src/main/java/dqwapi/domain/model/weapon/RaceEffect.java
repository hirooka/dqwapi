package dqwapi.domain.model.weapon;

import dqwapi.domain.model.common.RaceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RaceEffect {
  private RaceType race;
  private int magnification;
}
