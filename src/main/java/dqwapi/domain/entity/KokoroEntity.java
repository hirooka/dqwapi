package dqwapi.domain.entity;

import dqwapi.domain.model.common.KokoroType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.kokoro.GradeType;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"id", "grade"}))
@Entity
public class KokoroEntity {

  @Getter
  @Setter
  @Id
  private UUID uuid = UUID.randomUUID();

  @Getter
  @Setter
  private Integer id;

  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  private GradeType grade;

  @Getter
  @Setter
  private KokoroType type;

  @Getter
  @Setter
  private RaceType race;

  @Getter
  @Setter
  private Integer cost;

  @Getter
  @Setter
  private Integer plusCost;

  @Getter
  @Setter
  private Integer hp;

  @Getter
  @Setter
  private Integer mp;

  @Getter
  @Setter
  private Integer op;

  @Getter
  @Setter
  private Integer dp;

  @Getter
  @Setter
  private Integer os;

  @Getter
  @Setter
  private Integer ds;

  @Getter
  @Setter
  private Integer sp;

  @Getter
  @Setter
  private Integer dx;

  @Getter
  @Setter
  @OneToMany(mappedBy = "kokoro", cascade = CascadeType.ALL)
  private List<DamageEntity> damages;

  @Getter
  @Setter
  @OneToMany(mappedBy = "kokoro", cascade = CascadeType.ALL)
  private List<HealingEntity> healings;

}
