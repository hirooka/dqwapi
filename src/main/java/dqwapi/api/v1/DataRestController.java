package dqwapi.api.v1;

import dqwapi.domain.model.common.CsvType;
import dqwapi.domain.service.IDataService;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("${dqwapi.path-prefix}v1/data")
@RestController
public class DataRestController {

  @Value("${dqwapi.kokoro-persist}")
  private boolean canPersist;

  private final IDataService dataService;

  @Hidden
  @GetMapping("csv")
  public Map<String, String> csv(@RequestParam(value = "t", required = false) CsvType csvType) {
    if (!canPersist) {
      throw new IllegalArgumentException("Cannot create CSV...");
    }
    if (ObjectUtils.isEmpty(csvType)) {
      csvType = CsvType.ALL;
    }
    dataService.createCombinationCsv(csvType);
    return Map.of("CSV", csvType.name());
  }
}
