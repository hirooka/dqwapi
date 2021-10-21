package dqwapi.domain.entity;

import dqwapi.domain.model.common.HealingType;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
public class HealingEntity {

  @Getter
  @Setter
  @Id
  private UUID uuid = UUID.randomUUID();

  @Getter
  @Setter
  private HealingType type;

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
