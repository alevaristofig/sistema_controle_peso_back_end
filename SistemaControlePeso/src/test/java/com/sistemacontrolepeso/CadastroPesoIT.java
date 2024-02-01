package com.sistemacontrolepeso;

import static io.restassured.RestAssured.given;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.sistemacontrolepeso.domain.model.Peso;
import com.sistemacontrolepeso.domain.model.Pessoa;
import com.sistemacontrolepeso.domain.service.CadastroPesoService;
import com.sistemacontrolepeso.domain.service.CadastroPessoaService;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroPesoIT extends SistemaControlePesoApplicationTests {

	@LocalServerPort
	private int port;
	
	@Autowired
	private CadastroPessoaService pessoaService;
	
	@Autowired
	private CadastroPesoService pesoService;
	
	@BeforeAll
	public void setUp() {
		RestAssured.port = port;
		RestAssured.basePath = "/peso";
		
		prepararDados();
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarPeso() {
		given()
			.body("{ \"pessoa\": \"1\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	void prepararDados() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Alexandre");
		pessoa.setAltura(1.70);
		pessoa.setEmail("alevaristofig@gmail.com");
		pessoa.setEndereco("Rua Anhanguera, 109 - Santa Tereza, 31.015.090, Belo Horizonte MG");
		pessoa.setData(new Date());
		
		pessoaService.salvar(pessoa);
		
		Pessoa pessoaSalva = pessoaService.buscarOuFalhar(1L);
		
		Peso peso = new Peso();
		peso.setPessoa(pessoaSalva);
		peso.setValor(100.5);
		peso.setImc(30);
		peso.setData(new Date());
		
		pesoService.salvar(peso);
		
	}
}
