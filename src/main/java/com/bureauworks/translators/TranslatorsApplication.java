package com.bureauworks.translators;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TranslatorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TranslatorsApplication.class, args);
	}

}
