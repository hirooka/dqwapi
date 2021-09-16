package dqwapi.domain.model;

import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.weapon.Range;
import java.util.UUID;
import lombok.Data;

@Data
public class Spell {
  private UUID uuid;
  private String name;
  private String displayName;
  private Range range;
  private int limit;
  private int basis;
  private AttributeType attributeType;
}
