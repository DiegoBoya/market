package com.diego.supermercado;

import org.mapstruct.MapperConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@ComponentScan("com.diego.supermercado.persistance.mapper")
public class SupermercadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupermercadoApplication.class, args);
	}

}
