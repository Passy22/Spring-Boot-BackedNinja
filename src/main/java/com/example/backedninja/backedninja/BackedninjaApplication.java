package com.example.backedninja.backedninja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.example.backedninja")
// Añado estas dos configuraciones para que me cargue y encuentre las anotaciones de los repositorios.
@EntityScan("com.example.backedninja.entity")
@EnableJpaRepositories("com.example.backedninja.repository")
@EnableScheduling
public class BackedninjaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackedninjaApplication.class, args);
	}
}
