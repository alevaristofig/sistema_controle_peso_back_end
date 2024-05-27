package com.sistemacontrolepeso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sistemacontrolepeso.core.io.Base64ProtocolResolver;

@SpringBootApplication
public class SistemaControlePesoApplication {

	public static void main(String[] args) {
				
		var app = new SpringApplication(SistemaControlePesoApplication.class);
		app.addListeners(new Base64ProtocolResolver());
		app.run(args);
	}

}
