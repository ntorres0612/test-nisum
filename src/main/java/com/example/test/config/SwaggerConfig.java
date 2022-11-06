package com.example.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



@Configuration
public class SwaggerConfig {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	private ApiInfo apiInfo() {
		return new ApiInfo("MyApp Rest APIs",
				"APIs for MyApp.",
				"1.0",
				"Terms of service",
				new Contact("test", "www.org.com", "test@emaildomain.com"),
				"License of API",
				"API license URL",
				Collections.emptyList());
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.OAS_30)
				.apiInfo(apiInfo())
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	private ApiKey apiKey() {
		return new ApiKey(AUTHORIZATION_HEADER, "JWT", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope
				= new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference(AUTHORIZATION_HEADER, authorizationScopes));
	}

}
/*
@Configuration
@EnableSwagger2
public class SwaggerConfig {



	@Bean
	Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build();
	}


	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.securityContexts(securityContext())
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}
	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}
	private ApiInfo apiInfo() {
		return new ApiInfo(
				"My REST API",
				"Some custom description of API.",
				"1.0",
				"Terms of service",
				new Contact("Sallo Szrajbman", "www.baeldung.com", "salloszraj@gmail.com"),
				"License of API",
				"API license URL",
				Collections.emptyList());
	}
	private List<SecurityContext> securityContext() {
		return Arrays.asList(SecurityContext.builder()
				.securityReferences(sf()).build());
	}
	private List<SecurityReference> sf() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}
}
*/