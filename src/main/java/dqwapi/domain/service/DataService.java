package dqwapi.domain.service;

import static dqwapi.domain.model.common.AttackType.BREATH;
import static dqwapi.domain.model.common.AttackType.HIT;
import static dqwapi.domain.model.common.AttackType.PHYSICS_SPELL_HIT;
import static dqwapi.domain.model.common.AttackType.PHYSICS_SPELL_SLASH;
import static dqwapi.domain.model.common.AttackType.SLASH;
import static dqwapi.domain.model.common.AttackType.SPELL;
import static dqwapi.domain.model.common.AttributeType.BAGI;
import static dqwapi.domain.model.common.AttributeType.DEIN;
import static dqwapi.domain.model.common.AttributeType.DORUMA;
import static dqwapi.domain.model.common.AttributeType.GIRA;
import static dqwapi.domain.model.common.AttributeType.HYADO;
import static dqwapi.domain.model.common.AttributeType.IO;
import static dqwapi.domain.model.common.AttributeType.JIBARIA;
import static dqwapi.domain.model.common.AttributeType.MERA;
import static dqwapi.domain.model.common.CsvType.ALL;
import static dqwapi.domain.model.common.CsvType.ATTRIBUTE;
import static dqwapi.domain.model.common.CsvType.ATTRIBUTE_RACE;
import static dqwapi.domain.model.common.KokoroType.BLUE;
import static dqwapi.domain.model.common.KokoroType.GREEN;
import static dqwapi.domain.model.common.KokoroType.PURPLE;
import static dqwapi.domain.model.common.KokoroType.RED;
import static dqwapi.domain.model.common.KokoroType.YELLOW;
import static dqwapi.domain.model.common.RaceType.ANIMAL;
import static dqwapi.domain.model.common.RaceType.BIRD;
import static dqwapi.domain.model.common.RaceType.DEVIL;
import static dqwapi.domain.model.common.RaceType.DRAGON;
import static dqwapi.domain.model.common.RaceType.ELEMENT;
import static dqwapi.domain.model.common.RaceType.INSECT;
import static dqwapi.domain.model.common.RaceType.MACHINE;
import static dqwapi.domain.model.common.RaceType.MATERIAL;
import static dqwapi.domain.model.common.RaceType.PHANTOM;
import static dqwapi.domain.model.common.RaceType.PLANT;
import static dqwapi.domain.model.common.RaceType.SLIME;
import static dqwapi.domain.model.common.RaceType.WATER;
import static dqwapi.domain.model.common.RaceType.ZOMBIE;
import static dqwapi.domain.model.job.JobType.ARMAMENTALIST;
import static dqwapi.domain.model.job.JobType.BATTLE_MASTER;
import static dqwapi.domain.model.job.JobType.PALADIN;
import static dqwapi.domain.model.job.JobType.PIRATE;
import static dqwapi.domain.model.job.JobType.RANGER;
import static dqwapi.domain.model.job.JobType.SAGE;
import static dqwapi.domain.model.job.JobType.SUPERSTAR;
import static java.lang.Math.ceil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.CsvType;
import dqwapi.domain.model.common.KokoroType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.KokoroFlat;
import dqwapi.domain.model.kokoro.MaxPattern;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Slf4j
@Service
public class DataService implements IDataService {

  @Value("${dqwapi.kokoro-flat-json}")
  private String kokoroFlatJson;

  private List<KokoroFlat> kokoros = new ArrayList<>();

  @PostConstruct
  void init() {
    final Resource kokoroFlatJsonResource = new ClassPathResource(kokoroFlatJson);
    try {
      final StopWatch stopWatch = new StopWatch();
      final ObjectMapper objectMapper = new ObjectMapper();
      stopWatch.start("kokoroFlats");
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      kokoros =
          objectMapper.readValue(kokoroFlatJsonResource.getInputStream(), new TypeReference<>() {});
      stopWatch.stop();
      log.info("{} kokoros, {} ms",
          kokoros.size(), String.format("%,d", stopWatch.getLastTaskTimeMillis())
      );
      log.debug(kokoros.toString());
    } catch (IOException ex) {
      throw new IllegalStateException("Failed to parse JSON file.", ex);
    }
  }

