package dqwapi.domain.entity;

import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.RaceType;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.Type;

@Data
@Entity
public class DamageEntity  {

  @Type(type = "uuid-char")
  @Id
  private UUID uuid = UUID.randomUUID();

  private AttackType attack;

  private AttributeType attribute;

  private RaceType race;

  private Integer magnification;

  @ManyToOne
  private KokoroEntity kokoro;

  @ManyToOne
  private CombinationEntity combination;

}
