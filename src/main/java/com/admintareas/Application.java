package com.admintareas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import com.admintareas.utils.Constantes;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * Clase principal de la aplicación Spring Boot.
 * Esta clase se encarga de iniciar la API y escanear las entidades
 * definidas en el paquete especificado.
 * 
 * @author Bryan Núñez
 */
@SpringBootApplication
@EntityScan("${apitask.app.package.entities}")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	
	@Bean
	OpenAPI customOpenAPI() {
	    return new OpenAPI()
	    		.components(new Components().addSecuritySchemes(Constantes.SCHEME, createAPIKeyScheme()))
	    	        .info(createApiInfo());
	}

	private SecurityScheme createAPIKeyScheme() {
	    return new SecurityScheme()
	            .type(SecurityScheme.Type.HTTP) 
	            .scheme(Constantes.SCHEME); 
	}

	private Info createApiInfo() {
		return new Info()
				.title(Constantes.API_TITLE)
				.description(Constantes.API_DESCRIPTION)
				.version(Constantes.API_VERSION)
				.contact(new Contact()
						.name(Constantes.CONTACT_NAME)
						.email(Constantes.CONTACT_EMAIL)
						.url(Constantes.CONTACT_URL));
	}
}
 