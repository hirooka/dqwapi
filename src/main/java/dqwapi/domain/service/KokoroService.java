package dqwapi.domain.service;

import static com.google.common.base.CaseFormat.LOWER_HYPHEN;
import static com.google.common.base.CaseFormat.UPPER_UNDERSCORE;
import static dqwapi.domain.model.common.KokoroType.BLUE;
import static dqwapi.domain.model.common.KokoroType.BLUE_GREEN;
import static dqwapi.domain.model.common.KokoroType.BLUE_PURPLE;
import static dqwapi.domain.model.common.KokoroType.GREEN;
import static dqwapi.domain.model.common.KokoroType.PURPLE;
import static dqwapi.domain.model.common.KokoroType.PURPLE_GREEN;
import static dqwapi.domain.model.common.KokoroType.RAINBOW;
import static dqwapi.domain.model.common.KokoroType.RED;
import static dqwapi.domain.model.common.KokoroType.RED_BLUE;
import static dqwapi.domain.model.common.KokoroType.RED_YELLOW;
import static dqwapi.domain.model.common.KokoroType.YELLOW;
import static dqwapi.domain.model.common.KokoroType.YELLOW_BLUE;
import static dqwapi.domain.model.common.KokoroType.YELLOW_GREEN;
import static dqwapi.domain.model.common.KokoroType.YELLOW_PURPLE;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dqwapi.domain.entity.CombinationEntity;
import dqwapi.domain.entity.DamageEntity;
import dqwapi.domain.entity.KokoroEntity;
import dqwapi.domain.entity.KokoroFlatEntity;
import dqwapi.domain.entity.Result;
import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.DwhType;
import dqwapi.domain.model.common.KokoroType;
import dqwapi.domain.model.common.Parameter;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.job.JobClassType;
import dqwapi.domain.model.job.JobParameter;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.Combination;
import dqwapi.domain.model.kokoro.Damage;
import dqwapi.domain.model.kokoro.Healing;
import dqwapi.domain.model.kokoro.JobKokoroCombination;
import dqwapi.domain.model.kokoro.Kokoro;
import dqwapi.domain.model.kokoro.KokoroFlat;
import dqwapi.domain.model.kokoro.GradeType;
import dqwapi.domain.model.kokoro.Slot;
import dqwapi.domain.model.kokoro.SuitableCombination;
import dqwapi.domain.repository.ICombinationRepository;
import dqwapi.domain.repository.IKokoroCombinationRepository;
import dqwapi.domain.repository.IKokoroFlatRepository;
import dqwapi.domain.repository.IKokoroObjectRepository;
import dqwapi.domain.repository.IKokoroRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Slf4j
@RequiredArgsConstructor
@Service
public class KokoroService implements IKokoroService {

  @Value("${spring.profiles.active}")
  private String activeProfile;

  @Value("${dqwapi.kokoro-json}")
  private String kokoroJson;

  @Value("${dqwapi.kokoro-flat-json}")
  private String kokoroFlatJson;

  private final ApplicationContext applicationContext;
  private final IKokoroRepository kokoroRepository;
  private final ICombinationRepository combinationRepository;
  private final IKokoroFlatRepository kokoroFlatRepository;
  private final IKokoroObjectRepository kokoroObjectRepository;

  private final String repositorySuffix = "-repository";

  private Map<JobType, List<KokoroType>> slotsByJob = new HashMap<>();
  private List<Kokoro> kokoros = new ArrayList<>();
  private final List<JobKokoroCombination> jobKokoroCombinations = new ArrayList<>();
  private List<KokoroFlat> kokoroFlats = new ArrayList<>();
  private Map<String, Object> combinationInfo = new HashMap<>();

