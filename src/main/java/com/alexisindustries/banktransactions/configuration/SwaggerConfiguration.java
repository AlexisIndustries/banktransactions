package com.alexisindustries.banktransactions.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Banking Microservice API")
                        .version("1.0")
                        .description("API documentation for the banking microservice."));
    }

    @Bean
    public GroupedOpenApi transactionsApi() {
        return GroupedOpenApi.builder()
                .group("transactions")
                .pathsToMatch("/api/transactions/**")
                .build();
    }

    @Bean
    public GroupedOpenApi limitsApi() {
        return GroupedOpenApi.builder()
                .group("limits")
                .pathsToMatch("/api/limits/**")
                .build();
    }

    @Bean
    public GroupedOpenApi exchangeRatesApi() {
        return GroupedOpenApi.builder()
                .group("exchange-rates")
                .pathsToMatch("/api/exchange-rates/**")
                .build();
    }
}
