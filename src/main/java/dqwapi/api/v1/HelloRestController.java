package dqwapi.api.v1;

import dqwapi.domain.model.Hello;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequestMapping("api/v1/hello")
@RestController
public class HelloRestController {
  @GetMapping
  public Hello hello() {
    final Instant instant = Instant.now();
    return new Hello(
        instant.atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ISO_ZONED_DATE_TIME),
        instant.toEpochMilli()
    );
  }
}
