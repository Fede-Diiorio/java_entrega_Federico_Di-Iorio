package com.coderhouse.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

	@Bean
	public OpenAPI api() {
		return new OpenAPI().info(new Info().title("Ecommerce Java Coderhouse").version("v1.0.0")
				.contact(new Contact().name("Federico Di Iorio").email("fndiiorio@hotmail.com"))
				.termsOfService("https://github.com/Fede-Diiorio/java_entrega_Federico_Di-Iorio").description("Más información sobre el proyecto.")
				.description("API encargada de realizar peticiones a una base de datos simulando el funcionamiento de un ecommerce."));
	}
}
