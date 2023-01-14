package com.ejercicio.ejercObResJpa;

import com.ejercicio.ejercObResJpa.entities.Laptop;
import com.ejercicio.ejercObResJpa.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;




@SpringBootApplication
public class EjercObResJpaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(EjercObResJpaApplication.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);

		//CRUD


		//Crear laptop
		Laptop laptop1 = new Laptop(null, "Hewlett Packard", "hp elitebook", 1550.0, LocalDate.of(2018, 6, 15));
		Laptop laptop2 = new Laptop(null, "Toshiba", "TO excellent", 1750.0, LocalDate.of(2020, 1, 1));

		//Almacenar un laptop

		System.out.println("Num laptop en base de datos: " + repository.findAll().size());

		repository.save(laptop1);
		repository.save(laptop2);

		//Recuperar todos los laptops

		System.out.println("Num laptop en base de datos: " + repository.findAll().size());

		//Borrar un laptop

		//repository.deleteById(1L);

		System.out.println("Num laptop en base de datos: " + repository.findAll().size());


	}

}
