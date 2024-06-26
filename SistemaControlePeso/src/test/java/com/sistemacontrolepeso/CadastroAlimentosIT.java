package com.sistemacontrolepeso;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.internal.matchers.GreaterThan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.sistemacontrolepeso.domain.model.Alimento;
import com.sistemacontrolepeso.domain.repository.AlimentoRepositoy;
import com.sistemacontrolepeso.domain.service.CadastroAlimentoService;
import com.sistemacontrolepeso.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroAlimentosIT {

	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CadastroAlimentoService cadastroAlimentoService;
	
	@Autowired
	private AlimentoRepositoy alimentoRepository;
		
	@BeforeEach
	public void setUp() {
		RestAssured.port = port;
		RestAssured.basePath = "/alimentos";
		
		databaseCleaner.clearTables();
		prepararDados();
		atualizarDados();
		apagarDados();
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarAlimento() {
		given()
		.body("{ \"nome\": \"Café com Leite\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornar2Alimento_QuandoListarAlimentos() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(1));
	}
	
	@Test
	public void deveConter1Alimento_QuandoConsultarAlimentoComId() {
		given()
			.accept(ContentType.JSON)
			.pathParam("id", 2L)
		.when()
			.get("/{id}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("id", equalTo(2));
	}
	
	@Test
	public void deveRetornarStatus200_QuandoAtualiazarUmAlimento() {
		given()
			.body("{ \"id\": \"2\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("alimentoId", 2l)
		.when()
			.put("/{alimentoId}")
		.then()
			.statusCode(HttpStatus.OK.value());
		
	}
	
	@Test
	public void deveRetornarStatus204_QuandoApagarUmAlimento() {
		given()
			.accept(ContentType.JSON)
			.pathParam("alimentoId", 2L)
		.when()
			.delete("/{alimentoId}")
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	void prepararDados() {
		Alimento alimento = new Alimento();
		
		alimento.setNome("Café com Leite");
		alimento.setQuantidade("200ml");
		alimento.setCalorias(90L);
		alimento.setDataCadastro(OffsetDateTime.now());
		alimento.setDataAtualizacao(null);
		
		cadastroAlimentoService.salvar(alimento);
		
		Alimento alimento2 = new Alimento();
		
		alimento2.setNome("Pão com Creme de Ricota");
		alimento2.setQuantidade("1");
		alimento2.setCalorias(227);
		alimento2.setDataCadastro(OffsetDateTime.now());
		alimento2.setDataAtualizacao(null);
		
		cadastroAlimentoService.salvar(alimento2);
	}
	
	void atualizarDados() {
		Alimento alimento = cadastroAlimentoService.buscarOuFalhar(1L);
		
		alimento.setNome("Arroz");
		alimento.setQuantidade("2 colheres");
		alimento.setCalorias(45L);
		alimento.setDataCadastro(OffsetDateTime.now());
		alimento.setDataAtualizacao(OffsetDateTime.now());
		
		cadastroAlimentoService.salvar(alimento);
	}
	
	void apagarDados() {
		//Alimento alimento = cadastroAlimentoService.buscarOuFalhar(1L);
		Alimento alimento = new Alimento();
		alimento.setId(2L);
		
		alimentoRepository.delete(alimento);
	}
}
