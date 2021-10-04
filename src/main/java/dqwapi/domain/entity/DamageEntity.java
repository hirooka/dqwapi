package dqwapi.domain.entity;

import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.RaceType;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
public class DamageEntity  {

  @Getter
  @Setter
  //@Type(type = "uuid-char")
  @Id
  private UUID uuid = UUID.randomUUID();
  @Getter
  @Setter
  private AttackType attack;

  @Getter
  @Setter
  private AttributeType attribute;

  @Getter
  @Setter
  private RaceType race;

  @Getter
  @Setter
  private Integer magnification;

  @Getter
  @Setter
  @ManyToOne
  private KokoroEntity kokoro;

  @Getter
  @Setter
  @ManyToOne
  private CombinationEntity combination;

}
