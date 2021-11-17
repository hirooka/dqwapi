package dqwapi.domain.entity;

import dqwapi.domain.model.common.KokoroType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.kokoro.GradeType;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"id", "grade"}))
@Entity
public class KokoroFlatEntity {

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
  private Integer slashNoneNone;

  @Getter
  @Setter
  private Integer hitNoneNone;

  @Getter
  @Setter
  private Integer spellNoneNone;

  @Getter
  @Setter
  private Integer breathNoneNone;

  @Getter
  @Setter
  private Integer allBagiNone;

  @Getter
  @Setter
  private Integer allDeinNone;

  @Getter
  @Setter
  private Integer allDorumaNone;

  @Getter
  @Setter
  private Integer allGiraNone;

  @Getter
  @Setter
  private Integer allHyadoNone;

  @Getter
  @Setter
  private Integer allIoNone;

  @Getter
  @Setter
  private Integer allJibariaNone;

  @Getter
  @Setter
  private Integer allMeraNone;

  @Getter
  @Setter
  private Integer slashBagiNone;

  @Getter
  @Setter
  private Integer slashDeinNone;

  @Getter
  @Setter
  private Integer slashDorumaNone;

  @Getter
  @Setter
  private Integer slashGiraNone;

  @Getter
  @Setter
  private Integer slashHyadoNone;

  @Getter
  @Setter
  private Integer slashIoNone;

  @Getter
  @Setter
  private Integer slashJibariaNone;

  @Getter
  @Setter
  private Integer slashMeraNone;

  @Getter
  @Setter
  private Integer hitBagiNone;

  @Getter
  @Setter
  private Integer hitDeinNone;

  @Getter
  @Setter
  private Integer hitDorumaNone;

  @Getter
  @Setter
  private Integer hitGiraNone;

  @Getter
  @Setter
  private Integer hitHyadoNone;

  @Getter
  @Setter
  private Integer hitIoNone;

  @Getter
  @Setter
  private Integer hitJibariaNone;

  @Getter
  @Setter
  private Integer hitMeraNone;

  @Getter
  @Setter
  private Integer spellBagiNone;

  @Getter
  @Setter

  private Integer spellDeinNone;

  @Getter
  @Setter
  private Integer spellDorumaNone;

  @Getter
  @Setter
  private Integer spellGiraNone;

  @Getter
  @Setter
  private Integer spellHyadoNone;

  @Getter
  @Setter
  private Integer spellIoNone;

  @Getter
  @Setter
  private Integer spellJibariaNone;

  @Getter
  @Setter
  private Integer spellMeraNone;

  @Getter
  @Setter
  private Integer breathBagiNone;

  @Getter
  @Setter
  private Integer breathDeinNone;

  @Getter
  @Setter
  private Integer breathDorumaNone;

  @Getter
  @Setter
  private Integer breathGiraNone;

  @Getter
  @Setter
  private Integer breathHyadoNone;

  @Getter
  @Setter
  private Integer breathIoNone;

  @Getter
  @Setter
  private Integer breathJibariaNone;

  @Getter
  @Setter
  private Integer breathMeraNone;

  @Getter
  @Setter
  private Integer allNoneAnimal;

  @Getter
  @Setter
  private Integer allNoneBird;

  @Getter
  @Setter
  private Integer allNoneDevil;

  @Getter
  @Setter
  private Integer allNoneDragon;

  @Getter
  @Setter
  private Integer allNoneElement;

  @Getter
  @Setter
  private Integer allNoneInsect;

  @Getter
  @Setter
  private Integer allNoneMachine;

  @Getter
  @Setter
  private Integer allNoneMaterial;

  @Getter
  @Setter
  private Integer allNonePhantom;

  @Getter
  @Setter
  private Integer allNonePlant;

  @Getter
  @Setter
  private Integer allNoneSlime;

  @Getter
  @Setter
  private Integer allNoneWater;

  @Getter
  @Setter
  private Integer allNoneZombie;

  @Getter
  @Setter
  private Integer healingSkill;

  @Getter
  @Setter
  private Integer healingSpecialty;

  @Getter
  @Setter
  private Integer healingSpell;

}
