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
import dqwapi.domain.model.kokoro.Kokoro;
import dqwapi.domain.model.kokoro.Parameter;
import dqwapi.domain.model.kokoro.Slot;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KokoroService implements IKokoroService {

  private List<Kokoro> kokoros = new ArrayList<>();
  private List<List<Slot>> combinations = new ArrayList<>();

  @PostConstruct
  void init() {
    final String json = "kokoro.json";
    final Resource resource = new ClassPathResource(json);
    try {
      final ObjectMapper objectMapper = new ObjectMapper();
      kokoros = objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {});
      log.info(kokoros.toString());
      log.info("{} kokoros", kokoros.size());
      combinations = getCombinations();
      process();
    } catch (IOException ex) {
      throw new IllegalStateException("Failed to parse " + json, ex);
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
  }

  void process() {
    for (List<Slot> slots : combinations) {
      for (Slot slot : slots) {
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
    }
    for (List<Slot> slots : combinations) {
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
    }
  }

  @Override
  public List<List<Slot>> getCombinations() {
    int count = 0;
    for (int i = 0; i < kokoros.size(); i++) {
      for (int j = 0; j < kokoros.size(); j++) {
        if (j != i) {
          for (int k = 0; k < kokoros.size(); k++) {
            if (k != i && k != j) {
              for (int l = 0; l < kokoros.size(); l++) {
                if (l != i && l != j && l != k) {
                  log.debug("{}, {}, {}, {}", i, j, k, l);
                  count++;

                  final ObjectMapper objectMapper = new ObjectMapper();

                  // BATTLE_MASTER
                  List<Slot> slots = new ArrayList<>();

                  Slot slot1 = new Slot();
                  slot1.setType(RED);
                  slot1.setKokoro(objectMapper.convertValue(kokoros.get(i), Kokoro.class));
                  slots.add(slot1);

                  Slot slot2 = new Slot();
                  slot2.setType(RED);
                  slot2.setKokoro(objectMapper.convertValue(kokoros.get(j), Kokoro.class));
                  slots.add(slot2);

                  Slot slot3 = new Slot();
                  slot3.setType(RED_YELLOW);
                  slot3.setKokoro(objectMapper.convertValue(kokoros.get(k), Kokoro.class));
                  slots.add(slot3);

                  Slot slot4 = new Slot();
                  slot4.setType(RAINBOW);
                  slot4.setKokoro(objectMapper.convertValue(kokoros.get(l), Kokoro.class));
                  slots.add(slot4);

                  combinations.add(slots);
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
