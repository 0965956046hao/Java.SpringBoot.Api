package dev.toanle.restapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API Documentation", version = "v1", description = "API documentation for the project"))
public class SwaggerConfig {

    // Bean to configure OpenAPI
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("API Documentation")
                        .version("v1")
                        .description("API documentation for the Spring Boot project"));
    }
}