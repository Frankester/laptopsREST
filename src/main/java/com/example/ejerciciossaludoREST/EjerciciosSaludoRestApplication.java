package com.example.ejerciciossaludoREST;

import com.example.ejerciciossaludoREST.entities.Laptop;
import com.example.ejerciciossaludoREST.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EjerciciosSaludoRestApplication {

	public static void main(String[] args) {
		/*ApplicationContext context =*/ SpringApplication.run(EjerciciosSaludoRestApplication.class, args);

		/*LaptopRepository repository = (LaptopRepository) context.getBean(LaptopRepository.class);

		Laptop laptop = new Laptop(null,"1920 x 1080","HP P22v G4", 29999.00,true);
		repository.save(laptop);*/
	}

}
