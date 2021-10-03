package dqwapi.domain.entity;

import dqwapi.domain.model.common.KokoroType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.kokoro.RankType;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import org.hibernate.annotations.Type;

@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"id", "rank"}))
@Entity
public class KokoroEntity {

  @Type(type = "uuid-char")
  @Id
  private UUID uuid = UUID.randomUUID();

  private Integer id;

  private String name;

  private RankType rank;

  private KokoroType type;

  private RaceType race;

  private Integer cost;

  private Integer plusCost;

  private Integer hp;

  private Integer mp;

  private Integer op;

  private Integer dp;

  private Integer os;

  private Integer ds;

  private Integer sp;

  private Integer dx;

  @OneToMany(mappedBy = "kokoro", cascade = CascadeType.ALL)
  private List<DamageEntity> damages;

}
