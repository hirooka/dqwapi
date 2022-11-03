package dqwapi.domain.model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Hello {
  private String date;
  private long epoch;
  private String version;
  private String commitId;
  private Instant commitTime;
}
