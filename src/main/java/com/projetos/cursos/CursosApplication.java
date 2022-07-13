package com.projetos.cursos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursosApplication {

	private static final Logger LOGGER= LoggerFactory.getLogger(CursosApplication.class);

	public static void main(String[] args) {
        LOGGER.info("iniciando SpringBoot");
		SpringApplication.run(CursosApplication.class, args);
		LOGGER.info("SpringBoot iniciado com sucesso!!!");
	}

}
