package com.calebematos.algafood;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.calebematos.algafood.core.io.Base64ProtocolResolver;
import com.calebematos.algafood.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgafoodApiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		var app = new SpringApplication(AlgafoodApiApplication.class);
		app.addListeners(new Base64ProtocolResolver());
		app.run(args);
	}

}
