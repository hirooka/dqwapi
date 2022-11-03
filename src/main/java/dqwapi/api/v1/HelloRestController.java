package dqwapi.api.v1;

import dqwapi.domain.model.Hello;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.info.GitProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ハロー", description = "こんにちは")
@Slf4j
@AllArgsConstructor
@RequestMapping("${dqwapi.path-prefix}v1/hello")
@RestController
public class HelloRestController {

  private final GitProperties gitProperties;

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
