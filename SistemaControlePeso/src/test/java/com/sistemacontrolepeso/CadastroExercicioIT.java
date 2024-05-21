package com.sistemacontrolepeso;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import org.hamcrest.Matcher;
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

import com.sistemacontrolepeso.domain.model.Exercicio;
import com.sistemacontrolepeso.domain.repository.ExercicioRepository;
import com.sistemacontrolepeso.domain.service.CadastroExercicioService;
import com.sistemacontrolepeso.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroExercicioIT extends SistemaControlePesoApplicationTests {
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CadastroExercicioService cadastroExercicio;
	
	@Autowired
	private ExercicioRepository exercicioRepository;

	@LocalServerPort
	private int port;
	
	private String jsonExercicioAtualizar;
	
	@BeforeEach
	public void setUp() {
		RestAssured.port = port;
		RestAssured.basePath = "/exercicio";
		
		jsonExercicioAtualizar = com.sistemacontrolepeso.util.ResourceUtils.getContentFromResource("/json/exercicio_atualizar.json");
		
		databaseCleaner.clearTables();
		prepararDados();
		atualizarDados();
		//deletarExercicio();
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarExercicio() {
		given()
			.body("{ \"id\": \"1\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornar1Exercicio_QuandoListarExercicios() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(1));
	}
	
	@Test
	public void deveConter1Exercicio_QuandoConsultarExercicioComId() {
		given()
			.accept(ContentType.JSON)
			.pathParam("id", 1L)
		.when()
			.get("/{id}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("id", equalTo(1));
	}
	
	@Test
	public void deveRetornarStatus200_QuandoAtualiazarUmExercicio() {
		given()
			.body("{ \"id\": \"1\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("exercicioId", 1l)
		.when()
			.put("/{exercicioId}")
		.then()
			.statusCode(HttpStatus.OK.value());
		
	}
	
	@Test
	public void deveRetornarStatus204_QuandoApagarUmExercicio() {
		given()
			.accept(ContentType.JSON)
			.pathParam("exercicioId", 1L)
		.when()
			.delete("/{exercicioId}")
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	void prepararDados() {
		Exercicio exercicio = new Exercicio();
		exercicio.setNome("Esteira");
		exercicio.setTempo(45L);
		exercicio.setFrequencia(3);
		exercicio.setDataCadastro(OffsetDateTime.now());
		exercicio.setDataAtualizacao(null);
		
		cadastroExercicio.salvar(exercicio);
	}
	
	public void atualizarDados() {
		Exercicio exercicio = cadastroExercicio.buscarOuFalhar(1L);
		
		exercicio.setNome("Caminhada Esteira");
		exercicio.setTempo(55L);
		exercicio.setFrequencia(5);
		exercicio.setDataCadastro(OffsetDateTime.now());
		exercicio.setDataAtualizacao(OffsetDateTime.now());
		
		cadastroExercicio.salvar(exercicio);
	}
	
	public void deletarExercicio() {
		Exercicio exercicio = cadastroExercicio.buscarOuFalhar(1L);
		
		exercicioRepository.delete(exercicio);
	}
	
}