  private SuitableCombination sortKokoro(final JobType jobType, final List<Kokoro> kokoros) {
    final double magnification = 1.2;
    int max = 0;
    final SuitableCombination suitableCombination = new SuitableCombination();
    switch (jobType) {
      case BATTLE_MASTER:
        for (int i = 0; i < 4; i++) {
          for (int j = 0; j < 4; j++) {
            if (i != j) {
              for (int k = 0; k < 4; k++) {
                if (i != k && j != k) {
                  for (int l = 0; l < 4; l++) {
                    if (i != l && j != l && k != l) {
                      int hp = 0;
                      int mp = 0;
                      int op = 0;
                      int dp = 0;
                      int os = 0;
                      int ds = 0;
                      int sp = 0;
                      int dx = 0;
                      if (kokoros.get(i).getType().equals(RED)) {
                        hp += (int) Math.ceil(kokoros.get(i).getHp() * magnification);
                        mp += (int) Math.ceil(kokoros.get(i).getMp() * magnification);
                        op += (int) Math.ceil(kokoros.get(i).getOp() * magnification);
                        dp += (int) Math.ceil(kokoros.get(i).getDp() * magnification);
                        os += (int) Math.ceil(kokoros.get(i).getOs() * magnification);
                        ds += (int) Math.ceil(kokoros.get(i).getDs() * magnification);
                        sp += (int) Math.ceil(kokoros.get(i).getSp() * magnification);
                        dx += (int) Math.ceil(kokoros.get(i).getDx() * magnification);
                      } else {
                        hp += kokoros.get(i).getHp();
                        mp += kokoros.get(i).getMp();
                        op += kokoros.get(i).getOp();
                        dp += kokoros.get(i).getDp();
                        os += kokoros.get(i).getOs();
                        ds += kokoros.get(i).getDs();
                        sp += kokoros.get(i).getSp();
                        dx += kokoros.get(i).getDx();
                      }
                      if (kokoros.get(j).getType().equals(RED)) {
                        hp += (int) Math.ceil(kokoros.get(j).getHp() * magnification);
                        mp += (int) Math.ceil(kokoros.get(j).getMp() * magnification);
                        op += (int) Math.ceil(kokoros.get(j).getOp() * magnification);
                        dp += (int) Math.ceil(kokoros.get(j).getDp() * magnification);
                        os += (int) Math.ceil(kokoros.get(j).getOs() * magnification);
                        ds += (int) Math.ceil(kokoros.get(j).getDs() * magnification);
                        sp += (int) Math.ceil(kokoros.get(j).getSp() * magnification);
                        dx += (int) Math.ceil(kokoros.get(j).getDx() * magnification);
                      } else {
                        hp += kokoros.get(j).getHp();
                        mp += kokoros.get(j).getMp();
                        op += kokoros.get(j).getOp();
                        dp += kokoros.get(j).getDp();
                        os += kokoros.get(j).getOs();
                        ds += kokoros.get(j).getDs();
                        sp += kokoros.get(j).getSp();
                        dx += kokoros.get(j).getDx();
                      }
                      if (kokoros.get(k).getType().equals(RED)
                          || kokoros.get(k).getType().equals(YELLOW)
                      ) {
                        hp += (int) Math.ceil(kokoros.get(k).getHp() * magnification);
                        mp += (int) Math.ceil(kokoros.get(k).getMp() * magnification);
                        op += (int) Math.ceil(kokoros.get(k).getOp() * magnification);
                        dp += (int) Math.ceil(kokoros.get(k).getDp() * magnification);
                        os += (int) Math.ceil(kokoros.get(k).getOs() * magnification);
                        ds += (int) Math.ceil(kokoros.get(k).getDs() * magnification);
                        sp += (int) Math.ceil(kokoros.get(k).getSp() * magnification);
                        dx += (int) Math.ceil(kokoros.get(k).getDx() * magnification);
                      } else {
                        hp += kokoros.get(k).getHp();
                        mp += kokoros.get(k).getMp();
                        op += kokoros.get(k).getOp();
                        dp += kokoros.get(k).getDp();
                        os += kokoros.get(k).getOs();
                        ds += kokoros.get(k).getDs();
                        sp += kokoros.get(k).getSp();
                        dx += kokoros.get(k).getDx();
                      }
                      hp += (int) Math.ceil(kokoros.get(l).getHp() * magnification);
                      mp += (int) Math.ceil(kokoros.get(l).getMp() * magnification);
                      op += (int) Math.ceil(kokoros.get(l).getOp() * magnification);
                      dp += (int) Math.ceil(kokoros.get(l).getDp() * magnification);
                      os += (int) Math.ceil(kokoros.get(l).getOs() * magnification);
                      ds += (int) Math.ceil(kokoros.get(l).getDs() * magnification);
                      sp += (int) Math.ceil(kokoros.get(l).getSp() * magnification);
                      dx += (int) Math.ceil(kokoros.get(l).getDx() * magnification);
                      if (op > max) {
                        max = op;
                        final Parameter parameter = new Parameter(hp, mp, op, dp, os, ds, sp, dx);
                        suitableCombination.setKokoros(
                            Arrays.asList(
                                kokoros.get(i), kokoros.get(j), kokoros.get(k), kokoros.get(l)
                            )
                        );
                        suitableCombination.setParameter(parameter);
                      }
                    }
                  }
                }
              }
            }
          }
        }
        return suitableCombination;
      case RANGER:
        for (int i = 0; i < 4; i++) {
          for (int j = 0; j < 4; j++) {
            if (i != j) {
              for (int k = 0; k < 4; k++) {
                if (i != k && j != k) {
                  for (int l = 0; l < 4; l++) {
                    if (i != l && j != l && k != l) {
                      int hp = 0;
                      int mp = 0;
                      int op = 0;
                      int dp = 0;
                      int os = 0;
                      int ds = 0;
                      int sp = 0;
                      int dx = 0;
                      if (kokoros.get(i).getType().equals(BLUE)) {
                        hp += (int) Math.ceil(kokoros.get(i).getHp());
                        mp += (int) Math.ceil(kokoros.get(i).getMp());
                        op += (int) Math.ceil(kokoros.get(i).getOp());
                        dp += (int) Math.ceil(kokoros.get(i).getDp());
                        os += (int) Math.ceil(kokoros.get(i).getOs());
                        ds += (int) Math.ceil(kokoros.get(i).getDs());
                        sp += (int) Math.ceil(kokoros.get(i).getSp());
                        dx += (int) Math.ceil(kokoros.get(i).getDx());
                      } else {
                        hp += kokoros.get(i).getHp();
                        mp += kokoros.get(i).getMp();
                        op += kokoros.get(i).getOp();
                        dp += kokoros.get(i).getDp();
                        os += kokoros.get(i).getOs();
                        ds += kokoros.get(i).getDs();
                        sp += kokoros.get(i).getSp();
                        dx += kokoros.get(i).getDx();
                      }
                      if (kokoros.get(j).getType().equals(BLUE)) {
                        hp += (int) Math.ceil(kokoros.get(j).getHp());
                        mp += (int) Math.ceil(kokoros.get(j).getMp());
                        op += (int) Math.ceil(kokoros.get(j).getOp());
                        dp += (int) Math.ceil(kokoros.get(j).getDp());
                        os += (int) Math.ceil(kokoros.get(j).getOs());
                        ds += (int) Math.ceil(kokoros.get(j).getDs());
                        sp += (int) Math.ceil(kokoros.get(j).getSp());
                        dx += (int) Math.ceil(kokoros.get(j).getDx());
                      } else {
                        hp += kokoros.get(j).getHp();
                        mp += kokoros.get(j).getMp();
                        op += kokoros.get(j).getOp();
                        dp += kokoros.get(j).getDp();
                        os += kokoros.get(j).getOs();
                        ds += kokoros.get(j).getDs();
                        sp += kokoros.get(j).getSp();
                        dx += kokoros.get(j).getDx();
                      }
                      if (kokoros.get(k).getType().equals(BLUE)
                          || kokoros.get(k).getType().equals(RED)
                      ) {
                        hp += (int) Math.ceil(kokoros.get(k).getHp());
                        mp += (int) Math.ceil(kokoros.get(k).getMp());
                        op += (int) Math.ceil(kokoros.get(k).getOp());
                        dp += (int) Math.ceil(kokoros.get(k).getDp());
                        os += (int) Math.ceil(kokoros.get(k).getOs());
                        ds += (int) Math.ceil(kokoros.get(k).getDs());
                        sp += (int) Math.ceil(kokoros.get(k).getSp());
                        dx += (int) Math.ceil(kokoros.get(k).getDx());
                      } else {
                        hp += kokoros.get(k).getHp();
                        mp += kokoros.get(k).getMp();
                        op += kokoros.get(k).getOp();
                        dp += kokoros.get(k).getDp();
                        os += kokoros.get(k).getOs();
                        ds += kokoros.get(k).getDs();
                        sp += kokoros.get(k).getSp();
                        dx += kokoros.get(k).getDx();
                      }
                      hp += (int) Math.ceil(kokoros.get(l).getHp());
                      mp += (int) Math.ceil(kokoros.get(l).getMp());
                      op += (int) Math.ceil(kokoros.get(l).getOp());
                      dp += (int) Math.ceil(kokoros.get(l).getDp());
                      os += (int) Math.ceil(kokoros.get(l).getOs());
                      ds += (int) Math.ceil(kokoros.get(l).getDs());
                      sp += (int) Math.ceil(kokoros.get(l).getSp());
                      dx += (int) Math.ceil(kokoros.get(l).getDx());
                      if (op > max) {
                        max = op;
                        final Parameter parameter = new Parameter(hp, mp, op, dp, os, ds, sp, dx);
                        suitableCombination.setKokoros(
                            Arrays.asList(
                                kokoros.get(i), kokoros.get(j), kokoros.get(k), kokoros.get(l)
                            )
                        );
                        suitableCombination.setParameter(parameter);
                      }
                    }
                  }
                }
              }
            }
          }
        }
        return suitableCombination;
      case SAGE:
        for (int i = 0; i < 4; i++) {
          for (int j = 0; j < 4; j++) {
            if (i != j) {
              for (int k = 0; k < 4; k++) {
                if (i != k && j != k) {
                  for (int l = 0; l < 4; l++) {
                    if (i != l && j != l && k != l) {
                      int hp = 0;
                      int mp = 0;
                      int op = 0;
                      int dp = 0;
                      int os = 0;
                      int ds = 0;
                      int sp = 0;
                      int dx = 0;
                      if (kokoros.get(i).getType().equals(PURPLE)
                          || kokoros.get(i).getType().equals(GREEN)
                      ) {
                        hp += (int) Math.ceil(kokoros.get(i).getHp());
                        mp += (int) Math.ceil(kokoros.get(i).getMp());
                        op += (int) Math.ceil(kokoros.get(i).getOp());
                        dp += (int) Math.ceil(kokoros.get(i).getDp());
                        os += (int) Math.ceil(kokoros.get(i).getOs());
                        ds += (int) Math.ceil(kokoros.get(i).getDs());
                        sp += (int) Math.ceil(kokoros.get(i).getSp());
                        dx += (int) Math.ceil(kokoros.get(i).getDx());
                      } else {
                        hp += kokoros.get(i).getHp();
                        mp += kokoros.get(i).getMp();
                        op += kokoros.get(i).getOp();
                        dp += kokoros.get(i).getDp();
                        os += kokoros.get(i).getOs();
                        ds += kokoros.get(i).getDs();
                        sp += kokoros.get(i).getSp();
                        dx += kokoros.get(i).getDx();
                      }
                      if (kokoros.get(j).getType().equals(PURPLE)
                          || kokoros.get(j).getType().equals(GREEN)
                      ) {
                        hp += (int) Math.ceil(kokoros.get(j).getHp());
                        mp += (int) Math.ceil(kokoros.get(j).getMp());
                        op += (int) Math.ceil(kokoros.get(j).getOp());
                        dp += (int) Math.ceil(kokoros.get(j).getDp());
                        os += (int) Math.ceil(kokoros.get(j).getOs());
                        ds += (int) Math.ceil(kokoros.get(j).getDs());
                        sp += (int) Math.ceil(kokoros.get(j).getSp());
                        dx += (int) Math.ceil(kokoros.get(j).getDx());
                      } else {
                        hp += kokoros.get(j).getHp();
                        mp += kokoros.get(j).getMp();
                        op += kokoros.get(j).getOp();
                        dp += kokoros.get(j).getDp();
                        os += kokoros.get(j).getOs();
                        ds += kokoros.get(j).getDs();
                        sp += kokoros.get(j).getSp();
                        dx += kokoros.get(j).getDx();
                      }
                      if (kokoros.get(k).getType().equals(PURPLE)
                          || kokoros.get(k).getType().equals(GREEN)
                      ) {
                        hp += (int) Math.ceil(kokoros.get(k).getHp());
                        mp += (int) Math.ceil(kokoros.get(k).getMp());
                        op += (int) Math.ceil(kokoros.get(k).getOp());
                        dp += (int) Math.ceil(kokoros.get(k).getDp());
                        os += (int) Math.ceil(kokoros.get(k).getOs());
                        ds += (int) Math.ceil(kokoros.get(k).getDs());
                        sp += (int) Math.ceil(kokoros.get(k).getSp());
                        dx += (int) Math.ceil(kokoros.get(k).getDx());
                      } else {
                        hp += kokoros.get(k).getHp();
                        mp += kokoros.get(k).getMp();
                        op += kokoros.get(k).getOp();
                        dp += kokoros.get(k).getDp();
                        os += kokoros.get(k).getOs();
                        ds += kokoros.get(k).getDs();
                        sp += kokoros.get(k).getSp();
                        dx += kokoros.get(k).getDx();
                      }
                      hp += (int) Math.ceil(kokoros.get(l).getHp());
                      mp += (int) Math.ceil(kokoros.get(l).getMp());
                      op += (int) Math.ceil(kokoros.get(l).getOp());
                      dp += (int) Math.ceil(kokoros.get(l).getDp());
                      os += (int) Math.ceil(kokoros.get(l).getOs());
                      ds += (int) Math.ceil(kokoros.get(l).getDs());
                      sp += (int) Math.ceil(kokoros.get(l).getSp());
                      dx += (int) Math.ceil(kokoros.get(l).getDx());
                      if (op > max) {
                        max = op;
                        final Parameter parameter = new Parameter(hp, mp, op, dp, os, ds, sp, dx);
                        suitableCombination.setKokoros(
                            Arrays.asList(
                                kokoros.get(i), kokoros.get(j), kokoros.get(k), kokoros.get(l)
                            )
                        );
                        suitableCombination.setParameter(parameter);
                      }
                    }
                  }
                }
              }
            }
          }
        }
        return suitableCombination;
      case PALADIN:
        for (int i = 0; i < 4; i++) {
          for (int j = 0; j < 4; j++) {
            if (i != j) {
              for (int k = 0; k < 4; k++) {
                if (i != k && j != k) {
                  for (int l = 0; l < 4; l++) {
                    if (i != l && j != l && k != l) {
                      int hp = 0;
                      int mp = 0;
                      int op = 0;
                      int dp = 0;
                      int os = 0;
                      int ds = 0;
                      int sp = 0;
                      int dx = 0;
                      if (kokoros.get(i).getType().equals(YELLOW)) {
                        hp += (int) Math.ceil(kokoros.get(i).getHp());
                        mp += (int) Math.ceil(kokoros.get(i).getMp());
                        op += (int) Math.ceil(kokoros.get(i).getOp());
                        dp += (int) Math.ceil(kokoros.get(i).getDp());
                        os += (int) Math.ceil(kokoros.get(i).getOs());
                        ds += (int) Math.ceil(kokoros.get(i).getDs());
                        sp += (int) Math.ceil(kokoros.get(i).getSp());
                        dx += (int) Math.ceil(kokoros.get(i).getDx());
                      } else {
                        hp += kokoros.get(i).getHp();
                        mp += kokoros.get(i).getMp();
                        op += kokoros.get(i).getOp();
                        dp += kokoros.get(i).getDp();
                        os += kokoros.get(i).getOs();
                        ds += kokoros.get(i).getDs();
                        sp += kokoros.get(i).getSp();
                        dx += kokoros.get(i).getDx();
                      }
                      if (kokoros.get(j).getType().equals(YELLOW)) {
                        hp += (int) Math.ceil(kokoros.get(j).getHp());
                        mp += (int) Math.ceil(kokoros.get(j).getMp());
                        op += (int) Math.ceil(kokoros.get(j).getOp());
                        dp += (int) Math.ceil(kokoros.get(j).getDp());
                        os += (int) Math.ceil(kokoros.get(j).getOs());
                        ds += (int) Math.ceil(kokoros.get(j).getDs());
                        sp += (int) Math.ceil(kokoros.get(j).getSp());
                        dx += (int) Math.ceil(kokoros.get(j).getDx());
                      } else {
                        hp += kokoros.get(j).getHp();
                        mp += kokoros.get(j).getMp();
                        op += kokoros.get(j).getOp();
                        dp += kokoros.get(j).getDp();
                        os += kokoros.get(j).getOs();
                        ds += kokoros.get(j).getDs();
                        sp += kokoros.get(j).getSp();
                        dx += kokoros.get(j).getDx();
                      }
                      if (kokoros.get(k).getType().equals(YELLOW)
                          || kokoros.get(k).getType().equals(GREEN)
                      ) {
                        hp += (int) Math.ceil(kokoros.get(k).getHp());
                        mp += (int) Math.ceil(kokoros.get(k).getMp());
                        op += (int) Math.ceil(kokoros.get(k).getOp());
                        dp += (int) Math.ceil(kokoros.get(k).getDp());
                        os += (int) Math.ceil(kokoros.get(k).getOs());
                        ds += (int) Math.ceil(kokoros.get(k).getDs());
                        sp += (int) Math.ceil(kokoros.get(k).getSp());
                        dx += (int) Math.ceil(kokoros.get(k).getDx());
                      } else {
                        hp += kokoros.get(k).getHp();
                        mp += kokoros.get(k).getMp();
                        op += kokoros.get(k).getOp();
                        dp += kokoros.get(k).getDp();
                        os += kokoros.get(k).getOs();
                        ds += kokoros.get(k).getDs();
                        sp += kokoros.get(k).getSp();
                        dx += kokoros.get(k).getDx();
                      }
                      hp += (int) Math.ceil(kokoros.get(l).getHp());
                      mp += (int) Math.ceil(kokoros.get(l).getMp());
                      op += (int) Math.ceil(kokoros.get(l).getOp());
                      dp += (int) Math.ceil(kokoros.get(l).getDp());
                      os += (int) Math.ceil(kokoros.get(l).getOs());
                      ds += (int) Math.ceil(kokoros.get(l).getDs());
                      sp += (int) Math.ceil(kokoros.get(l).getSp());
                      dx += (int) Math.ceil(kokoros.get(l).getDx());
                      if (op > max) {
                        max = op;
                        final Parameter parameter = new Parameter(hp, mp, op, dp, os, ds, sp, dx);
                        suitableCombination.setKokoros(
                            Arrays.asList(
                                kokoros.get(i), kokoros.get(j), kokoros.get(k), kokoros.get(l)
                            )
                        );
                        suitableCombination.setParameter(parameter);
                      }
                    }
                  }
                }
              }
            }
          }
        }
        return suitableCombination;
      default:
    }
    return suitableCombination;
  }

