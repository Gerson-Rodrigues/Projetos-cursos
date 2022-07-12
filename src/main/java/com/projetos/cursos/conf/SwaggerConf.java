package com.projetos.cursos.conf;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConf {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.projetos.cursos"))
				.paths(PathSelectors.ant("/**")).build().apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfo("API para controle do CRUD",
				"Projeto para desenvolvimento de um CRUD",
				"Versão 3.5",
				"http://www.google.com.br",
				new Contact("Projeto Solo",
						"http://github.com/Gerson-Rodrigues",
						"gerson.rodrigues@castgroup.com.br"),
						"Apoio - Cast Group",
						"http://castgroup.com.br",Collections.emptyList());
	}
}
