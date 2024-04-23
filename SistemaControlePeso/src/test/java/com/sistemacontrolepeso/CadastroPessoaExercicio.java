package com.sistemacontrolepeso;

import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.sistemacontrolepeso.domain.model.Exercicio;
import com.sistemacontrolepeso.domain.model.Pessoa;
import com.sistemacontrolepeso.domain.model.PessoaExercicio;
import com.sistemacontrolepeso.domain.service.CadastroExercicioService;
import com.sistemacontrolepeso.domain.service.CadastroPessoaExercicioService;
import com.sistemacontrolepeso.domain.service.CadastroPessoaService;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroPessoaExercicio extends SistemaControlePesoApplicationTests {

	@LocalServerPort
	private int port;
	
	@Autowired
	private CadastroPessoaService pessoaService;
	
	@Autowired
	private CadastroExercicioService cadastroExercicio;
	
	@Autowired
	private CadastroPessoaExercicioService cadastroPessoaExercicioService;
	
	@BeforeAll
	public void setUp() {
		RestAssured.port = port;
		RestAssured.basePath = "/pessoaexercicio";
	}
	
	@Test
	public void deverRetornarStatus204_QuandoCadastrarExercicioParaPessoa() {
		given()
			.body("{ \"id\": \"1\" }")
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
			
	}
	
	@Test
	public void deverRetornar1PessoaExercicio_QuandoListarPessoaExercicio() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(1));
	}
	
	void prepararDados() {
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Alexandre");
		pessoa.setAltura(1.70);
		pessoa.setEmail("alevaristofig@gmail.com");
		pessoa.setEndereco("Rua Anhanguera, 109 - Santa Tereza, 31.015.090, Belo Horizonte MG");
		pessoa.setDataCadastro(OffsetDateTime.now());
		
		pessoaService.salvar(pessoa);
		
		Exercicio exercicio = new Exercicio();
		exercicio.setNome("Esteira");
		exercicio.setTempo(45L);
		exercicio.setFrequencia(3);
		exercicio.setDataCadastro(LocalDateTime.now());
		exercicio.setDataAtualizar(null);
		
		cadastroExercicio.salvar(exercicio);
		
		PessoaExercicio pessoaExercicio = new PessoaExercicio();
		pessoaExercicio.setPessoa(pessoa);
		pessoaExercicio.setExercicio(exercicio);
		pessoaExercicio.setTreino('S');
		pessoaExercicio.setDataCadastro(OffsetDateTime.now());
		
		cadastroPessoaExercicioService.salvar(pessoaExercicio);
	}
}
