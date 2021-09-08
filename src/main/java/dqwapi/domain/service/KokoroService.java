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
import dqwapi.domain.model.kokoro.Kokoro;
import dqwapi.domain.model.kokoro.Parameter;
import dqwapi.domain.model.kokoro.Slot;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KokoroService implements IKokoroService {

  private Map<JobType, List<KokoroType>> slotsByJob = new HashMap<>();
  private List<Kokoro> kokoros = new ArrayList<>();
  private List<Combination> combinations = new ArrayList<>();

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
      log.info(slotsByJob.toString());
      log.info("{} jobs", slotsByJob.size());
      kokoros =
          objectMapper.readValue(kokoroJsonResource.getInputStream(), new TypeReference<>() {});
      log.info(kokoros.toString());
      log.info("{} kokoros", kokoros.size());
      combinations = getCombinations(JobType.BATTLE_MASTER);
      process();
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

  void process() {
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

    combinations = combinations.stream()
        .sorted(Comparator.comparingInt(combination -> combination.getParameter().getOp()))
        .collect(Collectors.toList());

    for (Combination combination : combinations) {
      final List<Slot> slots = combination.getSlots();
      log.info("{}:{}:{}, {}:{}:{}, {}:{}:{}, {}:{}:{}",
          slots.get(0).getType(),
          slots.get(0).getKokoro().getName(),
          slots.get(0).getKokoro().getType(),
          slots.get(1).getType(),
          slots.get(1).getKokoro().getName(),
          slots.get(1).getKokoro().getType(),
          slots.get(2).getType(),
          slots.get(2).getKokoro().getName(),
          slots.get(2).getKokoro().getType(),
          slots.get(3).getType(),
          slots.get(3).getKokoro().getName(),
          slots.get(3).getKokoro().getType()
      );
      log.info(combination.getParameter().toString());
    }
  }

  @Override
  public List<Combination> getCombinations(final JobType jobType) {
    int count = 0;
    for (int i = 0; i < kokoros.size(); i++) {
      for (int j = 0; j < kokoros.size(); j++) {
        if (j != i) {
          for (int k = 0; k < kokoros.size(); k++) {
            if (k != i && k != j) {
              for (int l = 0; l < kokoros.size(); l++) {
                if (l != i && l != j && l != k) {
                  log.debug("{}, {}, {}, {}", i, j, k, l);
                  final List<Integer> kokoroIds = Arrays.asList(i, j, k, l);
                  count++;

                  final Combination combination = new Combination();
                  final List<KokoroType> kokoroTypes = slotsByJob.get(jobType);

                  final List<Slot> slots = new ArrayList<>();
                  final ObjectMapper objectMapper = new ObjectMapper();
                  for (int z = 0; z < kokoroTypes.size(); z++) {
                    final Slot slot = new Slot();
                    slot.setType(kokoroTypes.get(z));
                    slot.setKokoro(
                        objectMapper.convertValue(kokoros.get(kokoroIds.get(z)), Kokoro.class)
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
