package dqwapi.api.v1;

import dqwapi.domain.model.kokoro.Kokoro;
import dqwapi.domain.service.IKokoroService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RequestMapping("api/v1/kokoros")
@RestController
public class KokoroRestController {

  private final IKokoroService kokoroService;

  @GetMapping
  public List<Kokoro> getAll() {
    return kokoroService.getAll();
  }

  @GetMapping("pk")
  public String persistK() {
    kokoroService.persistKokoros();
    return "persisted.";
  }

  @GetMapping("pc")
  public String persistC() {
    kokoroService.persistCombinations();
    return "persisted.";
  }

}
