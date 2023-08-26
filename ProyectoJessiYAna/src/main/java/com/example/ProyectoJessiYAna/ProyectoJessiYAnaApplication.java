package com.example.ProyectoJessiYAna;

import com.example.ProyectoJessiYAna.repository.BD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoJessiYAnaApplication {

	public static void main(String[] args) {
		BD.crearTablas();
		SpringApplication.run(ProyectoJessiYAnaApplication.class, args);
	}

}
