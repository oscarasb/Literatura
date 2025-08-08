package com.oasb.literatura.API;

import com.oasb.literatura.API.Service.AutorService;
import com.oasb.literatura.API.Service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private LibroService libroService;

	@Autowired
	private AutorService autorService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) {
		com.oasb.literatura.API.Principal.Principal principal = new com.oasb.literatura.API.Principal.Principal(libroService, autorService);
		principal.muestraElMenu();
	}


}