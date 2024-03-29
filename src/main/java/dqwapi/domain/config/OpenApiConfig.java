package dqwapi.domain.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.cloud.CloudPlatform;
import org.springframework.boot.info.GitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.ObjectUtils;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class OpenApiConfig {

  @Autowired
  private GitProperties gitProperties;

  @Value("${dqwapi.fqdn}")
  private String fqdn;

  @Value("${dqwapi.run-on-cloud}")
  private boolean runOnCloud;

  private final Environment environment;

  @Bean
  public OpenAPI customOpenApi() {

    final String version = gitProperties.get("build.version");
    final String title = "DQW API";
    final String description = "ドラクエウォークの上級職において最適だと考えられるこころの組み合わせを提案するAPIです。";
    if (ObjectUtils.isEmpty(CloudPlatform.getActive(environment)) || CloudPlatform.getActive(environment).equals(CloudPlatform.NONE)) {
      if (runOnCloud) {
        return new OpenAPI()
            .addServersItem(new Server().url("https://" + fqdn))
            .info(new Info().title(title).version(version).description(description));
      } else {
        return new OpenAPI().info(new Info().title(title).version(version).description(description));
      }
    } else {
      log.info("CloudPlatform: {}", CloudPlatform.getActive(environment).name());
      return new OpenAPI()
          .addServersItem(new Server().url("https://" + fqdn))
          .info(new Info().title(title).version(version).description(description));
    }
  }

}
