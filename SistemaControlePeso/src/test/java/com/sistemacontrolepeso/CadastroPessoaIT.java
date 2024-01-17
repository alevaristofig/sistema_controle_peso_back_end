package com.sistemacontrolepeso;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.Date;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.sistemacontrolepeso.api.controller.PessoaController;
import com.sistemacontrolepeso.domain.model.Pessoa;
import com.sistemacontrolepeso.domain.repository.PessoaRepository;
import com.sistemacontrolepeso.domain.service.CadastroPessoaService;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroPessoaIT extends SistemaControlePesoApplicationTests {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private CadastroPessoaService pessoaService;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@BeforeAll
	//@BeforeEach
	public void setUp() {		
		RestAssured.port = port;
		RestAssured.basePath = "/pessoas";
		
		deletarDados();	
		prepararDados();		
	}	
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarPessoa() {
		given()
			.body("{ \"nome\": \"Alexandre\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveConter2Pessoas_QuandoConsultarPessoas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(1));
	}
	
	@Test
	public void deveConter1Pessoas_QuandoConsultarPessoasComId() {
		given()
			.accept(ContentType.JSON)
			.pathParam("pessoaId", 1)
		.when()
			.get("/{pessoaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo("Alexandre"));
	}
	
	void prepararDados() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Alexandre");
		pessoa.setAltura(1.70);
		pessoa.setEmail("alevaristofig@gmail.com");
		pessoa.setEndereco("Rua Anhanguera, 109 - Santa Tereza, 31.015.090, Belo Horizonte MG");
		pessoa.setData(new Date());
		
		Pessoa pessoa2 = new Pessoa();
		pessoa2.setNome("Adriane");
		pessoa2.setAltura(1.72);
		pessoa2.setEmail("adrianeef@yahoo.com.br");
		pessoa2.setEndereco("Rua Anhanguera, 109 - Santa Tereza, 31.015.090, Belo Horizonte MG");
		pessoa2.setData(new Date());
		
		pessoaService.salvar(pessoa);
		pessoaService.salvar(pessoa2);
		
	}
	
	void deletarDados() {
		pessoaRepository.deleteAll();
	}
}
