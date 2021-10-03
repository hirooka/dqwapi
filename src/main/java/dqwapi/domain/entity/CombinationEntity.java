package dqwapi.domain.entity;

import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import org.hibernate.annotations.Type;

@Data
@Entity
public class CombinationEntity {

  @Type(type = "uuid-char")
  @Id
  private UUID uuid = UUID.randomUUID();

  @OneToMany(mappedBy = "combination", cascade = CascadeType.ALL)
  private List<SlotEntity> slots;

  @OneToMany(mappedBy = "combination", cascade = CascadeType.ALL)
  private List<DamageEntity> damages;

}
