package dqwapi.api.v1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dqwapi.domain.model.kokoro.Damage;
import dqwapi.domain.model.kokoro.Healing;
import dqwapi.domain.model.kokoro.Kokoro;
import dqwapi.domain.model.kokoro.KokoroFlat;
import dqwapi.domain.service.IKokoroService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.CaseUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "こころ", description = "こころの一覧")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("${dqwapi.path-prefix}v1/kokoros")
@RestController
public class KokoroRestController {

  @Value("${dqwapi.kokoro-persist}")
  private boolean canPersist;

  @Value("${dqwapi.kokoro-json}")
  private String kokoroJson;

  @Value("${dqwapi.kokoro-flat-json}")
  private String kokoroFlatJson;

  private final IKokoroService kokoroService;

  @GetMapping
  public List<Kokoro> getAll() {
    return kokoroService.getAll();
  }

  @Hidden
  @GetMapping("k")
  public List<Map<String, String>> get() {
    return kokoroService.get();
  }

  @Hidden
  @GetMapping("j")
  public List<Map<String, String>> getJobs() {
    return kokoroService.getJobs();
  }

  @Hidden
  @GetMapping("pkf")
  public String persistKokoroFlats() {
    if (!canPersist) {
      throw new IllegalArgumentException("Cannot persist kokoros...");
    }
    kokoroService.persistKokoroFlats();
    return "persisted.";
  }

  @Hidden
  @GetMapping("pk")
  public String persistK() {
    if (!canPersist) {
      throw new IllegalArgumentException("Cannot persist kokoros...");
    }
    kokoroService.persistKokoros();
    return "persisted.";
  }

  @Hidden
  @GetMapping("pc")
  public String persistC() {
    if (!canPersist) {
      throw new IllegalArgumentException("Cannot persist kokoros...");
    }
    kokoroService.persistCombinations();
    return "persisted.";
  }

