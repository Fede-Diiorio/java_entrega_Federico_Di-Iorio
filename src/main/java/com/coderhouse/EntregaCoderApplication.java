package com.coderhouse;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EntregaCoderApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EntregaCoderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
