package dqwapi.domain.model.kokoro;

import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.RaceType;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Damage {
  private UUID uuid = UUID.randomUUID();
  private AttackType attack;
  private AttributeType attribute;
  private RaceType race;
  private int magnification;
}