  @Hidden
  @GetMapping("c")
  public String convert() {
    if (!canPersist) {
      throw new IllegalArgumentException("Cannot persist kokoros...");
    }
    final Resource kokoroJsonResource = new ClassPathResource(kokoroJson);
    try {
      final StopWatch stopWatch = new StopWatch();
      stopWatch.start("kokoros");
      final ObjectMapper objectMapper = new ObjectMapper();
      final List<Kokoro> kokoros =
          objectMapper.readValue(kokoroJsonResource.getInputStream(), new TypeReference<>() {});
      stopWatch.stop();
      log.info(
          "{} kokoros, {} ms",
          kokoros.size(), String.format("%,d", stopWatch.getLastTaskTimeMillis())
      );

      stopWatch.start("convert");
      final ModelMapper modelMapper = new ModelMapper();
      final List<KokoroFlat> kokoroFlats = new ArrayList<>();
      for (Kokoro kokoro : kokoros) {
        final KokoroFlat kokoroFlat = modelMapper.map(kokoro, KokoroFlat.class);
        for (Damage damage : kokoro.getDamages()) {
          final String string = damage.getAttack().name()
              + " " + damage.getAttribute().name()
              + " " + damage.getRace();
          final String damageString =
              CaseUtils.toCamelCase(string, false, ' ');
          log.debug(damageString);

          switch (damageString) {

            case "breathNoneNone":
              kokoroFlat.setBreathNoneNone(damage.getMagnification());
              break;
            case "hitNoneNone":
              kokoroFlat.setHitNoneNone(damage.getMagnification());
              break;
            case "slashNoneNone":
              kokoroFlat.setSlashNoneNone(damage.getMagnification());
              break;
            case "spellNoneNone":
              kokoroFlat.setSpellNoneNone(damage.getMagnification());
              break;

            case "allBagiNone":
              kokoroFlat.setAllBagiNone(damage.getMagnification());
              break;
            case "allDeinNone":
              kokoroFlat.setAllDeinNone(damage.getMagnification());
              break;
            case "allDorumaNone":
              kokoroFlat.setAllDorumaNone(damage.getMagnification());
              break;
            case "allGiraNone":
              kokoroFlat.setAllGiraNone(damage.getMagnification());
              break;
            case "allHyadoNone":
              kokoroFlat.setAllHyadoNone(damage.getMagnification());
              break;
            case "allIoNone":
              kokoroFlat.setAllIoNone(damage.getMagnification());
              break;
            case "allJibariaNone":
              kokoroFlat.setAllJibariaNone(damage.getMagnification());
              break;
            case "allMeraNone":
              kokoroFlat.setAllMeraNone(damage.getMagnification());
              break;

            case "hitBagiNone":
              kokoroFlat.setHitBagiNone(damage.getMagnification());
              break;
            case "hitDeinNone":
              kokoroFlat.setHitDeinNone(damage.getMagnification());
              break;
            case "hitDorumaNone":
              kokoroFlat.setHitDorumaNone(damage.getMagnification());
              break;
            case "hitGiraNone":
              kokoroFlat.setHitGiraNone(damage.getMagnification());
              break;
            case "hitHyadoNone":
              kokoroFlat.setHitHyadoNone(damage.getMagnification());
              break;
            case "hitIoNone":
              kokoroFlat.setHitIoNone(damage.getMagnification());
              break;
            case "hitJibariaNone":
              kokoroFlat.setHitJibariaNone(damage.getMagnification());
              break;
            case "hitMeraNone":
              kokoroFlat.setHitMeraNone(damage.getMagnification());
              break;

            case "slashBagiNone":
              kokoroFlat.setSlashBagiNone(damage.getMagnification());
              break;
            case "slashDeinNone":
              kokoroFlat.setSlashDeinNone(damage.getMagnification());
              break;
            case "slashDorumaNone":
              kokoroFlat.setSlashDorumaNone(damage.getMagnification());
              break;
            case "slashGiraNone":
              kokoroFlat.setSlashGiraNone(damage.getMagnification());
              break;
            case "slashHyadoNone":
              kokoroFlat.setSlashHyadoNone(damage.getMagnification());
              break;
            case "slashIoNone":
              kokoroFlat.setSlashIoNone(damage.getMagnification());
              break;
            case "slashJibariaNone":
              kokoroFlat.setSlashJibariaNone(damage.getMagnification());
              break;
            case "slashMeraNone":
              kokoroFlat.setSlashMeraNone(damage.getMagnification());
              break;

            case "spellBagiNone":
              kokoroFlat.setSpellBagiNone(damage.getMagnification());
              break;
            case "spellDeinNone":
              kokoroFlat.setSpellDeinNone(damage.getMagnification());
              break;
            case "spellDorumaNone":
              kokoroFlat.setSpellDorumaNone(damage.getMagnification());
              break;
            case "spellGiraNone":
              kokoroFlat.setSpellGiraNone(damage.getMagnification());
              break;
            case "spellHyadoNone":
              kokoroFlat.setSpellHyadoNone(damage.getMagnification());
              break;
            case "spellIoNone":
              kokoroFlat.setSpellIoNone(damage.getMagnification());
              break;
            case "spellJibariaNone":
              kokoroFlat.setSpellJibariaNone(damage.getMagnification());
              break;
            case "spellMeraNone":
              kokoroFlat.setSpellMeraNone(damage.getMagnification());
              break;

            case "allNoneAnimal":
              kokoroFlat.setAllNoneAnimal(damage.getMagnification());
              break;
            case "allNoneBird":
              kokoroFlat.setAllNoneBird(damage.getMagnification());
              break;
            case "allNoneDevil":
              kokoroFlat.setAllNoneDevil(damage.getMagnification());
              break;
            case "allNoneDragon":
              kokoroFlat.setAllNoneDragon(damage.getMagnification());
              break;
            case "allNoneElement":
              kokoroFlat.setAllNoneElement(damage.getMagnification());
              break;
            case "allNoneInsect":
              kokoroFlat.setAllNoneInsect(damage.getMagnification());
              break;
            case "allNoneMachine":
              kokoroFlat.setAllNoneMachine(damage.getMagnification());
              break;
            case "allNoneMaterial":
              kokoroFlat.setAllNoneMaterial(damage.getMagnification());
              break;
            case "allNonePhantom":
              kokoroFlat.setAllNonePhantom(damage.getMagnification());
              break;
            case "allNonePlant":
              kokoroFlat.setAllNonePlant(damage.getMagnification());
              break;
            case "allNoneSlime":
              kokoroFlat.setAllNoneSlime(damage.getMagnification());
              break;
            case "allNoneWater":
              kokoroFlat.setAllNoneWater(damage.getMagnification());
              break;
            case "allNoneZombie":
              kokoroFlat.setAllNoneZombie(damage.getMagnification());
              break;
            default:
              throw new IllegalArgumentException("Unknown damage format.");
          }
        }
        if (!ObjectUtils.isEmpty(kokoro.getHealings())) {
          for (Healing healing : kokoro.getHealings()) {
            final String string = "healing" + " " + healing.getType().name();
            final String healingString =
                CaseUtils.toCamelCase(string, false, ' ');
            log.debug(healingString);

            switch (healingString) {
              case "healingSkill":
                kokoroFlat.setHealingSkill(healing.getMagnification());
                break;
              case "healingSpecialty":
                kokoroFlat.setHealingSpecialty(healing.getMagnification());
                break;
              case "healingSpell":
                kokoroFlat.setHealingSpell(healing.getMagnification());
                break;
              default:
                throw new IllegalArgumentException("Unknown healing format.");
            }
          }
        }
        log.debug(kokoroFlat.toString());
        kokoroFlats.add(kokoroFlat);
      }

      final String json =
          objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(kokoroFlats);
      log.info(json);

      // TODO: path
      final FileOutputStream fos = new FileOutputStream(kokoroFlatJson);
      fos.write(json.getBytes(StandardCharsets.UTF_8));
      fos.close();

      stopWatch.stop();
      log.info(
          "{} kokoroFlats, {} ms",
          kokoroFlats.size(), String.format("%,d", stopWatch.getLastTaskTimeMillis())
      );

      return json;

    } catch (IOException ex) {
      throw new IllegalStateException("Failed to parse JSON file.", ex);
    }
  }
}
