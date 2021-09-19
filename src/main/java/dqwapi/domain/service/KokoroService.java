package dqwapi.domain.service;

import static dqwapi.domain.model.common.KokoroType.BLUE;
import static dqwapi.domain.model.common.KokoroType.BLUE_GREEN;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import dqwapi.domain.model.common.KokoroType;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.Combination;
import dqwapi.domain.model.kokoro.Damage;
import dqwapi.domain.model.kokoro.DamageMagnification;
import dqwapi.domain.model.kokoro.Effect;
import dqwapi.domain.model.kokoro.JobKokoroCombination;
import dqwapi.domain.model.kokoro.Kokoro;
import dqwapi.domain.model.kokoro.Parameter;
import dqwapi.domain.model.kokoro.Slot;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KokoroService implements IKokoroService {

  private Map<JobType, List<KokoroType>> slotsByJob = new HashMap<>();
  private List<Kokoro> kokoros = new ArrayList<>();
  private final List<JobKokoroCombination> jobKokoroCombinations = new ArrayList<>();

  @PostConstruct
  void init() {
    final String kokoroJson = "kokoro.json";
    final Resource kokoroJsonResource = new ClassPathResource(kokoroJson);
    final String jobSlotJson = "slots-by-job.json";
    final Resource jobSlotJsonResource = new ClassPathResource(jobSlotJson);
    try {
      final ObjectMapper objectMapper = new ObjectMapper();
      slotsByJob =
          objectMapper.readValue(jobSlotJsonResource.getInputStream(), new TypeReference<>() {});
      log.info("{} jobs", slotsByJob.size());
      log.info(slotsByJob.toString());
      kokoros =
          objectMapper.readValue(kokoroJsonResource.getInputStream(), new TypeReference<>() {});
      log.info("{} kokoros", kokoros.size());
      log.info(kokoros.toString());

      for (JobType jobType : slotsByJob.keySet()) {
        final List<Combination> combinations = createCombinations(jobType);
        final JobKokoroCombination jobKokoroCombination = new JobKokoroCombination();
        jobKokoroCombination.setJob(jobType);
        jobKokoroCombination.setCombinations(combinations);
        jobKokoroCombinations.add(jobKokoroCombination);
      }
      for (JobKokoroCombination jobKokoroCombination : jobKokoroCombinations) {
        jobKokoroCombination.setCombinations(process(jobKokoroCombination.getCombinations()));
      }

    } catch (IOException ex) {
      throw new IllegalStateException("Failed to parse JSON file.", ex);
    }
  }

  private void multiplyParameter(final Slot slot) {
    final Parameter parameter = slot.getKokoro().getStatus().getParameter();
    final double magnification = 1.2;
    parameter.setHp((int) Math.ceil(parameter.getHp() * magnification));
    parameter.setMp((int) Math.ceil(parameter.getMp() * magnification));
    parameter.setOp((int) Math.ceil(parameter.getOp() * magnification));
    parameter.setDp((int) Math.ceil(parameter.getDp() * magnification));
    parameter.setOs((int) Math.ceil(parameter.getOs() * magnification));
    parameter.setDs((int) Math.ceil(parameter.getDs() * magnification));
    parameter.setSp((int) Math.ceil(parameter.getSp() * magnification));
    parameter.setDx((int) Math.ceil(parameter.getDx() * magnification));
    slot.getKokoro().getStatus().setParameter(parameter);
    slot.getKokoro().setName(slot.getKokoro().getName() + "(up)");
  }

  List<Combination> process(List<Combination> combinations) {
    for (Combination combination : combinations) {
      for (Slot slot : combination.getSlots()) {
        if (slot.getType().equals(RAINBOW)) {
          multiplyParameter(slot);
        }
        if (slot.getType().equals(RED)) {
          if (slot.getKokoro().getType().equals(RED)) {
            multiplyParameter(slot);
          }
        }
        if (slot.getType().equals(BLUE)) {
          if (slot.getKokoro().getType().equals(BLUE)) {
            multiplyParameter(slot);
          }
        }
        if (slot.getType().equals(RED_YELLOW)) {
          if (slot.getKokoro().getType().equals(RED)
              || slot.getKokoro().getType().equals(YELLOW)
          ) {
            multiplyParameter(slot);
          }
        }
        if (slot.getType().equals(RED_BLUE)) {
          if (slot.getKokoro().getType().equals(RED)
              || slot.getKokoro().getType().equals(BLUE)
          ) {
            multiplyParameter(slot);
          }
        }
        if (slot.getType().equals(PURPLE_GREEN)) {
          if (slot.getKokoro().getType().equals(PURPLE)
              || slot.getKokoro().getType().equals(GREEN)
          ) {
            multiplyParameter(slot);
          }
        }
        if (slot.getType().equals(YELLOW_PURPLE)) {
          if (slot.getKokoro().getType().equals(YELLOW)
              || slot.getKokoro().getType().equals(PURPLE)
          ) {
            multiplyParameter(slot);
          }
        }
        if (slot.getType().equals(YELLOW_GREEN)) {
          if (slot.getKokoro().getType().equals(YELLOW)
              || slot.getKokoro().getType().equals(GREEN)
          ) {
            multiplyParameter(slot);
          }
        }
        if (slot.getType().equals(BLUE_GREEN)) {
          if (slot.getKokoro().getType().equals(BLUE)
              || slot.getKokoro().getType().equals(GREEN)
          ) {
            multiplyParameter(slot);
          }
        }
        if (slot.getType().equals(YELLOW_BLUE)) {
          if (slot.getKokoro().getType().equals(YELLOW)
              || slot.getKokoro().getType().equals(BLUE)
          ) {
            multiplyParameter(slot);
          }
        }
      }

      final Parameter parameter = new Parameter();
      parameter.setHp(combination.getSlots().stream()
          .mapToInt(slot -> slot.getKokoro().getStatus().getParameter().getHp()).sum());
      parameter.setMp(combination.getSlots().stream()
          .mapToInt(slot -> slot.getKokoro().getStatus().getParameter().getMp()).sum());
      parameter.setOp(combination.getSlots().stream()
          .mapToInt(slot -> slot.getKokoro().getStatus().getParameter().getOp()).sum());
      parameter.setDp(combination.getSlots().stream()
          .mapToInt(slot -> slot.getKokoro().getStatus().getParameter().getDp()).sum());
      parameter.setOs(combination.getSlots().stream()
          .mapToInt(slot -> slot.getKokoro().getStatus().getParameter().getOs()).sum());
      parameter.setDs(combination.getSlots().stream()
          .mapToInt(slot -> slot.getKokoro().getStatus().getParameter().getDs()).sum());
      parameter.setSp(combination.getSlots().stream()
          .mapToInt(slot -> slot.getKokoro().getStatus().getParameter().getSp()).sum());
      parameter.setDx(combination.getSlots().stream()
          .mapToInt(slot -> slot.getKokoro().getStatus().getParameter().getDx()).sum());
      combination.setParameter(parameter);
    }

    for (Combination combination : combinations) {
      final List<DamageMagnification> damageMagnifications = new ArrayList<>();
      for (Slot slot : combination.getSlots()) {
        for (Effect effect : slot.getKokoro().getStatus().getEffects()) {
          for (Damage damage : effect.getDamages()) {
            final DamageMagnification damageMagnification = new DamageMagnification();
            damageMagnification.setAttack(damage.getAttack());
            damageMagnification.setAttribute(damage.getAttribute());
            damageMagnification.setRace(damage.getRace());
            damageMagnification.setMagnification(damage.getMagnification());
            damageMagnifications.add(damageMagnification);
          }
        }
      }

      // merge
      final List<DamageMagnification> mergedDamageMagnifications = new ArrayList<>();

      for (int i = 0; i < damageMagnifications.size(); i++) {
        boolean isMerged = false;
        int mergedMagnification = damageMagnifications.get(i).getMagnification();
        for (int j = 0; j < damageMagnifications.size(); j++) {

          if (damageMagnifications.get(i).getAttack()
              .equals(damageMagnifications.get(j).getAttack())
              && damageMagnifications.get(i).getAttribute()
              .equals(damageMagnifications.get(j).getAttribute())
              && damageMagnifications.get(i).getRace()
              .equals(damageMagnifications.get(j).getRace())
          ) {
            if (j > i) {
              mergedMagnification += damageMagnifications.get(j).getMagnification();
            } else if (j < i) {
              isMerged = true;
            }
          }

        }

        if (!isMerged) {
          DamageMagnification mergedDamageMagnification = new DamageMagnification();
          mergedDamageMagnification.setAttack(damageMagnifications.get(i).getAttack());
          mergedDamageMagnification.setAttribute(damageMagnifications.get(i).getAttribute());
          mergedDamageMagnification.setRace(damageMagnifications.get(i).getRace());
          mergedDamageMagnification.setMagnification(mergedMagnification);
          mergedDamageMagnifications.add(mergedDamageMagnification);
        }
      }

      combination.setDamageMagnifications(mergedDamageMagnifications);
    }

    combinations = combinations.stream()
        .sorted(Comparator.comparingInt(combination -> combination.getParameter().getOp()))
        .collect(Collectors.toList());

    for (Combination combination : combinations) {
      final List<Slot> slots = combination.getSlots();
      log.debug("{}={}({}):{}, {}={}({}):{}, {}={}({}):{}, {}={}({}):{}",
          slots.get(0).getType(),
          slots.get(0).getKokoro().getName(),
          slots.get(0).getKokoro().getRank(),
          slots.get(0).getKokoro().getType(),
          slots.get(1).getType(),
          slots.get(1).getKokoro().getName(),
          slots.get(1).getKokoro().getRank(),
          slots.get(1).getKokoro().getType(),
          slots.get(2).getType(),
          slots.get(2).getKokoro().getName(),
          slots.get(2).getKokoro().getRank(),
          slots.get(2).getKokoro().getType(),
          slots.get(3).getType(),
          slots.get(3).getKokoro().getName(),
          slots.get(3).getKokoro().getRank(),
          slots.get(3).getKokoro().getType()
      );
      log.debug(combination.getParameter().toString());
      log.debug(combination.getDamageMagnifications().toString());
    }
    return combinations;
  }

  private boolean isDuplicatedId(final List<Integer> list) {
    final Set<Integer> set = new HashSet<>();
    list.forEach(index -> set.add(kokoros.get(index).getId()));
    return set.size() != list.size();
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

  private List<Combination> createCombinations(final JobType jobType) {
    final List<Combination> combinations = new ArrayList<>();
    int count = 0;
    for (int i = 0; i < kokoros.size(); i++) {
      for (int j = 0; j < kokoros.size(); j++) {
        if (j != i) {
          for (int k = 0; k < kokoros.size(); k++) {
            if (k != i && k != j) {
              for (int l = 0; l < kokoros.size(); l++) {
                if (l != i && l != j && l != k) {
                  log.debug("{}, {}, {}, {}", i, j, k, l);
                  final List<Integer> kokoroIndexes = Arrays.asList(i, j, k, l);
                  if (isDuplicatedId(kokoroIndexes)) {
                    break;
                  }
                  count++;

                  final Combination combination = new Combination();
                  final List<KokoroType> kokoroTypes = slotsByJob.get(jobType);

                  final List<Slot> slots = new ArrayList<>();
                  final ObjectMapper objectMapper = new ObjectMapper();
                  for (int z = 0; z < kokoroTypes.size(); z++) {
                    final Slot slot = new Slot();
                    slot.setType(kokoroTypes.get(z));
                    slot.setKokoro(
                        objectMapper.convertValue(kokoros.get(kokoroIndexes.get(z)), Kokoro.class)
                    );
                    slots.add(slot);
                  }

                  combination.setSlots(slots);
                  combinations.add(combination);
                }
              }
            }
          }
        }
      }
    }
    log.info("{} combinations", count);
    log.debug(combinations.toString());

    return combinations;
  }
}
