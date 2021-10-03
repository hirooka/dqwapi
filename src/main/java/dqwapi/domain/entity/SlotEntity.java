package dqwapi.domain.entity;

import dqwapi.domain.model.common.KokoroType;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.Type;

@Data
@Entity
public class SlotEntity  {

  @Type(type = "uuid-char")
  @Id
  private UUID uuid = UUID.randomUUID();

  private KokoroType type;

  @OneToOne()
  private KokoroEntity kokoro;

  @ManyToOne
  private CombinationEntity combination;
}
