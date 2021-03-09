package com.epam.jdi.bookstore.configuration.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

import static io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Bookstore API", version = "v1"))
@SecurityScheme(
    name = "bearerAuth",
    type = HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
@SecurityScheme(
    name = "basicAuth",
    type = HTTP,
    scheme = "basic"
)
public class OpenApi3Configuration {

}
