package dqwapi.domain.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Value("${dqwapi.version}")
  private String version;

  @Bean
  public OpenAPI customOpenApi() {
    // TODO: environmental variable
    return new OpenAPI().addServersItem(new Server().url("https://dqwapi.hirooka.pro")).info(new Info().title("OpenAPI definition").version(version));
  }

}
