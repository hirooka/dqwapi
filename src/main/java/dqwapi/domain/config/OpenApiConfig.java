package dqwapi.domain.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.cloud.CloudPlatform;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.ObjectUtils;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class OpenApiConfig {

  @Value("${dqwapi.version}")
  private String version;

  @Value("${dqwapi.fqdn}")
  private String fqdn;

  private final Environment environment;

  @Bean
  public OpenAPI customOpenApi() {

    if (ObjectUtils.isEmpty(CloudPlatform.getActive(environment)) || CloudPlatform.getActive(environment).equals(CloudPlatform.NONE)) {
      return new OpenAPI().info(new Info().title("OpenAPI definition").version(version));
    } else {
      log.info("CloudPlatform: {}", CloudPlatform.getActive(environment).name());
      return new OpenAPI()
          .addServersItem(new Server().url("https://" + fqdn))
          .info(new Info().title("OpenAPI definition").version(version));
    }
  }

}