  boolean canBrideSkip(final int i0, final int i1, final int i2, final int i3) {
    final List<Integer> list = Arrays.asList(i0, i1, i2, i3);
    if (list.contains(50_001)) {
      if (list.contains(50_002) || list.contains(50_003)) {
        return true;
      }
    }
    if (list.contains(50_002)) {
      if (list.contains(50_001) || list.contains(50_003)) {
        return true;
      }
    }
    if (list.contains(50_003)) {
      return list.contains(50_001) || list.contains(50_002);
    }
    return false;
  }

  private List<Combination> createCombinations(final JobType jobType) {

    final List<Combination> combinations = new ArrayList<>();
    final int len = kokoros.size();
    final StopWatch stopWatch = new StopWatch();
    stopWatch.start("createCombinations-" + jobType.name());

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
                  if (i0 != i1 && i0 != i2 && i0 != i3 && i1 != i2 && i1 != i3 && i2 != i3
                      && !canBrideSkip(i0, i1, i2, i3)
                  ) {
                    log.debug("{}, {}, {}, {}", i, j, k, l);
                    log.debug("org: {} {} {} {}",
                        kokoros.get(i).getName(),
                        kokoros.get(j).getName(),
                        kokoros.get(k).getName(),
                        kokoros.get(l).getName()
                    );
                    final SuitableCombination suitableCombination = sortKokoro(
                        jobType,
                        Arrays.asList(
                            kokoros.get(i), kokoros.get(j), kokoros.get(k), kokoros.get(l)
                        )
                    );
                    log.debug("suit: {} {} {} {}",
                        suitableCombination.getKokoros().get(0).getName(),
                        suitableCombination.getKokoros().get(1).getName(),
                        suitableCombination.getKokoros().get(2).getName(),
                        suitableCombination.getKokoros().get(3).getName()
                    );
                    final List<KokoroType> kokoroTypes = slotsByJob.get(jobType);
                    final Slot slot0 = new Slot();
                    slot0.setType(kokoroTypes.get(0));
                    slot0.setKokoro(suitableCombination.getKokoros().get(0));
                    final Slot slot1 = new Slot();
                    slot1.setType(kokoroTypes.get(1));
                    slot1.setKokoro(suitableCombination.getKokoros().get(1));
                    final Slot slot2 = new Slot();
                    slot2.setType(kokoroTypes.get(2));
                    slot2.setKokoro(suitableCombination.getKokoros().get(2));
                    final Slot slot3 = new Slot();
                    slot3.setType(kokoroTypes.get(3));
                    slot3.setKokoro(suitableCombination.getKokoros().get(3));
                    final List<Slot> slots = Arrays.asList(slot0, slot1, slot2, slot3);
                    final Combination combination = new Combination();
                    combination.setSlots(slots);
                    //combination.setParameter(suitableCombination.getParameter());

                    combination.setHp(suitableCombination.getParameter().getHp());
                    combination.setMp(suitableCombination.getParameter().getMp());
                    combination.setOp(suitableCombination.getParameter().getOp());
                    combination.setDp(suitableCombination.getParameter().getDp());
                    combination.setOs(suitableCombination.getParameter().getOs());
                    combination.setOp(suitableCombination.getParameter().getOp());
                    combination.setSp(suitableCombination.getParameter().getSp());
                    combination.setDx(suitableCombination.getParameter().getDx());
                    final int cost = suitableCombination.getKokoros().get(0).getCost()
                        - suitableCombination.getKokoros().get(0).getPlusCost()
                        + suitableCombination.getKokoros().get(1).getCost()
                        - suitableCombination.getKokoros().get(1).getPlusCost()
                        + suitableCombination.getKokoros().get(2).getCost()
                        - suitableCombination.getKokoros().get(2).getPlusCost()
                        + suitableCombination.getKokoros().get(3).getCost()
                        - suitableCombination.getKokoros().get(3).getPlusCost();
                    combination.setCost(cost);

                    // TODO: from -- Processing time and memory consumption
                    final List<Damage> damages = new ArrayList<>();
                    for (Slot slot : slots) {
                      damages.addAll(slot.getKokoro().getDamages());
                    }
                    final List<Damage> mergedDamages = new ArrayList<>();
                    for (int x = 0; x < damages.size(); x++) {
                      boolean isMerged = false;
                      int mergedMagnification = damages.get(x).getMagnification();
                      for (int y = 0; y < damages.size(); y++) {
                        if (damages.get(x).getAttack()
                            .equals(damages.get(y).getAttack())
                            && damages.get(x).getAttribute()
                            .equals(damages.get(y).getAttribute())
                            && damages.get(x).getRace()
                            .equals(damages.get(y).getRace())
                        ) {
                          if (y > x) {
                            mergedMagnification += damages.get(y).getMagnification();
                          } else if (y < x) {
                            isMerged = true;
                          }
                        }
                      }
                      if (!isMerged) {
                        final Damage mergedDamage = new Damage();
                        mergedDamage.setAttack(damages.get(x).getAttack());
                        mergedDamage.setAttribute(damages.get(x).getAttribute());
                        mergedDamage.setRace(damages.get(x).getRace());
                        mergedDamage.setMagnification(mergedMagnification);
                        mergedDamages.add(mergedDamage);
                      }
                    }
                    // TODO: to -- Processing time and memory consumption
                    log.debug(mergedDamages.toString());
                    combination.setDamages(mergedDamages);

                    combinations.add(combination);
                  }
                }
              }
            }
          }
        }
      }
    }
    stopWatch.stop();
    log.info("{}: {} kokoro combinations, {} ms",
        jobType, combinations.size(), String.format("%,d", stopWatch.getLastTaskTimeMillis())
    );
    return combinations;
  }

  @PostConstruct
  void init() {
    final Resource kokoroJsonResource = new ClassPathResource(kokoroJson);
    final String jobSlotJson = "slots-by-job.json";
    final Resource jobSlotJsonResource = new ClassPathResource(jobSlotJson);
    final Resource kokoroFlatJsonResource = new ClassPathResource(kokoroFlatJson);
    try {
      final StopWatch stopWatch = new StopWatch();
      stopWatch.start("slotsByJob");
      final ObjectMapper objectMapper = new ObjectMapper();
      slotsByJob =
          objectMapper.readValue(jobSlotJsonResource.getInputStream(), new TypeReference<>() {});
      stopWatch.stop();
      log.info("{} jobs, {} ms",
          slotsByJob.size(), String.format("%,d", stopWatch.getLastTaskTimeMillis())
      );
      log.debug(slotsByJob.toString());

      stopWatch.start("kokoros");
      kokoros =
          objectMapper.readValue(kokoroJsonResource.getInputStream(), new TypeReference<>() {});
      stopWatch.stop();
      log.info("{} kokoros, {} ms",
          kokoros.size(), String.format("%,d", stopWatch.getLastTaskTimeMillis())
      );
      log.debug(kokoros.toString());

//      stopWatch.start("kokoroFlats");
//      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//      kokoroFlats =
//          objectMapper.readValue(kokoroFlatJsonResource.getInputStream(), new TypeReference<>() {});
//      stopWatch.stop();
//      log.info("{} kokoroFlats, {} ms",
//          kokoroFlats.size(), String.format("%,d", stopWatch.getLastTaskTimeMillis())
//      );
//      log.debug(kokoroFlats.toString());

      // TODO: initialized on memory (too many memory usage)
      /*
      stopWatch.start("initialize");
      for (JobType jobType : slotsByJob.keySet()) {
        final List<Combination> combinations = createCombinations(jobType);
        final JobKokoroCombination jobKokoroCombination = new JobKokoroCombination();
        jobKokoroCombination.setJob(jobType);
        jobKokoroCombination.setCombinations(combinations);
        jobKokoroCombinations.add(jobKokoroCombination);
      }
      stopWatch.stop();
      log.info("initialize: {} ms", String.format("%,d", stopWatch.getLastTaskTimeMillis()));
      */

      final int len = kokoros.size();
      int combination = 0;
      for (int i = 0; i < len; i++) {
        for (int j = 0; j < len; j++) {
          if (!(i >= j)) {
            for (int k = 0; k < len; k++) {
              if (!(i >= k) && !(j >= k)) {
                for (int l = 0; l < len; l++) {
                  if (!(i >= l) && !(j >= l) && !(k >= l)) {
                    combination++;
                  }
                }
              }
            }
          }
        }
      }
      final int digit = Integer.toString(combination).split("").length;
      final double basis = Math.pow(10, digit - 2);
      final int floored  = (int) (Math.floor(combination / basis) * basis);
      final String combinationString = String.format("%,d", floored);
      combinationInfo = Map.of("kokoro", len,"combination", combinationString);

      kokoroObjectRepository.save(kokoroJson);

    } catch (IOException ex) {
      throw new IllegalStateException("Failed to parse JSON file.", ex);
    }
  }

  @Override
  public List<Kokoro> getAll() {
    return kokoros;
  }

  @Override
  public List<Map<String, String>> get() {
    final List<Map<String, String>> list = new ArrayList<>();
    for (Kokoro kokoro : kokoros) {
      final Map<String, String> map = new HashMap<>();
      map.put("text", kokoro.getName() + " " + kokoro.getGrade().name());
      map.put("value", kokoro.getNumber() + "" + kokoro.getGrade().name().toLowerCase());
      list.add(map);
    }
    return list;
  }

  @Override
  public Map<String, Object> getCombinationInfo() {
    return combinationInfo;
  }

  @Override
  public List<Map<String, String>> getJobs() {
    // TODO:
    final List<Map<String, String>> list = new ArrayList<>();
    slotsByJob.forEach((k, v) -> {
      final Map<String, String> map = new HashMap<>();
      final String text;
      switch (k) {
        case BATTLE_MASTER:
          text = "バトルマスター";
          break;
        case SAGE:
          text = "賢者";
          break;
        case RANGER:
          text = "レンジャー";
          break;
        case ARMAMENTALIST:
          text = "魔法戦士";
          break;
        case PALADIN:
          text = "パラディン";
          break;
        case SUPERSTAR:
          text = "スーパースター";
          break;
        case PIRATE:
          text = "海賊";
          break;
        default:
          throw new IllegalArgumentException("");
      }
      map.put("text", text);
      map.put("value", k.name());
      list.add(map);
    });
    log.info(list.toString());
    return list;
  }

  @Override
  public List<Combination> getCombinationsOnMemory(final JobType jobType) {
    for (JobKokoroCombination jobKokoroCombination : jobKokoroCombinations) {
      if (jobKokoroCombination.getJob().equals(jobType)) {
        return jobKokoroCombination.getCombinations();
      }
    }
    return new ArrayList<>();
  }

  @Override
  public List<Combination> getCombinationsOnMemory(
      final JobType jobType,
      final int cost,
      final String bride,
      final Map<Integer, List<GradeType>> exclusions
  ) {
    final int brideId;
    switch (bride) {
      case "ビアンカ":
        brideId = 50_001;
        break;
      case "フローラ":
        brideId = 50_002;
        break;
      case "デボラ":
        brideId = 50_003;
        break;
      default:
        throw new IllegalArgumentException("Illegal Argument: set correct bride name.");
    }
    final int magicN = 10_000;
    for (JobKokoroCombination jobKokoroCombination : jobKokoroCombinations) {
      if (jobKokoroCombination.getJob().equals(jobType)) {
        return jobKokoroCombination.getCombinations().stream()
            .filter(combination -> combination.getCost() <= cost)
            .filter(combination -> {
              final List<Integer> ids = combination.getSlots().stream()
                  .map(slot -> slot.getKokoro().getNumber())
                  .collect(Collectors.toList());
              if (brideId == 50_001) {
                return !ids.contains(50_002) && !ids.contains(50_003);
              }
              if (brideId == 50_002) {
                return !ids.contains(50_001) && !ids.contains(50_003);
              }
              return !ids.contains(50_001) && !ids.contains(50_002);
            })
            .filter(combination -> {
              for (Slot slot : combination.getSlots()) {
                if (exclusions.containsKey(slot.getKokoro().getNumber())) {
                  if (exclusions.get(slot.getKokoro().getNumber())
                      .contains(slot.getKokoro().getGrade())
                  ) {
                    return false;
                  }
                }
              }
              return true;
            })
            .sorted(Comparator.comparingInt(Combination::getOp).reversed())
            .collect(Collectors.toList()).subList(0, magicN);
      }
    }
    return new ArrayList<>();
  }

  private Kokoro get(final int number, final GradeType gradeType) {
    for (Kokoro kokoro : kokoros) {
      if (kokoro.getNumber() == number && kokoro.getGrade().equals(gradeType)) {
        return kokoro;
      }
    }
    throw new IllegalArgumentException("");
  }

  private void setParameter(final Combination combination, final JobType jobType, final JobClassType jobClassType) {
    final double magnification;
    if (jobClassType.equals(JobClassType.ADVANCED)) {
      magnification = 1.2;
    } else if (jobClassType.equals(JobClassType.SPECIAL)) {
      magnification = 1.3;
    } else {
      magnification = 1.2;
    }
    int hp = 0;
    int mp = 0;
    int op = 0;
    int dp = 0;
    int os = 0;
    int ds = 0;
    int sp = 0;
    int dx = 0;
    switch (jobType) {
      case BATTLE_MASTER:
        if (combination.getSlots().get(0).getKokoro().getType().equals(RED) || combination.getSlots().get(0).getKokoro().getType().equals(RAINBOW)) {
          hp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(0).getKokoro().getHp();
          mp += combination.getSlots().get(0).getKokoro().getMp();
          op += combination.getSlots().get(0).getKokoro().getOp();
          dp += combination.getSlots().get(0).getKokoro().getDp();
          os += combination.getSlots().get(0).getKokoro().getOs();
          ds += combination.getSlots().get(0).getKokoro().getDs();
          sp += combination.getSlots().get(0).getKokoro().getSp();
          dx += combination.getSlots().get(0).getKokoro().getDx();
        }
        if (combination.getSlots().get(1).getKokoro().getType().equals(RED) || combination.getSlots().get(1).getKokoro().getType().equals(RAINBOW)) {
          hp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(1).getKokoro().getHp();
          mp += combination.getSlots().get(1).getKokoro().getMp();
          op += combination.getSlots().get(1).getKokoro().getOp();
          dp += combination.getSlots().get(1).getKokoro().getDp();
          os += combination.getSlots().get(1).getKokoro().getOs();
          ds += combination.getSlots().get(1).getKokoro().getDs();
          sp += combination.getSlots().get(1).getKokoro().getSp();
          dx += combination.getSlots().get(1).getKokoro().getDx();
        }
        if (combination.getSlots().get(2).getKokoro().getType().equals(RED)
            || combination.getSlots().get(2).getKokoro().getType().equals(YELLOW)
            || combination.getSlots().get(2).getKokoro().getType().equals(RAINBOW)
        ) {
          hp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(2).getKokoro().getHp();
          mp += combination.getSlots().get(2).getKokoro().getMp();
          op += combination.getSlots().get(2).getKokoro().getOp();
          dp += combination.getSlots().get(2).getKokoro().getDp();
          os += combination.getSlots().get(2).getKokoro().getOs();
          ds += combination.getSlots().get(2).getKokoro().getDs();
          sp += combination.getSlots().get(2).getKokoro().getSp();
          dx += combination.getSlots().get(2).getKokoro().getDx();
        }
        break;
      case SAGE:
        if (combination.getSlots().get(0).getKokoro().getType().equals(PURPLE)
            || combination.getSlots().get(0).getKokoro().getType().equals(GREEN)
            || combination.getSlots().get(0).getKokoro().getType().equals(RAINBOW)
        ) {
          hp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(0).getKokoro().getHp();
          mp += combination.getSlots().get(0).getKokoro().getMp();
          op += combination.getSlots().get(0).getKokoro().getOp();
          dp += combination.getSlots().get(0).getKokoro().getDp();
          os += combination.getSlots().get(0).getKokoro().getOs();
          ds += combination.getSlots().get(0).getKokoro().getDs();
          sp += combination.getSlots().get(0).getKokoro().getSp();
          dx += combination.getSlots().get(0).getKokoro().getDx();
        }
        if (combination.getSlots().get(1).getKokoro().getType().equals(PURPLE)
            || combination.getSlots().get(1).getKokoro().getType().equals(GREEN)
            || combination.getSlots().get(1).getKokoro().getType().equals(RAINBOW)
        ) {
          hp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(1).getKokoro().getHp();
          mp += combination.getSlots().get(1).getKokoro().getMp();
          op += combination.getSlots().get(1).getKokoro().getOp();
          dp += combination.getSlots().get(1).getKokoro().getDp();
          os += combination.getSlots().get(1).getKokoro().getOs();
          ds += combination.getSlots().get(1).getKokoro().getDs();
          sp += combination.getSlots().get(1).getKokoro().getSp();
          dx += combination.getSlots().get(1).getKokoro().getDx();
        }
        if (combination.getSlots().get(2).getKokoro().getType().equals(PURPLE)
            || combination.getSlots().get(2).getKokoro().getType().equals(GREEN)
            || combination.getSlots().get(2).getKokoro().getType().equals(RAINBOW)
        ) {
          hp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(2).getKokoro().getHp();
          mp += combination.getSlots().get(2).getKokoro().getMp();
          op += combination.getSlots().get(2).getKokoro().getOp();
          dp += combination.getSlots().get(2).getKokoro().getDp();
          os += combination.getSlots().get(2).getKokoro().getOs();
          ds += combination.getSlots().get(2).getKokoro().getDs();
          sp += combination.getSlots().get(2).getKokoro().getSp();
          dx += combination.getSlots().get(2).getKokoro().getDx();
        }
        break;
      case RANGER:
        if (combination.getSlots().get(0).getKokoro().getType().equals(BLUE) || combination.getSlots().get(0).getKokoro().getType().equals(RAINBOW)) {
          hp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(0).getKokoro().getHp();
          mp += combination.getSlots().get(0).getKokoro().getMp();
          op += combination.getSlots().get(0).getKokoro().getOp();
          dp += combination.getSlots().get(0).getKokoro().getDp();
          os += combination.getSlots().get(0).getKokoro().getOs();
          ds += combination.getSlots().get(0).getKokoro().getDs();
          sp += combination.getSlots().get(0).getKokoro().getSp();
          dx += combination.getSlots().get(0).getKokoro().getDx();
        }
        if (combination.getSlots().get(1).getKokoro().getType().equals(BLUE) || combination.getSlots().get(1).getKokoro().getType().equals(RAINBOW)) {
          hp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(1).getKokoro().getHp();
          mp += combination.getSlots().get(1).getKokoro().getMp();
          op += combination.getSlots().get(1).getKokoro().getOp();
          dp += combination.getSlots().get(1).getKokoro().getDp();
          os += combination.getSlots().get(1).getKokoro().getOs();
          ds += combination.getSlots().get(1).getKokoro().getDs();
          sp += combination.getSlots().get(1).getKokoro().getSp();
          dx += combination.getSlots().get(1).getKokoro().getDx();
        }
        if (combination.getSlots().get(2).getKokoro().getType().equals(RED)
            || combination.getSlots().get(2).getKokoro().getType().equals(BLUE)
            || combination.getSlots().get(2).getKokoro().getType().equals(RAINBOW)
        ) {
          hp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(2).getKokoro().getHp();
          mp += combination.getSlots().get(2).getKokoro().getMp();
          op += combination.getSlots().get(2).getKokoro().getOp();
          dp += combination.getSlots().get(2).getKokoro().getDp();
          os += combination.getSlots().get(2).getKokoro().getOs();
          ds += combination.getSlots().get(2).getKokoro().getDs();
          sp += combination.getSlots().get(2).getKokoro().getSp();
          dx += combination.getSlots().get(2).getKokoro().getDx();
        }
        break;
      case ARMAMENTALIST:
        if (combination.getSlots().get(0).getKokoro().getType().equals(YELLOW)
            || combination.getSlots().get(0).getKokoro().getType().equals(PURPLE) || combination.getSlots().get(0).getKokoro().getType().equals(RAINBOW)
        ) {
          hp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(0).getKokoro().getHp();
          mp += combination.getSlots().get(0).getKokoro().getMp();
          op += combination.getSlots().get(0).getKokoro().getOp();
          dp += combination.getSlots().get(0).getKokoro().getDp();
          os += combination.getSlots().get(0).getKokoro().getOs();
          ds += combination.getSlots().get(0).getKokoro().getDs();
          sp += combination.getSlots().get(0).getKokoro().getSp();
          dx += combination.getSlots().get(0).getKokoro().getDx();
        }
        if (combination.getSlots().get(1).getKokoro().getType().equals(YELLOW)
            || combination.getSlots().get(1).getKokoro().getType().equals(PURPLE) || combination.getSlots().get(1).getKokoro().getType().equals(RAINBOW)
        ) {
          hp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(1).getKokoro().getHp();
          mp += combination.getSlots().get(1).getKokoro().getMp();
          op += combination.getSlots().get(1).getKokoro().getOp();
          dp += combination.getSlots().get(1).getKokoro().getDp();
          os += combination.getSlots().get(1).getKokoro().getOs();
          ds += combination.getSlots().get(1).getKokoro().getDs();
          sp += combination.getSlots().get(1).getKokoro().getSp();
          dx += combination.getSlots().get(1).getKokoro().getDx();
        }
        if (combination.getSlots().get(2).getKokoro().getType().equals(YELLOW)
            || combination.getSlots().get(2).getKokoro().getType().equals(PURPLE) || combination.getSlots().get(2).getKokoro().getType().equals(RAINBOW)
        ) {
          hp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(2).getKokoro().getHp();
          mp += combination.getSlots().get(2).getKokoro().getMp();
          op += combination.getSlots().get(2).getKokoro().getOp();
          dp += combination.getSlots().get(2).getKokoro().getDp();
          os += combination.getSlots().get(2).getKokoro().getOs();
          ds += combination.getSlots().get(2).getKokoro().getDs();
          sp += combination.getSlots().get(2).getKokoro().getSp();
          dx += combination.getSlots().get(2).getKokoro().getDx();
        }
        break;
      case PALADIN:
        if (combination.getSlots().get(0).getKokoro().getType().equals(YELLOW)
            || combination.getSlots().get(0).getKokoro().getType().equals(GREEN) || combination.getSlots().get(0).getKokoro().getType().equals(RAINBOW)
        ) {
          hp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(0).getKokoro().getHp();
          mp += combination.getSlots().get(0).getKokoro().getMp();
          op += combination.getSlots().get(0).getKokoro().getOp();
          dp += combination.getSlots().get(0).getKokoro().getDp();
          os += combination.getSlots().get(0).getKokoro().getOs();
          ds += combination.getSlots().get(0).getKokoro().getDs();
          sp += combination.getSlots().get(0).getKokoro().getSp();
          dx += combination.getSlots().get(0).getKokoro().getDx();
        }
        if (combination.getSlots().get(1).getKokoro().getType().equals(YELLOW)
            || combination.getSlots().get(1).getKokoro().getType().equals(GREEN) || combination.getSlots().get(1).getKokoro().getType().equals(RAINBOW)
        ) {
          hp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(1).getKokoro().getHp();
          mp += combination.getSlots().get(1).getKokoro().getMp();
          op += combination.getSlots().get(1).getKokoro().getOp();
          dp += combination.getSlots().get(1).getKokoro().getDp();
          os += combination.getSlots().get(1).getKokoro().getOs();
          ds += combination.getSlots().get(1).getKokoro().getDs();
          sp += combination.getSlots().get(1).getKokoro().getSp();
          dx += combination.getSlots().get(1).getKokoro().getDx();
        }
        if (combination.getSlots().get(2).getKokoro().getType().equals(YELLOW)
            || combination.getSlots().get(2).getKokoro().getType().equals(GREEN) || combination.getSlots().get(2).getKokoro().getType().equals(RAINBOW)
        ) {
          hp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(2).getKokoro().getHp();
          mp += combination.getSlots().get(2).getKokoro().getMp();
          op += combination.getSlots().get(2).getKokoro().getOp();
          dp += combination.getSlots().get(2).getKokoro().getDp();
          os += combination.getSlots().get(2).getKokoro().getOs();
          ds += combination.getSlots().get(2).getKokoro().getDs();
          sp += combination.getSlots().get(2).getKokoro().getSp();
          dx += combination.getSlots().get(2).getKokoro().getDx();
        }
        break;
      case SUPERSTAR:
        if (combination.getSlots().get(0).getKokoro().getType().equals(BLUE) || combination.getSlots().get(0).getKokoro().getType().equals(RAINBOW)) {
          hp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(0).getKokoro().getHp();
          mp += combination.getSlots().get(0).getKokoro().getMp();
          op += combination.getSlots().get(0).getKokoro().getOp();
          dp += combination.getSlots().get(0).getKokoro().getDp();
          os += combination.getSlots().get(0).getKokoro().getOs();
          ds += combination.getSlots().get(0).getKokoro().getDs();
          sp += combination.getSlots().get(0).getKokoro().getSp();
          dx += combination.getSlots().get(0).getKokoro().getDx();
        }
        if (combination.getSlots().get(1).getKokoro().getType().equals(GREEN) || combination.getSlots().get(1).getKokoro().getType().equals(RAINBOW)) {
          hp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(1).getKokoro().getHp();
          mp += combination.getSlots().get(1).getKokoro().getMp();
          op += combination.getSlots().get(1).getKokoro().getOp();
          dp += combination.getSlots().get(1).getKokoro().getDp();
          os += combination.getSlots().get(1).getKokoro().getOs();
          ds += combination.getSlots().get(1).getKokoro().getDs();
          sp += combination.getSlots().get(1).getKokoro().getSp();
          dx += combination.getSlots().get(1).getKokoro().getDx();
        }
        if (combination.getSlots().get(2).getKokoro().getType().equals(BLUE)
            || combination.getSlots().get(2).getKokoro().getType().equals(GREEN) || combination.getSlots().get(2).getKokoro().getType().equals(RAINBOW)
        ) {
          hp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(2).getKokoro().getHp();
          mp += combination.getSlots().get(2).getKokoro().getMp();
          op += combination.getSlots().get(2).getKokoro().getOp();
          dp += combination.getSlots().get(2).getKokoro().getDp();
          os += combination.getSlots().get(2).getKokoro().getOs();
          ds += combination.getSlots().get(2).getKokoro().getDs();
          sp += combination.getSlots().get(2).getKokoro().getSp();
          dx += combination.getSlots().get(2).getKokoro().getDx();
        }
        break;
      case PIRATE:
        if (combination.getSlots().get(0).getKokoro().getType().equals(YELLOW) || combination.getSlots().get(0).getKokoro().getType().equals(RAINBOW)) {
          hp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(0).getKokoro().getHp();
          mp += combination.getSlots().get(0).getKokoro().getMp();
          op += combination.getSlots().get(0).getKokoro().getOp();
          dp += combination.getSlots().get(0).getKokoro().getDp();
          os += combination.getSlots().get(0).getKokoro().getOs();
          ds += combination.getSlots().get(0).getKokoro().getDs();
          sp += combination.getSlots().get(0).getKokoro().getSp();
          dx += combination.getSlots().get(0).getKokoro().getDx();
        }
        if (combination.getSlots().get(1).getKokoro().getType().equals(BLUE) || combination.getSlots().get(1).getKokoro().getType().equals(RAINBOW)) {
          hp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(1).getKokoro().getHp();
          mp += combination.getSlots().get(1).getKokoro().getMp();
          op += combination.getSlots().get(1).getKokoro().getOp();
          dp += combination.getSlots().get(1).getKokoro().getDp();
          os += combination.getSlots().get(1).getKokoro().getOs();
          ds += combination.getSlots().get(1).getKokoro().getDs();
          sp += combination.getSlots().get(1).getKokoro().getSp();
          dx += combination.getSlots().get(1).getKokoro().getDx();
        }
        if (combination.getSlots().get(2).getKokoro().getType().equals(YELLOW)
            || combination.getSlots().get(2).getKokoro().getType().equals(BLUE) || combination.getSlots().get(2).getKokoro().getType().equals(RAINBOW)
        ) {
          hp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(2).getKokoro().getHp();
          mp += combination.getSlots().get(2).getKokoro().getMp();
          op += combination.getSlots().get(2).getKokoro().getOp();
          dp += combination.getSlots().get(2).getKokoro().getDp();
          os += combination.getSlots().get(2).getKokoro().getOs();
          ds += combination.getSlots().get(2).getKokoro().getDs();
          sp += combination.getSlots().get(2).getKokoro().getSp();
          dx += combination.getSlots().get(2).getKokoro().getDx();
        }
        break;
      case MONSTER_MASTER:
        if (combination.getSlots().get(0).getKokoro().getType().equals(BLUE)
                || combination.getSlots().get(0).getKokoro().getType().equals(PURPLE) || combination.getSlots().get(0).getKokoro().getType().equals(RAINBOW)
        ) {
          hp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(0).getKokoro().getHp();
          mp += combination.getSlots().get(0).getKokoro().getMp();
          op += combination.getSlots().get(0).getKokoro().getOp();
          dp += combination.getSlots().get(0).getKokoro().getDp();
          os += combination.getSlots().get(0).getKokoro().getOs();
          ds += combination.getSlots().get(0).getKokoro().getDs();
          sp += combination.getSlots().get(0).getKokoro().getSp();
          dx += combination.getSlots().get(0).getKokoro().getDx();
        }
        if (combination.getSlots().get(1).getKokoro().getType().equals(BLUE)
                || combination.getSlots().get(1).getKokoro().getType().equals(PURPLE) || combination.getSlots().get(1).getKokoro().getType().equals(RAINBOW)
        ) {
          hp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(1).getKokoro().getHp();
          mp += combination.getSlots().get(1).getKokoro().getMp();
          op += combination.getSlots().get(1).getKokoro().getOp();
          dp += combination.getSlots().get(1).getKokoro().getDp();
          os += combination.getSlots().get(1).getKokoro().getOs();
          ds += combination.getSlots().get(1).getKokoro().getDs();
          sp += combination.getSlots().get(1).getKokoro().getSp();
          dx += combination.getSlots().get(1).getKokoro().getDx();
        }
        hp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getHp() * magnification);
        mp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getMp() * magnification);
        op += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getOp() * magnification);
        dp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDp() * magnification);
        os += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getOs() * magnification);
        ds += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDs() * magnification);
        sp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getSp() * magnification);
        dx += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDx() * magnification);
        break;
      case GOD_HAND:
        if (combination.getSlots().get(0).getKokoro().getType().equals(RED) || combination.getSlots().get(0).getKokoro().getType().equals(RAINBOW)) {
          hp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(0).getKokoro().getHp();
          mp += combination.getSlots().get(0).getKokoro().getMp();
          op += combination.getSlots().get(0).getKokoro().getOp();
          dp += combination.getSlots().get(0).getKokoro().getDp();
          os += combination.getSlots().get(0).getKokoro().getOs();
          ds += combination.getSlots().get(0).getKokoro().getDs();
          sp += combination.getSlots().get(0).getKokoro().getSp();
          dx += combination.getSlots().get(0).getKokoro().getDx();
        }
        if (combination.getSlots().get(1).getKokoro().getType().equals(YELLOW) || combination.getSlots().get(1).getKokoro().getType().equals(RAINBOW)) {
          hp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(1).getKokoro().getHp();
          mp += combination.getSlots().get(1).getKokoro().getMp();
          op += combination.getSlots().get(1).getKokoro().getOp();
          dp += combination.getSlots().get(1).getKokoro().getDp();
          os += combination.getSlots().get(1).getKokoro().getOs();
          ds += combination.getSlots().get(1).getKokoro().getDs();
          sp += combination.getSlots().get(1).getKokoro().getSp();
          dx += combination.getSlots().get(1).getKokoro().getDx();
        }
        if (combination.getSlots().get(2).getKokoro().getType().equals(RED)
                || combination.getSlots().get(2).getKokoro().getType().equals(YELLOW)
                || combination.getSlots().get(2).getKokoro().getType().equals(RAINBOW)
        ) {
          hp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(2).getKokoro().getHp();
          mp += combination.getSlots().get(2).getKokoro().getMp();
          op += combination.getSlots().get(2).getKokoro().getOp();
          dp += combination.getSlots().get(2).getKokoro().getDp();
          os += combination.getSlots().get(2).getKokoro().getOs();
          ds += combination.getSlots().get(2).getKokoro().getDs();
          sp += combination.getSlots().get(2).getKokoro().getSp();
          dx += combination.getSlots().get(2).getKokoro().getDx();
        }
        break;
      case DAIMADOUSHI:
        if (combination.getSlots().get(0).getKokoro().getType().equals(YELLOW)
                || combination.getSlots().get(0).getKokoro().getType().equals(PURPLE)
                || combination.getSlots().get(0).getKokoro().getType().equals(RAINBOW)
        ) {
          hp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(0).getKokoro().getHp();
          mp += combination.getSlots().get(0).getKokoro().getMp();
          op += combination.getSlots().get(0).getKokoro().getOp();
          dp += combination.getSlots().get(0).getKokoro().getDp();
          os += combination.getSlots().get(0).getKokoro().getOs();
          ds += combination.getSlots().get(0).getKokoro().getDs();
          sp += combination.getSlots().get(0).getKokoro().getSp();
          dx += combination.getSlots().get(0).getKokoro().getDx();
        }
        if (combination.getSlots().get(1).getKokoro().getType().equals(PURPLE)
                || combination.getSlots().get(1).getKokoro().getType().equals(RAINBOW)
        ) {
          hp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(1).getKokoro().getHp();
          mp += combination.getSlots().get(1).getKokoro().getMp();
          op += combination.getSlots().get(1).getKokoro().getOp();
          dp += combination.getSlots().get(1).getKokoro().getDp();
          os += combination.getSlots().get(1).getKokoro().getOs();
          ds += combination.getSlots().get(1).getKokoro().getDs();
          sp += combination.getSlots().get(1).getKokoro().getSp();
          dx += combination.getSlots().get(1).getKokoro().getDx();
        }
        if (combination.getSlots().get(2).getKokoro().getType().equals(YELLOW)
                || combination.getSlots().get(2).getKokoro().getType().equals(PURPLE)
                || combination.getSlots().get(2).getKokoro().getType().equals(RAINBOW)
        ) {
          hp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(2).getKokoro().getHp();
          mp += combination.getSlots().get(2).getKokoro().getMp();
          op += combination.getSlots().get(2).getKokoro().getOp();
          dp += combination.getSlots().get(2).getKokoro().getDp();
          os += combination.getSlots().get(2).getKokoro().getOs();
          ds += combination.getSlots().get(2).getKokoro().getDs();
          sp += combination.getSlots().get(2).getKokoro().getSp();
          dx += combination.getSlots().get(2).getKokoro().getDx();
        }
        break;
      default:
        throw new IllegalArgumentException("");
    }
    hp += (int) Math.ceil(combination.getSlots().get(3).getKokoro().getHp() * magnification);
    mp += (int) Math.ceil(combination.getSlots().get(3).getKokoro().getMp() * magnification);
    op += (int) Math.ceil(combination.getSlots().get(3).getKokoro().getOp() * magnification);
    dp += (int) Math.ceil(combination.getSlots().get(3).getKokoro().getDp() * magnification);
    os += (int) Math.ceil(combination.getSlots().get(3).getKokoro().getOs() * magnification);
    ds += (int) Math.ceil(combination.getSlots().get(3).getKokoro().getDs() * magnification);
    sp += (int) Math.ceil(combination.getSlots().get(3).getKokoro().getSp() * magnification);
    dx += (int) Math.ceil(combination.getSlots().get(3).getKokoro().getDx() * magnification);

    combination.setHp(hp);
    combination.setMp(mp);
    combination.setOp(op);
    combination.setDp(dp);
    combination.setOs(os);
    combination.setDs(ds);
    combination.setDx(dx);
    combination.setSp(sp);
  }

  private List<Combination> convert(final List<Result> results, final JobType jobType, final JobClassType jobClassType) {
    final List<Combination> combinations = new ArrayList<>();
    for (Result result : results) {
      final String pattern = result.getPattern();
      final int k0Index = Integer.parseInt(pattern.substring(1, 2));
      final int k1Index = Integer.parseInt(pattern.substring(2, 3));
      final int k2Index = Integer.parseInt(pattern.substring(3, 4));
      final int k3Index = Integer.parseInt(pattern.substring(4, 5));
      log.debug("{}, {}, {}, {}", k0Index, k1Index, k2Index, k3Index);
      List<Integer> indexes = Arrays.asList(k0Index, k1Index, k2Index, k3Index);
      List<Integer> ids = Arrays.asList(
          result.getK0id(), result.getK1id(), result.getK2id(), result.getK3id()
      );
      List<GradeType> grades = Arrays.asList(
          result.getK0grade(), result.getK1grade(), result.getK2grade(), result.getK3grade()
      );
      final Combination combination = new Combination();
      final List<Slot> slots = new ArrayList<>();
      for (int i = 0; i < 4; i++) {
        final Slot slot = new Slot();
        slot.setKokoro(get(ids.get(indexes.get(i)), grades.get(indexes.get(i))));
        switch (jobType) {
          case BATTLE_MASTER:
            switch (i) {
              case 0:
              case 1:
                slot.setType(RED);
                if (slot.getKokoro().getType().equals(RED) || slot.getKokoro().getType().equals(RAINBOW)) {
                  slot.setUp(true);
                }
                break;
              case 2:
                slot.setType(RED_YELLOW);
                if (slot.getKokoro().getType().equals(RED)
                    || slot.getKokoro().getType().equals(YELLOW) || slot.getKokoro().getType().equals(RAINBOW)
                ) {
                  slot.setUp(true);
                }
                break;
              case 3:
                slot.setType(RAINBOW);
                slot.setUp(true);
                break;
              default:
                throw new IllegalArgumentException("");
            }
            break;
          case SAGE:
            switch (i) {
              case 0:
              case 1:
              case 2:
                slot.setType(PURPLE_GREEN);
                if (slot.getKokoro().getType().equals(PURPLE)
                    || slot.getKokoro().getType().equals(GREEN) || slot.getKokoro().getType().equals(RAINBOW)
                ) {
                  slot.setUp(true);
                }
                break;
              case 3:
                slot.setType(RAINBOW);
                slot.setUp(true);
                break;
              default:
                throw new IllegalArgumentException("");
            }
            break;
          case RANGER:
            switch (i) {
              case 0:
              case 1:
                slot.setType(BLUE);
                if (slot.getKokoro().getType().equals(BLUE) || slot.getKokoro().getType().equals(RAINBOW)) {
                  slot.setUp(true);
                }
                break;
              case 2:
                slot.setType(RED_BLUE);
                if (slot.getKokoro().getType().equals(RED)
                    || slot.getKokoro().getType().equals(BLUE) || slot.getKokoro().getType().equals(RAINBOW)
                ) {
                  slot.setUp(true);
                }
                break;
              case 3:
                slot.setType(RAINBOW);
                slot.setUp(true);
                break;
              default:
                throw new IllegalArgumentException("");
            }
            break;
          case ARMAMENTALIST:
            switch (i) {
              case 0:
              case 1:
              case 2:
                slot.setType(YELLOW_PURPLE);
                if (slot.getKokoro().getType().equals(YELLOW)
                    || slot.getKokoro().getType().equals(PURPLE) || slot.getKokoro().getType().equals(RAINBOW)
                ) {
                  slot.setUp(true);
                }
                break;
              case 3:
                slot.setType(RAINBOW);
                slot.setUp(true);
                break;
              default:
                throw new IllegalArgumentException("");
            }
            break;
          case PALADIN:
            switch (i) {
              case 0:
              case 1:
              case 2:
                slot.setType(YELLOW_GREEN);
                if (slot.getKokoro().getType().equals(YELLOW)
                    || slot.getKokoro().getType().equals(GREEN) || slot.getKokoro().getType().equals(RAINBOW)
                ) {
                  slot.setUp(true);
                }
                break;
              case 3:
                slot.setType(RAINBOW);
                slot.setUp(true);
                break;
              default:
                throw new IllegalArgumentException("");
            }
            break;
          case SUPERSTAR:
            switch (i) {
              case 0:
                slot.setType(BLUE);
                if (slot.getKokoro().getType().equals(BLUE) || slot.getKokoro().getType().equals(RAINBOW)) {
                  slot.setUp(true);
                }
                break;
              case 1:
                slot.setType(GREEN);
                if (slot.getKokoro().getType().equals(GREEN) || slot.getKokoro().getType().equals(RAINBOW)) {
                  slot.setUp(true);
                }
                break;
              case 2:
                slot.setType(BLUE_GREEN);
                if (slot.getKokoro().getType().equals(BLUE)
                    || slot.getKokoro().getType().equals(GREEN) || slot.getKokoro().getType().equals(RAINBOW)
                ) {
                  slot.setUp(true);
                }
                break;
              case 3:
                slot.setType(RAINBOW);
                slot.setUp(true);
                break;
              default:
                throw new IllegalArgumentException("");
            }
            break;
          case PIRATE:
            switch (i) {
              case 0:
                slot.setType(YELLOW);
                if (slot.getKokoro().getType().equals(YELLOW) || slot.getKokoro().getType().equals(RAINBOW)) {
                  slot.setUp(true);
                }
                break;
              case 1:
                slot.setType(BLUE);
                if (slot.getKokoro().getType().equals(BLUE) || slot.getKokoro().getType().equals(RAINBOW)) {
                  slot.setUp(true);
                }
                break;
              case 2:
                slot.setType(YELLOW_BLUE);
                if (slot.getKokoro().getType().equals(YELLOW)
                    || slot.getKokoro().getType().equals(BLUE) || slot.getKokoro().getType().equals(RAINBOW)
                ) {
                  slot.setUp(true);
                }
                break;
              case 3:
                slot.setType(RAINBOW);
                slot.setUp(true);
                break;
              default:
                throw new IllegalArgumentException("");
            }
            break;
          case MONSTER_MASTER:
            switch (i) {
              case 0:
              case 1:
                slot.setType(BLUE_PURPLE);
                if (slot.getKokoro().getType().equals(BLUE)
                        || slot.getKokoro().getType().equals(PURPLE) || slot.getKokoro().getType().equals(RAINBOW)
                ) {
                  slot.setUp(true);
                }
                break;
              case 2:
              case 3:
                slot.setType(RAINBOW);
                slot.setUp(true);
                break;
              default:
                throw new IllegalArgumentException("");
            }
            break;
          case GOD_HAND:
            switch (i) {
              case 0:
                slot.setType(RED);
                if (slot.getKokoro().getType().equals(RED) || slot.getKokoro().getType().equals(RAINBOW)) {
                  slot.setUp(true);
                }
                break;
              case 1:
                slot.setType(YELLOW);
                if (slot.getKokoro().getType().equals(YELLOW) || slot.getKokoro().getType().equals(RAINBOW)) {
                  slot.setUp(true);
                }
                break;
              case 2:
                slot.setType(RED_YELLOW);
                if (slot.getKokoro().getType().equals(RED)
                        || slot.getKokoro().getType().equals(YELLOW)
                        || slot.getKokoro().getType().equals(RAINBOW)
                ) {
                  slot.setUp(true);
                }
                break;
              case 3:
                slot.setType(RAINBOW);
                slot.setUp(true);
                break;
              default:
                throw new IllegalArgumentException("");
            }
            break;
          case DAIMADOUSHI:
            switch (i) {
              case 0:
                slot.setType(YELLOW_PURPLE);
                if (slot.getKokoro().getType().equals(YELLOW)
                        || slot.getKokoro().getType().equals(PURPLE)
                        || slot.getKokoro().getType().equals(RAINBOW)
                ) {
                  slot.setUp(true);
                }
                break;
              case 1:
                slot.setType(PURPLE);
                if (slot.getKokoro().getType().equals(PURPLE)
                        || slot.getKokoro().getType().equals(RAINBOW)
                ) {
                  slot.setUp(true);
                }
                break;
              case 2:
                slot.setType(YELLOW_GREEN);
                if (slot.getKokoro().getType().equals(YELLOW)
                        || slot.getKokoro().getType().equals(PURPLE)
                        || slot.getKokoro().getType().equals(RAINBOW)
                ) {
                  slot.setUp(true);
                }
                break;
              case 3:
                slot.setType(RAINBOW);
                slot.setUp(true);
                break;
              default:
                throw new IllegalArgumentException("");
            }
            break;
          default:
            throw new IllegalArgumentException("");
        }
        slots.add(slot);
      }
      combination.setSlots(slots);

      setParameter(combination, jobType, jobClassType);

      final int cost = combination.getSlots().get(0).getKokoro().getCost()
          + combination.getSlots().get(1).getKokoro().getCost()
          + combination.getSlots().get(2).getKokoro().getCost()
          + combination.getSlots().get(3).getKokoro().getCost();
      combination.setCost(cost);

      final List<Damage> damages = new ArrayList<>();
      for (Slot slot : slots) {
        damages.addAll(slot.getKokoro().getDamages());
      }
      final List<Damage> mergedDamages = new ArrayList<>();
      for (int x = 0; x < damages.size(); x++) {
        boolean isMerged = false;
        int mergedMagnification = damages.get(x).getMagnification();
        for (int y = 0; y < damages.size(); y++) {
          if (damages.get(x).getAttack()
              .equals(damages.get(y).getAttack())
              && damages.get(x).getAttribute()
              .equals(damages.get(y).getAttribute())
              && damages.get(x).getRace()
              .equals(damages.get(y).getRace())
          ) {
            if (y > x) {
              mergedMagnification += damages.get(y).getMagnification();
            } else if (y < x) {
              isMerged = true;
            }
          }
        }
        if (!isMerged) {
          final Damage mergedDamage = new Damage();
          mergedDamage.setAttack(damages.get(x).getAttack());
          mergedDamage.setAttribute(damages.get(x).getAttribute());
          mergedDamage.setRace(damages.get(x).getRace());
          mergedDamage.setMagnification(mergedMagnification);
          mergedDamages.add(mergedDamage);
        }
      }
      combination.setDamages(damages);

      final List<Healing> healings = new ArrayList<>();
      for (Slot slot : slots) {
        healings.addAll(slot.getKokoro().getHealings());
      }
      final List<Healing> mergedHealings = new ArrayList<>();
      for (int x = 0; x < healings.size(); x++) {
        boolean isMerged = false;
        int mergedMagnification = healings.get(x).getMagnification();
        for (int y = 0; y < healings.size(); y++) {
          if (healings.get(x).getType().equals(healings.get(y).getType())) {
            if (y > x) {
              mergedMagnification += healings.get(y).getMagnification();
            } else if (y < x) {
              isMerged = true;
            }
          }
        }
        if (!isMerged) {
          final Healing mergedHealing = new Healing();
          mergedHealing.setType(healings.get(x).getType());
          mergedHealing.setMagnification(mergedMagnification);
          mergedHealings.add(mergedHealing);
        }
      }
      combination.setHealings(healings);

      combinations.add(combination);
      log.debug("{}, {}, {}, {}",
          result.getK0id(),
          result.getK1id(),
          result.getK2id(),
          result.getK3id()
      );
      log.debug("{}, {}, {}, {}",
          slots.get(0).getKokoro().getNumber(),
          slots.get(1).getKokoro().getNumber(),
          slots.get(2).getKokoro().getNumber(),
          slots.get(3).getKokoro().getNumber()
      );
    }
    return combinations;
  }

  @Override
  public List<Combination> getCombinations(
      final JobType jobType,
      final JobClassType jobClassType,
      final AttackType attackType,
      final AttributeType attributeType,
      final RaceType raceType,
      final int cost,
      final JobParameter jobParameter,
      final String bride,
      final Map<Integer, List<GradeType>> exclusions,
      final Map<Integer, List<GradeType>> inclusions,
      final int limit
  ) {
    final List<Integer> nonBrides;
    switch (bride) {
      case "ビアンカ":
        nonBrides = Arrays.asList(9990, 9989);
        break;
      case "フローラ":
        nonBrides = Arrays.asList(9991, 9989);
        break;
      case "デボラ":
        nonBrides = Arrays.asList(9991, 9990);
        break;
      default:
        throw new IllegalArgumentException("Illegal Argument: set correct bride name: " + bride + ".");
    }

    final StopWatch stopWatch = new StopWatch();
    stopWatch.start("findCombinationsFromRdbms");

    final DwhType dwhType;
    if (activeProfile.equals("dwh-gcp-bigquery-prod") || activeProfile.equals("dwh-gcp-bigquery-dev")) {
      dwhType = DwhType.BIG_QUERY;
    } else if (activeProfile.equals("dwh-gcp-cloudsql-postgresql")) {
      dwhType = DwhType.CLOUD_SQL_POSTGRESQL;
    } else {
      throw new IllegalStateException("Unknown profile name: " + activeProfile);
    }
    final List<Result> results = applicationContext
        .getBeansOfType(IKokoroCombinationRepository.class)
        .get(UPPER_UNDERSCORE.to(LOWER_HYPHEN, dwhType.name()) + repositorySuffix)
        .get(jobType, attackType, attributeType, raceType, cost, jobParameter, nonBrides, exclusions, inclusions, limit);

    stopWatch.stop();
    log.info("{} results, {} ms",
        results.size(), String.format("%,d", stopWatch.getLastTaskTimeMillis())
    );
    log.debug(results.toString());
    return convert(results, jobType, jobClassType);
  }

  @Override
  public void persistKokoroFlats() {
    final ModelMapper modelMapper = new ModelMapper();
    for (final KokoroFlat kokoroFlat : kokoroFlats) {
      final KokoroFlatEntity kokoroFlatEntity = modelMapper.map(kokoroFlat, KokoroFlatEntity.class);
      log.debug(kokoroFlatEntity.toString());
      if (kokoroFlatRepository.findFirstByIdAndGrade(kokoroFlat.getNumber(), kokoroFlat.getGrade())
          .size() == 0
      ) {
        kokoroFlatRepository.save(kokoroFlatEntity);
      }
    }
  }

  @Override
  public void persistKokoros() {
    final ModelMapper modelMapper = new ModelMapper();
    for (final Kokoro kokoro : kokoros) {
      final KokoroEntity kokoroEntity = modelMapper.map(kokoro, KokoroEntity.class);
      final List<DamageEntity> damageEntities = new ArrayList<>();
      for (final Damage damage : kokoro.getDamages()) {
        final DamageEntity damageEntity = modelMapper.map(damage, DamageEntity.class);
        damageEntity.setKokoro(kokoroEntity);
        damageEntities.add(damageEntity);
      }
      kokoroEntity.setDamages(damageEntities);
      log.debug(kokoroEntity.toString());
      kokoroRepository.save(kokoroEntity);
    }
  }

  @Async
  @Override
  public void persistCombinations() {
    final ObjectMapper objectMapper = new ObjectMapper();
    final StopWatch stopWatch = new StopWatch();
    stopWatch.start("persistCombinations");
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    jobKokoroCombinations.get(0).getCombinations().forEach(combination -> {
      combinationRepository.save(objectMapper.convertValue(combination, CombinationEntity.class));
    });
    stopWatch.stop();
    log.info("save {} entities: {} ms",
        jobKokoroCombinations.get(0).getCombinations().size(),
        String.format("%,d", stopWatch.getLastTaskTimeMillis())
    );
  }
}