  private int getParameter(
      final JobType jobType,
      final int p0, final KokoroType t0,
      final int p1, final KokoroType t1,
      final int p2, final KokoroType t2,
      final int p3, final KokoroType t3
  ) {
    final double mag = 1.2;
    switch (jobType) {
      case BATTLE_MASTER:
        if (t0.equals(RED)) {
          if (t1.equals(RED)) {
            if (t2.equals(RED) || t2.equals(YELLOW)) {
              return (int) ceil(p0 * mag)
                  + (int) ceil(p1 * mag)
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return (int) ceil(p0 * mag)
                  + (int) ceil(p1 * mag)
                  + p2
                  + (int) ceil(p3 * mag);
            }
          } else {
            if (t2.equals(RED) || t2.equals(YELLOW)) {
              return (int) ceil(p0 * mag)
                  + p1
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return (int) ceil(p0 * mag)
                  + p1
                  + p2
                  + (int) ceil(p3 * mag);
            }
          }
        } else {
          if (t1.equals(RED)) {
            if (t2.equals(RED) || t2.equals(YELLOW)) {
              return p0
                  + (int) ceil(p1 * mag)
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return p0
                  + (int) ceil(p1 * mag)
                  + p2
                  + (int) ceil(p3 * mag);
            }
          } else {
            if (t2.equals(RED) || t2.equals(YELLOW)) {
              return p0
                  + p1
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return p0
                  + p1
                  + p2
                  + (int) ceil(p3 * mag);
            }
          }
        }
      case SAGE:
        if (t0.equals(PURPLE) || t0.equals(GREEN)) {
          if (t1.equals(PURPLE) || t1.equals(GREEN)) {
            if (t2.equals(PURPLE) || t2.equals(GREEN)) {
              return (int) ceil(p0 * mag)
                  + (int) ceil(p1 * mag)
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return (int) ceil(p0 * mag)
                  + (int) ceil(p1 * mag)
                  + p2
                  + (int) ceil(p3 * mag);
            }
          } else {
            if (t2.equals(PURPLE) || t2.equals(GREEN)) {
              return (int) ceil(p0 * mag)
                  + p1
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return (int) ceil(p0 * mag)
                  + p1
                  + p2
                  + (int) ceil(p3 * mag);
            }
          }
        } else {
          if (t1.equals(PURPLE) || t1.equals(GREEN)) {
            if (t2.equals(PURPLE) || t2.equals(GREEN)) {
              return p0
                  + (int) ceil(p1 * mag)
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return p0
                  + (int) ceil(p1 * mag)
                  + p2
                  + (int) ceil(p3 * mag);
            }
          } else {
            if (t2.equals(PURPLE) || t2.equals(GREEN)) {
              return p0
                  + p1
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return p0
                  + p1
                  + p2
                  + (int) ceil(p3 * mag);
            }
          }
        }
      case RANGER:
        if (t0.equals(BLUE)) {
          if (t1.equals(BLUE)) {
            if (t2.equals(RED) || t2.equals(BLUE)) {
              return (int) ceil(p0 * mag)
                  + (int) ceil(p1 * mag)
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return (int) ceil(p0 * mag)
                  + (int) ceil(p1 * mag)
                  + p2
                  + (int) ceil(p3 * mag);
            }
          } else {
            if (t2.equals(RED) || t2.equals(BLUE)) {
              return (int) ceil(p0 * mag)
                  + p1
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return (int) ceil(p0 * mag)
                  + p1
                  + p2
                  + (int) ceil(p3 * mag);
            }
          }
        } else {
          if (t1.equals(BLUE)) {
            if (t2.equals(RED) || t2.equals(BLUE)) {
              return p0
                  + (int) ceil(p1 * mag)
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return p0
                  + (int) ceil(p1 * mag)
                  + p2
                  + (int) ceil(p3 * mag);
            }
          } else {
            if (t2.equals(RED) || t2.equals(BLUE)) {
              return p0
                  + p1
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return p0
                  + p1
                  + p2
                  + (int) ceil(p3 * mag);
            }
          }
        }
      case ARMAMENTALIST:
        if (t0.equals(YELLOW) || t0.equals(PURPLE)) {
          if (t1.equals(YELLOW) || t1.equals(PURPLE)) {
            if (t2.equals(YELLOW) || t2.equals(PURPLE)) {
              return (int) ceil(p0 * mag)
                  + (int) ceil(p1 * mag)
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return (int) ceil(p0 * mag)
                  + (int) ceil(p1 * mag)
                  + p2
                  + (int) ceil(p3 * mag);
            }
          } else {
            if (t2.equals(YELLOW) || t2.equals(PURPLE)) {
              return (int) ceil(p0 * mag)
                  + p1
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return (int) ceil(p0 * mag)
                  + p1
                  + p2
                  + (int) ceil(p3 * mag);
            }
          }
        } else {
          if (t1.equals(YELLOW) || t1.equals(PURPLE)) {
            if (t2.equals(YELLOW) || t2.equals(PURPLE)) {
              return p0
                  + (int) ceil(p1 * mag)
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return p0
                  + (int) ceil(p1 * mag)
                  + p2
                  + (int) ceil(p3 * mag);
            }
          } else {
            if (t2.equals(YELLOW) || t2.equals(PURPLE)) {
              return p0
                  + p1
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return p0
                  + p1
                  + p2
                  + (int) ceil(p3 * mag);
            }
          }
        }
      case PALADIN:
        if (t0.equals(YELLOW) || t0.equals(GREEN)) {
          if (t1.equals(YELLOW) || t1.equals(GREEN)) {
            if (t2.equals(YELLOW) || t2.equals(GREEN)) {
              return (int) ceil(p0 * mag)
                  + (int) ceil(p1 * mag)
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return (int) ceil(p0 * mag)
                  + (int) ceil(p1 * mag)
                  + p2
                  + (int) ceil(p3 * mag);
            }
          } else {
            if (t2.equals(YELLOW) || t2.equals(GREEN)) {
              return (int) ceil(p0 * mag)
                  + p1
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return (int) ceil(p0 * mag)
                  + p1
                  + p2
                  + (int) ceil(p3 * mag);
            }
          }
        } else {
          if (t1.equals(YELLOW) || t1.equals(GREEN)) {
            if (t2.equals(YELLOW) || t2.equals(GREEN)) {
              return p0
                  + (int) ceil(p1 * mag)
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return p0
                  + (int) ceil(p1 * mag)
                  + p2
                  + (int) ceil(p3 * mag);
            }
          } else {
            if (t2.equals(YELLOW) || t2.equals(GREEN)) {
              return p0
                  + p1
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return p0
                  + p1
                  + p2
                  + (int) ceil(p3 * mag);
            }
          }
        }
      case SUPERSTAR:
        if (t0.equals(BLUE)) {
          if (t1.equals(GREEN)) {
            if (t2.equals(BLUE) || t2.equals(GREEN)) {
              return (int) ceil(p0 * mag)
                  + (int) ceil(p1 * mag)
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return (int) ceil(p0 * mag)
                  + (int) ceil(p1 * mag)
                  + p2
                  + (int) ceil(p3 * mag);
            }
          } else {
            if (t2.equals(BLUE) || t2.equals(GREEN)) {
              return (int) ceil(p0 * mag)
                  + p1
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return (int) ceil(p0 * mag)
                  + p1
                  + p2
                  + (int) ceil(p3 * mag);
            }
          }
        } else {
          if (t1.equals(GREEN)) {
            if (t2.equals(BLUE) || t2.equals(GREEN)) {
              return p0
                  + (int) ceil(p1 * mag)
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return p0
                  + (int) ceil(p1 * mag)
                  + p2
                  + (int) ceil(p3 * mag);
            }
          } else {
            if (t2.equals(BLUE) || t2.equals(GREEN)) {
              return p0
                  + p1
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return p0
                  + p1
                  + p2
                  + (int) ceil(p3 * mag);
            }
          }
        }
      case PIRATE:
        if (t0.equals(YELLOW)) {
          if (t1.equals(BLUE)) {
            if (t2.equals(YELLOW) || t2.equals(BLUE)) {
              return (int) ceil(p0 * mag)
                  + (int) ceil(p1 * mag)
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return (int) ceil(p0 * mag)
                  + (int) ceil(p1 * mag)
                  + p2
                  + (int) ceil(p3 * mag);
            }
          } else {
            if (t2.equals(YELLOW) || t2.equals(BLUE)) {
              return (int) ceil(p0 * mag)
                  + p1
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return (int) ceil(p0 * mag)
                  + p1
                  + p2
                  + (int) ceil(p3 * mag);
            }
          }
        } else {
          if (t1.equals(BLUE)) {
            if (t2.equals(YELLOW) || t2.equals(BLUE)) {
              return p0
                  + (int) ceil(p1 * mag)
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return p0
                  + (int) ceil(p1 * mag)
                  + p2
                  + (int) ceil(p3 * mag);
            }
          } else {
            if (t2.equals(YELLOW) || t2.equals(BLUE)) {
              return p0
                  + p1
                  + (int) ceil(p2 * mag)
                  + (int) ceil(p3 * mag);
            } else {
              return p0
                  + p1
                  + p2
                  + (int) ceil(p3 * mag);
            }
          }
        }
      default:
        throw new IllegalArgumentException("Unknown JobType: " + jobType);
    }
  }

