package dqwapi.domain.entity;

import dqwapi.domain.model.common.KokoroType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.kokoro.RankType;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"id", "rank"}))
@Entity
public class KokoroFlatEntity {

  @Getter
  @Setter
  @Id
  private UUID uuid = UUID.randomUUID();

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer id;

  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private RankType rank;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private KokoroType type;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private RaceType race;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer cost;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer plusCost;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer hp;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer mp;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer op;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer dp;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer os;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer ds;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer sp;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer dx;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer breathNoneNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer hitNoneNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer slashNoneNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer spellNoneNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer allBagiNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer allDeinNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer allDorumaNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer allGiraNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer allHyadoNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer allIoNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer allJibariaNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer allMeraNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer hitBagiNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer hitDeinNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer hitDorumaNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer hitGiraNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer hitHyadoNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer hitIoNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer hitJibariaNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer hitMeraNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer slashBagiNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer slashDeinNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer slashDorumaNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer slashGiraNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer slashHyadoNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer slashIoNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer slashJibariaNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer slashMeraNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer spellBagiNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer spellDeinNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer spellDorumaNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer spellGiraNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer spellHyadoNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer spellIoNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer spellJibariaNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer spellMeraNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer breathBagiNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer breathDeinNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer breathDorumaNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer breathGiraNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer breathHyadoNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer breathIoNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer breathJibariaNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer breathMeraNone;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer allNoneAnimal;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer allNoneBird;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer allNoneDevil;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer allNoneDragon;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer allNoneElement;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer allNoneInsect;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer allNoneMachine;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer allNoneMaterial;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer allNonePhantom;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer allNonePlant;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer allNoneSlime;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer allNoneWater;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer allNoneZombie;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer healingSkill;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer healingSpecialty;

  @Getter
  @Setter
  @Column(columnDefinition = "int2")
  private Integer healingSpell;

}
