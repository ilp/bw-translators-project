package com.bureauworks.translators;

import com.bureauworks.translators.model.Translator;
import com.bureauworks.translators.repository.TranslatorRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TranslatorsApplication {

	@Autowired
	private TranslatorRepository translatorRepository;

	public static void main(String[] args) {
		SpringApplication.run(TranslatorsApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		if (translatorRepository.findAll().size() > 0)
			return null;
		else {
			return () -> {
				translatorRepository.save(new Translator(
						"Fulano Silva",
						"fulano@gmail.com",
						"pt_br",
						"en_us"));
				translatorRepository.save(new Translator(
						"Fulano Silva",
						"fulano@gmail.com",
						"pt_br",
						"en_gb"));
				translatorRepository.save(new Translator(
						"Fulano Silva",
						"fulano@gmail.com",
						"en_us",
						"pt_br"));
				translatorRepository.save(new Translator(
						"Fulano Silva",
						"fulano@gmail.com",
						"en_gb",
						"pt_br"));
				translatorRepository.save(new Translator(
						"Fulano Silva",
						"fulano@gmail.com",
						"es_es",
						"pt_br"));
				translatorRepository.save(new Translator(
						"Fulano Silva",
						"fulano@gmail.com",
						"es_es",
						"pt_br"));
				translatorRepository.save(new Translator(
						"Sicrano José",
						"fulano@gmail.com",
						"en_us",
						"pt_br"));
				translatorRepository.save(new Translator(
						"Sicrano José",
						"fulano@gmail.com",
						"en_us",
						"pt_br"));
				translatorRepository.save(new Translator(
						"Sicrano José",
						"fulano@gmail.com",
						"en_gb",
						"pt_br"));
				translatorRepository.save(new Translator(
						"Beltrano Antonio",
						"fulano@gmail.com",
						"en_us",
						"pt_br"));
				translatorRepository.save(new Translator(
						"Beltrano Antonio",
						"fulano@gmail.com",
						"es_es",
						"pt_br"));
				translatorRepository.save(new Translator(
						"Beltrano Antonio",
						"fulano@gmail.com",
						"en_gb",
						"pt_br"));
			};
		}
	}

}
