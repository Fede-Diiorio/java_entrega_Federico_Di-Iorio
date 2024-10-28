package com.coderhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coderhouse.helpers.DatabaseLoader;

@SpringBootApplication
public class EntregaCoderApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(EntregaCoderApplication.class, args);
	}
	
	@Autowired DatabaseLoader dbl;

	@Override
	public void run(String... args) throws Exception {
		dbl.loadCategories();
		dbl.loadProducts();
	}

}