  private MaxPattern getMaxPattern(
      final JobType jobType,
      final AttackType attackType,
      final List<KokoroFlat> kokoros
  ) {
    final MaxPattern maxPattern = new MaxPattern();
    int max = 0;
    List<Integer> indexes = Arrays.asList(0, 1, 2, 3);
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        if (i != j) {
          for (int k = 0; k < 4; k++) {
            if (i != k && j != k) {
              for (int l = 0; l < 4; l++) {
                if (i != l && j != l && k != l) {
                  switch (attackType) {
                    case BREATH:
                      final int breath = getParameter(
                          jobType,
                          kokoros.get(i).getOp() + kokoros.get(i).getDx(),
                          kokoros.get(i).getType(),
                          kokoros.get(j).getOp() + kokoros.get(j).getDx(),
                          kokoros.get(j).getType(),
                          kokoros.get(k).getOp() + kokoros.get(k).getDx(),
                          kokoros.get(k).getType(),
                          kokoros.get(l).getOp() + kokoros.get(l).getDx(),
                          kokoros.get(l).getType()
                      );
                      if (breath > max) {
                        max = breath;
                        indexes = Arrays.asList(i, j, k, l);
                      }
                      break;
                    case HEALING_SPELL:
                    case HEALING_SPECIALTY:
                      final int healing = getParameter(
                          jobType,
                          kokoros.get(i).getDs(), kokoros.get(i).getType(),
                          kokoros.get(j).getDs(), kokoros.get(j).getType(),
                          kokoros.get(k).getDs(), kokoros.get(k).getType(),
                          kokoros.get(l).getDs(), kokoros.get(l).getType()
                      );
                      if (healing > max) {
                        max = healing;
                        indexes = Arrays.asList(i, j, k, l);
                      }
                      break;
                    case HIT:
                    case SLASH:
                      final int hitOrSlash = getParameter(
                          jobType,
                          kokoros.get(i).getOp(), kokoros.get(i).getType(),
                          kokoros.get(j).getOp(), kokoros.get(j).getType(),
                          kokoros.get(k).getOp(), kokoros.get(k).getType(),
                          kokoros.get(l).getOp(), kokoros.get(l).getType()
                      );
                      if (hitOrSlash > max) {
                        max = hitOrSlash;
                        indexes = Arrays.asList(i, j, k, l);
                      }
                      break;
                    case PHYSICS_SPELL_HIT:
                    case PHYSICS_SPELL_SLASH:
                      int physicsSpell = getParameter(
                          jobType,
                          kokoros.get(i).getOp() + kokoros.get(i).getOs(),
                          kokoros.get(i).getType(),
                          kokoros.get(j).getOp() + kokoros.get(j).getOs(),
                          kokoros.get(j).getType(),
                          kokoros.get(k).getOp() + kokoros.get(k).getOs(),
                          kokoros.get(k).getType(),
                          kokoros.get(l).getOp() + kokoros.get(l).getOs(),
                          kokoros.get(l).getType()
                      );
                      if (physicsSpell > max) {
                        max = physicsSpell;
                        indexes = Arrays.asList(i, j, k, l);
                      }
                      break;
                    case SPELL:
                      int spell = getParameter(
                          jobType,
                          kokoros.get(i).getOs(), kokoros.get(i).getType(),
                          kokoros.get(j).getOs(), kokoros.get(j).getType(),
                          kokoros.get(k).getOs(), kokoros.get(k).getType(),
                          kokoros.get(l).getOs(), kokoros.get(l).getType()
                      );
                      if (spell > max) {
                        max = spell;
                        indexes = Arrays.asList(i, j, k, l);
                      }
                      break;
                    default:
                      throw new IllegalArgumentException("Unknown AttackType: " + attackType);
                  }
                }
              }
            }
          }
        }
      }
    }
    maxPattern.setValue(max);
    maxPattern.setIndexes(indexes);
    return maxPattern;
  }

  private String getPattern(final List<Integer> indexes) {
    final StringBuilder p = new StringBuilder("p");
    for (int index : indexes) {
      p.append(index);
    }
    return p.toString();
  }

  private String getCsv(
      final JobType jobType,
      final KokoroFlat k0, final KokoroFlat k1, final KokoroFlat k2, final KokoroFlat k3,
      final CsvType csvType
  ) {

    // Pattern
    final MaxPattern opMaxPattern = getMaxPattern(
        jobType,
        AttackType.SLASH,
        Arrays.asList(
            k0, k1, k2, k3
        )
    );
    final int maxOp = opMaxPattern.getValue();
    final String maxOpPattern = getPattern(opMaxPattern.getIndexes());

    final MaxPattern osMaxPattern = getMaxPattern(
        jobType,
        AttackType.SPELL,
        Arrays.asList(
            k0, k1, k2, k3
        )
    );
    final int maxOs = osMaxPattern.getValue();
    final String maxOsPattern = getPattern(osMaxPattern.getIndexes());

    final MaxPattern opOsMaxPattern = getMaxPattern(
        jobType,
        AttackType.PHYSICS_SPELL_SLASH,
        Arrays.asList(
            k0, k1, k2, k3
        )
    );
    final int maxOpOs = opOsMaxPattern.getValue();
    final String maxOpOsPattern = getPattern(opOsMaxPattern.getIndexes());

    final MaxPattern opDxMaxPattern = getMaxPattern(
        jobType,
        AttackType.BREATH,
        Arrays.asList(
            k0, k1, k2, k3
        )
    );
    final int maxOpDx = opDxMaxPattern.getValue();
    final String maxOpDxPattern = getPattern(opDxMaxPattern.getIndexes());

    final MaxPattern dsMaxPattern = getMaxPattern(
        jobType,
        AttackType.HEALING_SPELL,
        Arrays.asList(
            k0, k1, k2, k3
        )
    );
    final int maxDs = dsMaxPattern.getValue();
    final String maxDsPattern = getPattern(dsMaxPattern.getIndexes());

    // Magnification
    final int slashMag = k0.getSlashNoneNone() + k1.getSlashNoneNone() + k2.getSlashNoneNone() + k3.getSlashNoneNone();
    final int hitMag = k0.getHitNoneNone() + k1.getHitNoneNone() + k2.getHitNoneNone() + k3.getHitNoneNone();
    final int spellMag = k0.getSpellNoneNone() + k1.getSpellNoneNone() + k2.getSpellNoneNone() + k3.getSpellNoneNone();
    final int breathMag = k0.getBreathNoneNone() + k1.getBreathNoneNone() + k2.getBreathNoneNone() + k3.getBreathNoneNone();

    final int slashBagiMag = k0.getSlashBagiNone() + k1.getSlashBagiNone() + k2.getSlashBagiNone() + k3.getSlashBagiNone();
    final int slashDeinMag = k0.getSlashDeinNone() + k1.getSlashDeinNone() + k2.getSlashDeinNone() + k3.getSlashDeinNone();
    final int slashDorumaMag = k0.getSlashDorumaNone() + k1.getSlashDorumaNone() + k2.getSlashDorumaNone() + k3.getSlashDorumaNone();
    final int slashGiraMag = k0.getSlashGiraNone() + k1.getSlashGiraNone() + k2.getSlashGiraNone() + k3.getSlashGiraNone();
    final int slashHyadoMag = k0.getSlashHyadoNone() + k1.getSlashHyadoNone() + k2.getSlashHyadoNone() + k3.getSlashHyadoNone();
    final int slashIoMag = k0.getSlashIoNone() + k1.getSlashIoNone() + k2.getSlashIoNone() + k3.getSlashIoNone();
    final int slashJibariaMag = k0.getSlashJibariaNone() + k1.getSlashJibariaNone() + k2.getSlashJibariaNone() + k3.getSlashJibariaNone();
    final int slashMeraMag = k0.getSlashMeraNone() + k1.getSlashMeraNone() + k2.getSlashMeraNone() + k3.getSlashMeraNone();

    final int hitBagiMag = k0.getHitBagiNone() + k1.getHitBagiNone() + k2.getHitBagiNone() + k3.getHitBagiNone();
    final int hitDeinMag = k0.getHitDeinNone() + k1.getHitDeinNone() + k2.getHitDeinNone() + k3.getHitDeinNone();
    final int hitDorumaMag = k0.getHitDorumaNone() + k1.getHitDorumaNone() + k2.getHitDorumaNone() + k3.getHitDorumaNone();
    final int hitGiraMag = k0.getHitGiraNone() + k1.getHitGiraNone() + k2.getHitGiraNone() + k3.getHitGiraNone();
    final int hitHyadoMag = k0.getHitHyadoNone() + k1.getHitHyadoNone() + k2.getHitHyadoNone() + k3.getHitHyadoNone();
    final int hitIoMag = k0.getHitIoNone() + k1.getHitIoNone() + k2.getHitIoNone() + k3.getHitIoNone();
    final int hitJibariaMag = k0.getHitJibariaNone() + k1.getHitJibariaNone() + k2.getHitJibariaNone() + k3.getHitJibariaNone();
    final int hitMeraMag = k0.getHitMeraNone() + k1.getHitMeraNone() + k2.getHitMeraNone() + k3.getHitMeraNone();

    final int spellBagiMag = k0.getSpellBagiNone() + k1.getSpellBagiNone() + k2.getSpellBagiNone() + k3.getSpellBagiNone();
    final int spellDeinMag = k0.getSpellDeinNone() + k1.getSpellDeinNone() + k2.getSpellDeinNone() + k3.getSpellDeinNone();
    final int spellDorumaMag = k0.getSpellDorumaNone() + k1.getSpellDorumaNone() + k2.getSpellDorumaNone() + k3.getSpellDorumaNone();
    final int spellGiraMag = k0.getSpellGiraNone() + k1.getSpellGiraNone() + k2.getSpellGiraNone() + k3.getSpellGiraNone();
    final int spellHyadoMag = k0.getSpellHyadoNone() + k1.getSpellHyadoNone() + k2.getSpellHyadoNone() + k3.getSpellHyadoNone();
    final int spellIoMag = k0.getSpellIoNone() + k1.getSpellIoNone() + k2.getSpellIoNone() + k3.getSpellIoNone();
    final int spellJibariaMag = k0.getSpellJibariaNone() + k1.getSpellJibariaNone() + k2.getSpellJibariaNone() + k3.getSpellJibariaNone();
    final int spellMeraMag = k0.getSpellMeraNone() + k1.getSpellMeraNone() + k2.getSpellMeraNone() + k3.getSpellMeraNone();

    final int breathBagiMag = k0.getBreathBagiNone() + k1.getBreathBagiNone() + k2.getBreathBagiNone() + k3.getBreathBagiNone();
    final int breathDeinMag = k0.getBreathDeinNone() + k1.getBreathDeinNone() + k2.getBreathDeinNone() + k3.getBreathDeinNone();
    final int breathDorumaMag = k0.getBreathDorumaNone() + k1.getBreathDorumaNone() + k2.getBreathDorumaNone() + k3.getBreathDorumaNone();
    final int breathGiraMag = k0.getBreathGiraNone() + k1.getBreathGiraNone() + k2.getBreathGiraNone() + k3.getBreathGiraNone();
    final int breathHyadoMag = k0.getBreathHyadoNone() + k1.getBreathHyadoNone() + k2.getBreathHyadoNone() + k3.getBreathHyadoNone();
    final int breathIoMag = k0.getBreathIoNone() + k1.getBreathIoNone() + k2.getBreathIoNone() + k3.getBreathIoNone();
    final int breathJibariaMag = k0.getBreathJibariaNone() + k1.getBreathJibariaNone() + k2.getBreathJibariaNone() + k3.getBreathJibariaNone();
    final int breathMeraMag = k0.getBreathMeraNone() + k1.getBreathMeraNone() + k2.getBreathMeraNone() + k3.getBreathMeraNone();

    final int bagiMag = k0.getAllBagiNone() + k1.getAllBagiNone() + k2.getAllBagiNone() + k3.getAllBagiNone();
    final int deinMag = k0.getAllDeinNone() + k1.getAllDeinNone() + k2.getAllDeinNone() + k3.getAllDeinNone();
    final int dorumaMag = k0.getAllDorumaNone() + k1.getAllDorumaNone() + k2.getAllDorumaNone() + k3.getAllDorumaNone();
    final int giraMag = k0.getAllGiraNone() + k1.getAllGiraNone() + k2.getAllGiraNone() + k3.getAllGiraNone();
    final int hyadoMag = k0.getAllHyadoNone() + k1.getAllHyadoNone() + k2.getAllHyadoNone() + k3.getAllHyadoNone();
    final int ioMag = k0.getAllIoNone() + k1.getAllIoNone() + k2.getAllIoNone() + k3.getAllIoNone();
    final int jibariaMag = k0.getAllJibariaNone() + k1.getAllJibariaNone() + k2.getAllJibariaNone() + k3.getAllJibariaNone();
    final int meraMag = k0.getAllMeraNone() + k1.getAllMeraNone() + k2.getAllMeraNone() + k3.getAllMeraNone();

    final int healingSkillMag = k0.getHealingSkill() + k1.getHealingSkill() + k2.getHealingSkill() + k3.getHealingSkill();
    final int healingSpecialtyMag = k0.getHealingSpecialty() + k1.getHealingSpecialty() + k2.getHealingSpecialty() + k3.getHealingSpecialty();
    final int healingSpellMag = k0.getHealingSpell() + k1.getHealingSpell() + k2.getHealingSpell() + k3.getHealingSpell();

    final int animalMag = k0.getAllNoneAnimal() + k1.getAllNoneAnimal() + k2.getAllNoneAnimal() + k3.getAllNoneAnimal();
    final int birdMag = k0.getAllNoneBird() + k1.getAllNoneBird() + k2.getAllNoneBird() + k3.getAllNoneBird();
    final int devilMag = k0.getAllNoneDevil() + k1.getAllNoneDevil() + k2.getAllNoneDevil() + k3.getAllNoneDevil();
    final int dragonMag = k0.getAllNoneDragon() + k1.getAllNoneDragon() + k2.getAllNoneDragon() + k3.getAllNoneDragon();
    final int elementMag = k0.getAllNoneElement() + k1.getAllNoneElement() + k2.getAllNoneElement() + k3.getAllNoneElement();
    final int insectMag = k0.getAllNoneInsect() + k1.getAllNoneInsect() + k2.getAllNoneInsect() + k3.getAllNoneInsect();
    final int machineMag = k0.getAllNoneMachine() + k1.getAllNoneMachine() + k2.getAllNoneMachine() + k3.getAllNoneMachine();
    final int materialMag = k0.getAllNoneMaterial() + k1.getAllNoneMaterial() + k2.getAllNoneMaterial() + k3.getAllNoneMaterial();
    final int phantomMag = k0.getAllNonePhantom() + k1.getAllNonePhantom() + k2.getAllNonePhantom() + k3.getAllNonePhantom();
    final int plantMag = k0.getAllNonePlant() + k1.getAllNonePlant() + k2.getAllNonePlant() + k3.getAllNonePlant();
    final int slimeMag = k0.getAllNoneSlime() + k1.getAllNoneSlime() + k2.getAllNoneSlime() + k3.getAllNoneSlime();
    final int waterMag = k0.getAllNoneWater() + k1.getAllNoneWater() + k2.getAllNoneWater() + k3.getAllNoneWater();
    final int zombieMag = k0.getAllNoneZombie() + k1.getAllNoneZombie() + k2.getAllNoneZombie() + k3.getAllNoneZombie();

    // Damage (Attribute)
    final int bagiSlashDamage = (int) ceil(maxOp * (1 + (slashMag / 100.0)) * (1 + (slashBagiMag / 100.0)) * (1 + (bagiMag / 100.0)));
    final int deinSlashDamage = (int) ceil(maxOp * (1 + (slashMag / 100.0)) * (1 + (slashDeinMag / 100.0)) * (1 + (deinMag / 100.0)));
    final int dorumaSlashDamage = (int) ceil(maxOp * (1 + (slashMag / 100.0)) * (1 + (slashDorumaMag / 100.0)) * (1 + (dorumaMag / 100.0)));
    final int giraSlashDamage = (int) ceil(maxOp * (1 + (slashMag / 100.0)) * (1 + (slashGiraMag / 100.0)) * (1 + (giraMag / 100.0)));
    final int hyadoSlashDamage = (int) ceil(maxOp * (1 + (slashMag / 100.0)) * (1 + (slashHyadoMag / 100.0)) * (1 + (hyadoMag / 100.0)));
    final int ioSlashDamage = (int) ceil(maxOp * (1 + (slashMag / 100.0)) * (1 + (slashIoMag / 100.0)) * (1 + (ioMag / 100.0)));
    final int jibariaSlashDamage = (int) ceil(maxOp * (1 + (slashMag / 100.0)) * (1 + (slashJibariaMag / 100.0)) * (1 + (jibariaMag / 100.0)));
    final int meraSlashDamage = (int) ceil(maxOp * (1 + (slashMag / 100.0)) * (1 + (slashMeraMag / 100.0)) * (1 + (meraMag / 100.0)));

    final int bagiHitDamage = (int) ceil(maxOp * (1 + (hitMag / 100.0)) * (1 + (hitBagiMag / 100.0)) * (1 + (bagiMag / 100.0)));
    final int deinHitDamage = (int) ceil(maxOp * (1 + (hitMag / 100.0)) * (1 + (hitDeinMag / 100.0)) * (1 + (deinMag / 100.0)));
    final int dorumaHitDamage = (int) ceil(maxOp * (1 + (hitMag / 100.0)) * (1 + (hitDorumaMag / 100.0)) * (1 + (dorumaMag / 100.0)));
    final int giraHitDamage = (int) ceil(maxOp * (1 + (hitMag / 100.0)) * (1 + (hitGiraMag / 100.0)) * (1 + (giraMag / 100.0)));
    final int hyadoHitDamage = (int) ceil(maxOp * (1 + (hitMag / 100.0)) * (1 + (hitHyadoMag / 100.0)) * (1 + (hyadoMag / 100.0)));
    final int ioHitDamage = (int) ceil(maxOp * (1 + (hitMag / 100.0)) * (1 + (hitIoMag / 100.0)) * (1 + (ioMag / 100.0)));
    final int jibariaHitDamage = (int) ceil(maxOp * (1 + (hitMag / 100.0)) * (1 + (hitJibariaMag / 100.0)) * (1 + (jibariaMag / 100.0)));
    final int meraHitDamage = (int) ceil(maxOp * (1 + (hitMag / 100.0)) * (1 + (hitMeraMag / 100.0)) * (1 + (meraMag / 100.0)));

    final int bagiSpellDamage = (int) ceil(maxOs * (1 + (spellMag / 100.0)) * (1 + (spellBagiMag / 100.0)) * (1 + (bagiMag / 100.0)));
    final int deinSpellDamage = (int) ceil(maxOs * (1 + (spellMag / 100.0)) * (1 + (spellDeinMag / 100.0)) * (1 + (deinMag / 100.0)));
    final int dorumaSpellDamage = (int) ceil(maxOs * (1 + (spellMag / 100.0)) * (1 + (spellDorumaMag / 100.0)) * (1 + (dorumaMag / 100.0)));
    final int giraSpellDamage = (int) ceil(maxOs * (1 + (spellMag / 100.0)) * (1 + (spellGiraMag / 100.0)) * (1 + (giraMag / 100.0)));
    final int hyadoSpellDamage = (int) ceil(maxOs * (1 + (spellMag / 100.0)) * (1 + (spellHyadoMag / 100.0)) * (1 + (hyadoMag / 100.0)));
    final int ioSpellDamage = (int) ceil(maxOs * (1 + (spellMag / 100.0)) * (1 + (spellIoMag / 100.0)) * (1 + (ioMag / 100.0)));
    final int jibariaSpellDamage = (int) ceil(maxOs * (1 + (spellMag / 100.0)) * (1 + (spellJibariaMag / 100.0)) * (1 + (jibariaMag / 100.0)));
    final int meraSpellDamage = (int) ceil(maxOs * (1 + (spellMag / 100.0)) * (1 + (spellMeraMag / 100.0)) * (1 + (meraMag / 100.0)));

    final int bagiSlashPhysicalSpellDamage = (int) ceil(maxOpOs * (1 + (slashMag / 100.0)) * (1 + (slashBagiMag / 100.0)) * (1 + (bagiMag / 100.0)));
    final int deinSlashPhysicalSpellDamage = (int) ceil(maxOpOs * (1 + (slashMag / 100.0)) * (1 + (slashDeinMag / 100.0)) * (1 + (deinMag / 100.0)));
    final int dorumaSlashPhysicalSpellDamage = (int) ceil(maxOpOs * (1 + (slashMag / 100.0)) * (1 + (slashDorumaMag / 100.0)) * (1 + (dorumaMag / 100.0)));
    final int giraSlashPhysicalSpellDamage = (int) ceil(maxOpOs * (1 + (slashMag / 100.0)) * (1 + (slashGiraMag / 100.0)) * (1 + (giraMag / 100.0)));
    final int hyadoSlashPhysicalSpellDamage = (int) ceil(maxOpOs * (1 + (slashMag / 100.0)) * (1 + (slashHyadoMag / 100.0)) * (1 + (hyadoMag / 100.0)));
    final int ioSlashPhysicalSpellDamage = (int) ceil(maxOpOs * (1 + (slashMag / 100.0)) * (1 + (slashIoMag / 100.0)) * (1 + (ioMag / 100.0)));
    final int jibariaSlashPhysicalSpellDamage = (int) ceil(maxOpOs * (1 + (slashMag / 100.0)) * (1 + (slashJibariaMag / 100.0)) * (1 + (jibariaMag / 100.0)));
    final int meraSlashPhysicalSpellDamage = (int) ceil(maxOpOs * (1 + (slashMag / 100.0)) * (1 + (slashMeraMag / 100.0)) * (1 + (meraMag / 100.0)));

    final int bagiHitPhysicalSpellDamage = (int) ceil(maxOpOs * (1 + (hitMag / 100.0)) * (1 + (hitBagiMag / 100.0)) * (1 + (bagiMag / 100.0)));
    final int deinHitPhysicalSpellDamage = (int) ceil(maxOpOs * (1 + (hitMag / 100.0)) * (1 + (hitDeinMag / 100.0)) * (1 + (deinMag / 100.0)));
    final int dorumaHitPhysicalSpellDamage = (int) ceil(maxOpOs * (1 + (hitMag / 100.0)) * (1 + (hitDorumaMag / 100.0)) * (1 + (dorumaMag / 100.0)));
    final int giraHitPhysicalSpellDamage = (int) ceil(maxOpOs * (1 + (hitMag / 100.0)) * (1 + (hitGiraMag / 100.0)) * (1 + (giraMag / 100.0)));
    final int hyadoHitPhysicalSpellDamage = (int) ceil(maxOpOs * (1 + (hitMag / 100.0)) * (1 + (hitHyadoMag / 100.0)) * (1 + (hyadoMag / 100.0)));
    final int ioHitPhysicalSpellDamage = (int) ceil(maxOpOs * (1 + (hitMag / 100.0)) * (1 + (hitIoMag / 100.0)) * (1 + (ioMag / 100.0)));
    final int jibariaHitPhysicalSpellDamage = (int) ceil(maxOpOs * (1 + (hitMag / 100.0)) * (1 + (hitJibariaMag / 100.0)) * (1 + (jibariaMag / 100.0)));
    final int meraHitPhysicalSpellDamage = (int) ceil(maxOpOs * (1 + (hitMag / 100.0)) * (1 + (hitMeraMag / 100.0)) * (1 + (meraMag / 100.0)));

    final int bagiBreathDamage = (int) ceil(maxOpDx * (1 + (breathMag / 100.0)) * (1 + (breathBagiMag / 100.0)) * (1 + (bagiMag / 100.0)));
    final int deinBreathDamage = (int) ceil(maxOpDx * (1 + (breathMag / 100.0)) * (1 + (breathDeinMag / 100.0)) * (1 + (deinMag / 100.0)));
    final int dorumaBreathDamage = (int) ceil(maxOpDx * (1 + (breathMag / 100.0)) * (1 + (breathDorumaMag / 100.0)) * (1 + (dorumaMag / 100.0)));
    final int giraBreathDamage = (int) ceil(maxOpDx * (1 + (breathMag / 100.0)) * (1 + (breathGiraMag / 100.0)) * (1 + (giraMag / 100.0)));
    final int hyadoBreathDamage = (int) ceil(maxOpDx * (1 + (breathMag / 100.0)) * (1 + (breathHyadoMag / 100.0)) * (1 + (hyadoMag / 100.0)));
    final int ioBreathDamage = (int) ceil(maxOpDx * (1 + (breathMag / 100.0)) * (1 + (breathIoMag / 100.0)) * (1 + (ioMag / 100.0)));
    final int jibariaBreathDamage = (int) ceil(maxOpDx * (1 + (breathMag / 100.0)) * (1 + (breathJibariaMag / 100.0)) * (1 + (jibariaMag / 100.0)));
    final int meraBreathDamage = (int) ceil(maxOpDx * (1 + (breathMag / 100.0)) * (1 + (breathMeraMag / 100.0)) * (1 + (meraMag / 100.0)));

    // Healing
    final int specialityHealing = (int) ceil(maxDs * (1 + (healingSkillMag / 100.0)) * (1 + (healingSpecialtyMag / 100.0)));
    final int spellHealing = (int) ceil(maxDs * (1 + (healingSkillMag / 100.0)) * (1 + (healingSpellMag / 100.0)));

    // CSV string
    final String patterns = maxOpPattern
        + "," + maxOsPattern
        + "," + maxOpOsPattern
        + "," + maxOpDxPattern
        + "," + maxDsPattern;

    final List<Integer> attributeDamages = Arrays.asList(
        bagiSlashDamage,
        deinSlashDamage,
        dorumaSlashDamage,
        giraSlashDamage,
        hyadoSlashDamage,
        ioSlashDamage,
        jibariaSlashDamage,
        meraSlashDamage,

        bagiHitDamage,
        deinHitDamage,
        dorumaHitDamage,
        giraHitDamage,
        hyadoHitDamage,
        ioHitDamage,
        jibariaHitDamage,
        meraHitDamage,

        bagiSpellDamage,
        deinSpellDamage,
        dorumaSpellDamage,
        giraSpellDamage,
        hyadoSpellDamage,
        ioSpellDamage,
        jibariaSpellDamage,
        meraSpellDamage,

        bagiSlashPhysicalSpellDamage,
        deinSlashPhysicalSpellDamage,
        dorumaSlashPhysicalSpellDamage,
        giraSlashPhysicalSpellDamage,
        hyadoSlashPhysicalSpellDamage,
        ioSlashPhysicalSpellDamage,
        jibariaSlashPhysicalSpellDamage,
        meraSlashPhysicalSpellDamage,

        bagiHitPhysicalSpellDamage,
        deinHitPhysicalSpellDamage,
        dorumaHitPhysicalSpellDamage,
        giraHitPhysicalSpellDamage,
        hyadoHitPhysicalSpellDamage,
        ioHitPhysicalSpellDamage,
        jibariaHitPhysicalSpellDamage,
        meraHitPhysicalSpellDamage,

        bagiBreathDamage,
        deinBreathDamage,
        dorumaBreathDamage,
        giraBreathDamage,
        hyadoBreathDamage,
        ioBreathDamage,
        jibariaBreathDamage,
        meraBreathDamage,

        specialityHealing,
        spellHealing
    );

    final List<Integer> raceMags = Arrays.asList(
        animalMag, birdMag, devilMag, dragonMag, elementMag, insectMag, machineMag,
        materialMag, phantomMag, plantMag, slimeMag, waterMag, zombieMag
    );

    if (csvType.equals(ATTRIBUTE)) {
      final StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(patterns);
      stringBuilder.append(",");
      for (final int integer : attributeDamages) {
        stringBuilder.append(integer);
        stringBuilder.append(",");
      }
      return stringBuilder.toString();
    } else if (csvType.equals(ATTRIBUTE_RACE)) {
      final StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(patterns);
      stringBuilder.append(",");
      for (final int integer : attributeDamages) {
        stringBuilder.append(integer);
        stringBuilder.append(",");
      }
      final List<Integer> subAttributeDamages =
          attributeDamages.subList(0, attributeDamages.size() - 2);
      for (final int raceMag : raceMags) {
        for (final int attributeDamage : subAttributeDamages) {
          int attributeRaceDamage = (int) ceil(attributeDamage * (1 + (raceMag / 100.0)));
          stringBuilder.append(attributeRaceDamage);
          stringBuilder.append(",");
        }
      }
      return stringBuilder.toString();
    } else {
      throw new IllegalArgumentException("");
    }
  }

  private int getCombinationSize() {
    final int len = kokoros.size();
    int count = 0;
    for (int i = 0; i < len; i++) {
      for (int j = 0; j < len; j++) {
        if (!(i >= j)) {
          for (int k = 0; k < len; k++) {
            if (!(i >= k) && !(j >= k)) {
              for (int l = 0; l < len; l++) {
                if (!(i >= l) && !(j >= l) && !(k >= l)) {
                  final int i0 = kokoros.get(i).getNumber();
                  final int i1 = kokoros.get(j).getNumber();
                  final int i2 = kokoros.get(k).getNumber();
                  final int i3 = kokoros.get(l).getNumber();
                  if (i0 != i1 && i0 != i2 && i0 != i3 && i1 != i2 && i1 != i3 && i2 != i3) {
                    count++;
                  }
                }
              }
            }
          }
        }
      }
    }
    return count;
  }

  @Async
  @Override
  public void createCombinationCsv(final CsvType csvType) {
    if (csvType.equals(ALL)) {
      createAttributeCsv();
      createAttributeRaceCsv();
    } else if (csvType.equals(ATTRIBUTE)) {
      createAttributeCsv();
    } else if (csvType.equals(ATTRIBUTE_RACE)) {
      createAttributeRaceCsv();
    } else {
      throw new IllegalArgumentException("Unknown CsvType: " + csvType);
    }
  }

  private void createAttributeCsv() {
    log.info("{} kokoros, {} combinations",
        kokoros.size(), String.format("%,d", getCombinationSize()));
    final StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    final String csvName = "combination.csv";
    try (final BufferedWriter bufferedWriter =
             Files.newBufferedWriter(Paths.get(csvName), StandardCharsets.UTF_8)
    ) {
      bufferedWriter.write(createAttributeCsvHeader());
      bufferedWriter.newLine();
      final int len = kokoros.size();
      log.info("{} kokoros", len);
      int count = 0;
      for (int i = 0; i < len; i++) {
        for (int j = 0; j < len; j++) {
          if (!(i >= j)) {
            for (int k = 0; k < len; k++) {
              if (!(i >= k) && !(j >= k)) {
                for (int l = 0; l < len; l++) {
                  if (!(i >= l) && !(j >= l) && !(k >= l)) {
                    final int i0 = kokoros.get(i).getNumber();
                    final int i1 = kokoros.get(j).getNumber();
                    final int i2 = kokoros.get(k).getNumber();
                    final int i3 = kokoros.get(l).getNumber();
                    if (i0 != i1 && i0 != i2 && i0 != i3 && i1 != i2 && i1 != i3 && i2 != i3) {
                      count++;

                      final KokoroFlat k0 = kokoros.get(i);
                      final KokoroFlat k1 = kokoros.get(j);
                      final KokoroFlat k2 = kokoros.get(k);
                      final KokoroFlat k3 = kokoros.get(l);

                      // Basis
                      final String names = k0.getName()
                          + "," + k1.getName()
                          + "," + k2.getName()
                          + "," + k3.getName();
                      final String numbers = k0.getNumber()
                          + "," + k1.getNumber()
                          + "," + k2.getNumber()
                          + "," + k3.getNumber();
                      final String grades = k0.getGrade().ordinal()
                          + "," + k1.getGrade().ordinal()
                          + "," + k2.getGrade().ordinal()
                          + "," + k3.getGrade().ordinal();
                      int totalCost = k0.getCost() + k1.getCost()
                          + k2.getCost() + k3.getCost()
                          - k0.getPlusCost() - k1.getPlusCost()
                          - k2.getPlusCost() - k3.getPlusCost();
                      final String numbersAndGrades = k0.getNumber() + "," + k0.getGrade().ordinal()
                          + "," + k1.getNumber() + "," + k1.getGrade().ordinal()
                          + "," + k2.getNumber() + "," + k2.getGrade().ordinal()
                          + "," + k3.getNumber() + "," + k3.getGrade().ordinal();

                      final String battleMaster = getCsv(
                          BATTLE_MASTER,
                          kokoros.get(i), kokoros.get(j), kokoros.get(k), kokoros.get(l),
                          ATTRIBUTE
                      );
                      final String sage = getCsv(
                          SAGE,
                          kokoros.get(i), kokoros.get(j), kokoros.get(k), kokoros.get(l),
                          ATTRIBUTE
                      );
                      final String ranger = getCsv(
                          RANGER,
                          kokoros.get(i), kokoros.get(j), kokoros.get(k), kokoros.get(l),
                          ATTRIBUTE
                      );
                      final String armamentalist = getCsv(
                          ARMAMENTALIST,
                          kokoros.get(i), kokoros.get(j), kokoros.get(k), kokoros.get(l),
                          ATTRIBUTE
                      );
                      final String paladin = getCsv(
                          PALADIN,
                          kokoros.get(i), kokoros.get(j), kokoros.get(k), kokoros.get(l),
                          ATTRIBUTE
                      );
                      final String superstar = getCsv(
                          SUPERSTAR,
                          kokoros.get(i), kokoros.get(j), kokoros.get(k), kokoros.get(l),
                          ATTRIBUTE
                      );
                      final String pirate = getCsv(
                          PIRATE,
                          kokoros.get(i), kokoros.get(j), kokoros.get(k), kokoros.get(l),
                          ATTRIBUTE
                      );

                      bufferedWriter.write(
                          numbersAndGrades
                              + ","
                              + battleMaster
                              + sage
                              + ranger
                              + armamentalist
                              + paladin
                              + superstar
                              + pirate
                              + totalCost
                      );
                      bufferedWriter.newLine();
                    }
                  }
                }
              }
            }
          }
        }
      }
      stopWatch.stop();
      log.info("{}: {} kokoros, {} combinations, {} ms",
          csvName,
          kokoros.size(),
          String.format("%,d", count),
          String.format("%,d", stopWatch.getLastTaskTimeMillis())
      );
    } catch (IOException ex) {
      throw new IllegalStateException("Failed to write combination.csv.", ex);
    }
  }

  private void createAttributeRaceCsv() {
    final List<JobType> jobs = Arrays.asList(
        BATTLE_MASTER, SAGE, RANGER, ARMAMENTALIST, PALADIN, SUPERSTAR, PIRATE
    );
    log.info("{} kokoros, {} combinations",
        kokoros.size(), String.format("%,d", getCombinationSize()));
    final StopWatch stopWatch = new StopWatch();
    for (JobType jobType : jobs) {
      final String csvName = "combination_" + jobType.name().toLowerCase() + ".csv";
      try {
        stopWatch.start();
        final BufferedWriter bufferedWriter =
            Files.newBufferedWriter(
                Paths.get(csvName),
                StandardCharsets.UTF_8
            );
        bufferedWriter.write(createAttributeRaceCsvHeader(jobType));
        bufferedWriter.newLine();
        final int len = kokoros.size();
        log.info("{} kokoros", len);
        int count = 0;
        for (int i = 0; i < len; i++) {
          for (int j = 0; j < len; j++) {
            if (!(i >= j)) {
              for (int k = 0; k < len; k++) {
                if (!(i >= k) && !(j >= k)) {
                  for (int l = 0; l < len; l++) {
                    if (!(i >= l) && !(j >= l) && !(k >= l)) {
                      final int i0 = kokoros.get(i).getNumber();
                      final int i1 = kokoros.get(j).getNumber();
                      final int i2 = kokoros.get(k).getNumber();
                      final int i3 = kokoros.get(l).getNumber();
                      if (i0 != i1 && i0 != i2 && i0 != i3 && i1 != i2 && i1 != i3 && i2 != i3) {
                        count++;

                        final KokoroFlat k0 = kokoros.get(i);
                        final KokoroFlat k1 = kokoros.get(j);
                        final KokoroFlat k2 = kokoros.get(k);
                        final KokoroFlat k3 = kokoros.get(l);

                        // Basis
                        final String names = k0.getName()
                            + "," + k1.getName()
                            + "," + k2.getName()
                            + "," + k3.getName();
                        final String numbers = k0.getNumber()
                            + "," + k1.getNumber()
                            + "," + k2.getNumber()
                            + "," + k3.getNumber();
                        final String grades = k0.getGrade().ordinal()
                            + "," + k1.getGrade().ordinal()
                            + "," + k2.getGrade().ordinal()
                            + "," + k3.getGrade().ordinal();
                        int totalCost = k0.getCost() + k1.getCost()
                            + k2.getCost() + k3.getCost()
                            - k0.getPlusCost() - k1.getPlusCost()
                            - k2.getPlusCost() - k3.getPlusCost();
                        final String numbersAndGrades = k0.getNumber() + "," + k0.getGrade().ordinal()
                            + "," + k1.getNumber() + "," + k1.getGrade().ordinal()
                            + "," + k2.getNumber() + "," + k2.getGrade().ordinal()
                            + "," + k3.getNumber() + "," + k3.getGrade().ordinal();

                        final String string = getCsv(
                            jobType,
                            kokoros.get(i),
                            kokoros.get(j),
                            kokoros.get(k),
                            kokoros.get(l),
                            ATTRIBUTE_RACE
                        );

                        bufferedWriter.write(
                            numbersAndGrades
                                + ","
                                + string
                                + totalCost
                        );
                        bufferedWriter.newLine();
                      }
                    }
                  }
                }
              }
            }
          }
        }
        bufferedWriter.close();
        stopWatch.stop();
        log.info("{}: {}, {} kokoros, {} combinations, {} ms",
            csvName,
            jobType.name(),
            kokoros.size(),
            String.format("%,d", count),
            String.format("%,d", stopWatch.getLastTaskTimeMillis())
        );
      } catch (IOException ex) {
        throw new IllegalStateException("Failed to write " + csvName + ".", ex);
      }
    }
  }

  private String createAttributeCsvHeader() {
    String header = "";
    final List<JobType> jobTypes = Arrays.asList(JobType.values());
    final List<AttributeType> attributeTypes = Arrays.asList(
        BAGI, DEIN, DORUMA, GIRA, HYADO, IO, JIBARIA, MERA
    );
    final List<AttackType> attackTypes = Arrays.asList(
        SLASH, HIT, SPELL, PHYSICS_SPELL_SLASH, PHYSICS_SPELL_HIT, BREATH
    );

    final String basis = "k0number,k0grade,k1number,k1grade,k2number,k2grade,k3number,k3grade,";
    header += basis;
    for (final JobType jobType : jobTypes) {
      final String pattern = "max_" + jobType.name().toLowerCase() + "_op_pattern,"
          + "max_" + jobType.name().toLowerCase() + "_os_pattern,"
          + "max_" + jobType.name().toLowerCase() + "_opos_pattern,"
          + "max_" + jobType.name().toLowerCase() + "_opdx_pattern,"
          + "max_" + jobType.name().toLowerCase() + "_ds_pattern,";
      header += pattern;
      for (final AttackType attackType : attackTypes) {
        for (final AttributeType attributeType : attributeTypes) {
          final String column = jobType.name().toLowerCase() + "_"
              + attributeType.name().toLowerCase() + "_"
              + attackType.name().toLowerCase() + "_damage,";
          header += column;
        }
      }
      header += jobType.name().toLowerCase() + "_speciality_healing,";
      header += jobType.name().toLowerCase() + "_spell_healing,";
    }
    final String suffix = "total_cost";
    header += suffix;
    return header;
  }

  private String createAttributeRaceCsvHeader(final JobType jobType) {
    String header = "";
    final List<RaceType> raceTypes = Arrays.asList(
        ANIMAL, BIRD, DEVIL, DRAGON, ELEMENT, INSECT, MACHINE,
        MATERIAL, PHANTOM, PLANT, SLIME, WATER, ZOMBIE
    );
    final List<AttributeType> attributeTypes = Arrays.asList(
        BAGI, DEIN, DORUMA, GIRA, HYADO, IO, JIBARIA, MERA
    );
    final List<AttackType> attackTypes = Arrays.asList(
        SLASH, HIT, SPELL, PHYSICS_SPELL_SLASH, PHYSICS_SPELL_HIT, BREATH
    );

    //
    final String basis = "k0number,k0grade,k1number,k1grade,k2number,k2grade,k3number,k3grade,";
    header += basis;
    final String pattern = "max_" + jobType.name().toLowerCase() + "_op_pattern,"
        + "max_" + jobType.name().toLowerCase() + "_os_pattern,"
        + "max_" + jobType.name().toLowerCase() + "_opos_pattern,"
        + "max_" + jobType.name().toLowerCase() + "_opdx_pattern,"
        + "max_" + jobType.name().toLowerCase() + "_ds_pattern,";
    header += pattern;
    for (final AttackType attackType : attackTypes) {
      for (final AttributeType attributeType : attributeTypes) {
        final String column = jobType.name().toLowerCase() + "_"
            + attributeType.name().toLowerCase() + "_"
            + attackType.name().toLowerCase() + "_damage,";
        header += column;
      }
    }
    header += jobType.name().toLowerCase() + "_speciality_healing,";
    header += jobType.name().toLowerCase() + "_spell_healing,";
    //

    for (final RaceType raceType : raceTypes) {
      for (final AttackType attackType : attackTypes) {
        for (final AttributeType attributeType : attributeTypes) {
          final String column = jobType.name().toLowerCase() + "_"
              + attributeType.name().toLowerCase() + "_"
              + attackType.name().toLowerCase() + "_"
              + raceType.name().toLowerCase() + "_damage,";
          header += column;
        }
      }
    }
    final String suffix = "total_cost";
    header += suffix;
    return header;
  }
}
