package dqwapi.domain.model.kokoro;

import dqwapi.domain.model.common.KokoroType;
import dqwapi.domain.model.common.RaceType;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class KokoroFlat {

  private UUID uuid = UUID.randomUUID();

  private int number;
  private String name;
  private EncounterType encounter;
  private KokoroType type;
  private RaceType race;
  private GradeType grade;
  private int cost;
  private int hp;
  private int mp;
  private int op;
  private int dp;
  private int os;
  private int ds;
  private int sp;
  private int dx;
  private int plusCost;

  private int slashNoneNone;
  private int hitNoneNone;
  private int spellNoneNone;
  private int breathNoneNone;

  private int allAllNone;
  private int allMeraNone;
  private int allGiraNone;
  private int allHyadoNone;
  private int allBagiNone;
  private int allDeinNone;
  private int allJibariaNone;
  private int allIoNone;
  private int allDorumaNone;

  private int slashMeraNone;
  private int slashGiraNone;
  private int slashHyadoNone;
  private int slashBagiNone;
  private int slashDeinNone;
  private int slashJibariaNone;
  private int slashIoNone;
  private int slashDorumaNone;

  private int hitMeraNone;
  private int hitGiraNone;
  private int hitHyadoNone;
  private int hitBagiNone;
  private int hitDeinNone;
  private int hitJibariaNone;
  private int hitIoNone;
  private int hitDorumaNone;

  private int spellMeraNone;
  private int spellGiraNone;
  private int spellHyadoNone;
  private int spellBagiNone;
  private int spellDeinNone;
  private int spellJibariaNone;
  private int spellIoNone;
  private int spellDorumaNone;

  private int breathMeraNone;
  private int breathGiraNone;
  private int breathHyadoNone;
  private int breathBagiNone;
  private int breathDeinNone;
  private int breathJibariaNone;
  private int breathIoNone;
  private int breathDorumaNone;

  private int allNoneSlime;
  private int allNoneAnimal;
  private int allNoneDragon;
  private int allNoneInsect;
  private int allNoneBird;
  private int allNonePlant;
  private int allNoneMaterial;
  private int allNoneMachine;
  private int allNoneZombie;
  private int allNoneDevil;
  private int allNoneElement;
  private int allNonePhantom;
  private int allNoneWater;
  private int allNoneSecret;

  private int healingSkill;
  private int healingSpell;
  private int healingSpecialty;

}
