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

  private int id;
  private String name;
  private RankType rank;
  private KokoroType type;
  private RaceType race;

  private int cost;
  private int plusCost;

  private int hp;
  private int mp;
  private int op;
  private int dp;
  private int os;
  private int ds;
  private int sp;
  private int dx;

  private int breathNoneNone;
  private int hitNoneNone;
  private int slashNoneNone;
  private int spellNoneNone;

  private int allBagiNone;
  private int allDeinNone;
  private int allDorumaNone;
  private int allGiraNone;
  private int allHyadoNone;
  private int allIoNone;
  private int allJibariaNone;
  private int allMeraNone;

  private int hitBagiNone;
  private int hitDeinNone;
  private int hitDorumaNone;
  private int hitGiraNone;
  private int hitHyadoNone;
  private int hitIoNone;
  private int hitJibariaNone;
  private int hitMeraNone;

  private int slashBagiNone;
  private int slashDeinNone;
  private int slashDorumaNone;
  private int slashGiraNone;
  private int slashHyadoNone;
  private int slashIoNone;
  private int slashJibariaNone;
  private int slashMeraNone;

  private int spellBagiNone;
  private int spellDeinNone;
  private int spellDorumaNone;
  private int spellGiraNone;
  private int spellHyadoNone;
  private int spellIoNone;
  private int spellJibariaNone;
  private int spellMeraNone;

  private int breathBagiNone;
  private int breathDeinNone;
  private int breathDorumaNone;
  private int breathGiraNone;
  private int breathHyadoNone;
  private int breathIoNone;
  private int breathJibariaNone;
  private int breathMeraNone;

  private int allNoneAnimal;
  private int allNoneBird;
  private int allNoneDevil;
  private int allNoneDragon;
  private int allNoneElement;
  private int allNoneInsect;
  private int allNoneMachine;
  private int allNoneMaterial;
  private int allNonePhantom;
  private int allNonePlant;
  private int allNoneSlime;
  private int allNoneWater;
  private int allNoneZombie;
}
