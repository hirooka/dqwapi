package dqwapi.domain.service;

import static dqwapi.domain.model.common.KokoroType.BLUE;
import static dqwapi.domain.model.common.KokoroType.GREEN;
import static dqwapi.domain.model.common.KokoroType.PURPLE;
import static dqwapi.domain.model.common.KokoroType.RED;
import static dqwapi.domain.model.common.KokoroType.YELLOW;
import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dqwapi.domain.entity.CombinationEntity;
import dqwapi.domain.entity.DamageEntity;
import dqwapi.domain.entity.KokoroEntity;
import dqwapi.domain.model.common.KokoroType;
import dqwapi.domain.model.common.Parameter;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.Combination;
import dqwapi.domain.model.kokoro.Damage;
import dqwapi.domain.model.kokoro.JobKokoroCombination;
import dqwapi.domain.model.kokoro.Kokoro;
import dqwapi.domain.model.kokoro.RankType;
import dqwapi.domain.model.kokoro.Slot;
import dqwapi.domain.model.kokoro.SuitableCombination;
import dqwapi.domain.repository.ICombinationRepository;
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
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Slf4j
@Service
public class KokoroService implements IKokoroService {

  @Value("${dqwapi.kokoro-json}")
  private String kokoroJson;

  private final IKokoroRepository kokoroRepository;
  private final ICombinationRepository combinationRepository;

  private Map<JobType, List<KokoroType>> slotsByJob = new HashMap<>();
  private List<Kokoro> kokoros = new ArrayList<>();
  private final List<JobKokoroCombination> jobKokoroCombinations = new ArrayList<>();

  public KokoroService(
      final IKokoroRepository kokoroRepository,
      final ICombinationRepository combinationRepository) {
    this.kokoroRepository = requireNonNull(kokoroRepository);
    this.combinationRepository = requireNonNull(combinationRepository);
  }

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
                      if (kokoros.get(k).getType().equals(RED) || kokoros.get(k).getType().equals(YELLOW)) {
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
                      if (kokoros.get(k).getType().equals(BLUE) || kokoros.get(k).getType().equals(RED)) {
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
                      if (kokoros.get(i).getType().equals(PURPLE) || kokoros.get(i).getType().equals(GREEN)) {
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
                      if (kokoros.get(j).getType().equals(PURPLE) || kokoros.get(j).getType().equals(GREEN)) {
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
                      if (kokoros.get(k).getType().equals(PURPLE) || kokoros.get(k).getType().equals(GREEN)) {
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
                      if (kokoros.get(k).getType().equals(YELLOW) || kokoros.get(k).getType().equals(GREEN)) {
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
                  final int i0 = kokoros.get(i).getId();
                  final int i1 = kokoros.get(j).getId();
                  final int i2 = kokoros.get(k).getId();
                  final int i3 = kokoros.get(l).getId();
                  if (i0 != i1 && i0 != i2 && i0 != i3 && i1 != i2 && i1 != i3 && i2 != i3 && !canBrideSkip(i0, i1, i2, i3)) {
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
    try {
      final StopWatch stopWatch = new StopWatch();
      stopWatch.start("slotsByJob");
      final ObjectMapper objectMapper = new ObjectMapper();
      slotsByJob =
          objectMapper.readValue(jobSlotJsonResource.getInputStream(), new TypeReference<>() {});
      stopWatch.stop();
      log.info("{} jobs, {} ms", slotsByJob.size(), String.format("%,d", stopWatch.getLastTaskTimeMillis()));
      log.debug(slotsByJob.toString());

      stopWatch.start("kokoros");
      kokoros =
          objectMapper.readValue(kokoroJsonResource.getInputStream(), new TypeReference<>() {});
      stopWatch.stop();
      log.info("{} kokoros, {} ms", kokoros.size(), String.format("%,d", stopWatch.getLastTaskTimeMillis()));
      log.debug(kokoros.toString());

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

    } catch (IOException ex) {
      throw new IllegalStateException("Failed to parse JSON file.", ex);
    }
  }

  @Override
  public List<Kokoro> getAll() {
    return kokoros;
  }

  @Override
  public List<Combination> getCombinations(final JobType jobType) {
    for (JobKokoroCombination jobKokoroCombination : jobKokoroCombinations) {
      if (jobKokoroCombination.getJob().equals(jobType)) {
        return jobKokoroCombination.getCombinations();
      }
    }
    return new ArrayList<>();
  }

  @Override
  public List<Combination> getCombinations(
      final JobType jobType,
      final int cost,
      final String bride,
      final Map<Integer, List<RankType>> exclusions
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
                  .map(slot -> slot.getKokoro().getId())
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
                if (exclusions.containsKey(slot.getKokoro().getId())) {
                  if (exclusions.get(slot.getKokoro().getId()).contains(slot.getKokoro().getRank())) {
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

  @Override
  public void persistKokoros() {
    final ModelMapper modelMapper = new ModelMapper();
    for (Kokoro kokoro : kokoros) {
      final KokoroEntity kokoroEntity = modelMapper.map(kokoro, KokoroEntity.class);
      final List<DamageEntity> damageEntities = new ArrayList<>();
      for (Damage damage : kokoro.getDamages()) {
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
    log.info("save {} entities: {} ms", jobKokoroCombinations.get(0).getCombinations().size(), String.format("%,d", stopWatch.getLastTaskTimeMillis()));
  }
}
