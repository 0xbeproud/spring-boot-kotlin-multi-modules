package com.beproud.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@OpenAPIDefinition(
    info = Info(title="SuperDesire API", description = "API description", version = "V1", contact = Contact(name = "superdesire", email = "contact@superdesire.xyz"))
)
class OpenApiConfig {
    @Bean
    fun openApi(): GroupedOpenApi {
        val paths = arrayOf("/api/v1/**")
        return GroupedOpenApi.builder()
            .group("SuperDesire API v1")
            .pathsToMatch(*paths)
            .build()
    }
}