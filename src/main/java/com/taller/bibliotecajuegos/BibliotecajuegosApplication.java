package com.taller.bibliotecajuegos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la API REST de la biblioteca de videojuegos.
 *
 * @author Sistema Biblioteca de videojuegos
 * @version 1.0
 */
@SpringBootApplication
public class BibliotecajuegosApplication {

	/**
	 * Arranca el contexto de Spring Boot.
	 *
	 * @param args argumentos de línea de comandos
	 */
	public static void main(String[] args) {
		SpringApplication.run(BibliotecajuegosApplication.class, args);
	}

}
