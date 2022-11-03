package dqwapi.api;

import dqwapi.domain.model.Hello;
import io.swagger.v3.oas.annotations.Hidden;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.info.GitProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RequestMapping("")
@RestController
public class IndexRestController {

  private final GitProperties gitProperties;

  @Hidden
  @GetMapping
  public Hello hello() {
    final Instant instant = Instant.now();
    return new Hello(
        instant.atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ISO_ZONED_DATE_TIME),
        instant.toEpochMilli(),
        gitProperties.get("build.version"),
        gitProperties.getShortCommitId(),
        gitProperties.getCommitTime()
    );
  }
}
