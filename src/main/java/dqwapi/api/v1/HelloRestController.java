package dqwapi.api.v1;

import dqwapi.domain.model.Hello;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ハロー", description = "こんにちは")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("${dqwapi.path-prefix}v1/hello")
@RestController
public class HelloRestController {

  @Value("${dqwapi.git-build-version}")
  private String version;

  @Value("${dqwapi.git-commit-id}")
  private String commitId;

  @GetMapping
  public Hello hello() {
    final Instant instant = Instant.now();
    return new Hello(
        instant.atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ISO_ZONED_DATE_TIME),
        instant.toEpochMilli(),
        version,
        commitId
    );
  }
}
