package com.sistemacontrolepeso;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.sistemacontrolepeso.api.model.DietaModel;
import com.sistemacontrolepeso.domain.model.Alimento;
import com.sistemacontrolepeso.domain.model.Dieta;
import com.sistemacontrolepeso.domain.repository.DietaRepository;
import com.sistemacontrolepeso.domain.service.CadastroDietaService;
import com.sistemacontrolepeso.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CadastroDietaIT {

	@LocalServerPort
	private int port;
	
	@Autowired
	private CadastroDietaService cadastroDietaService;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private DietaRepository dietaRepository;
	
	@BeforeEach
	//@BeforeAll
	public void setUp() {
		RestAssured.port = port;
		RestAssured.basePath = "/dietas";
		
		databaseCleaner.clearTables();
		prepararDados();
		atualizarDados();
		apagarDados();
	}
	
	@Test
	@Order(1)
	public void deveRetornarStatus201_QuandoCadastrarDieta() {
		given()
		.body("{ \"nome\": \"Café da Manhã\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	@Order(2)
	public void deveRetornarNumerodeDietasMaiorQueZero_QuandoListarDietas() {
		Response response = given().accept(ContentType.JSON).when().get();
		
		JsonPath resp = response.then().extract().jsonPath();
		int results = resp.getList("id").size();
		
		assertTrue(results > 0, "O resultado não é maior que 0");
	}
	
	@Test
	@Order(3)
	public void deveConter1Dieta_QuandoConsultarDietaComId() {
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
	@Order(4)
	public void deveRetornarStatus200_QuandoAtualiazarUmaDieta() {
		given()
			.body("{ \"id\": \"1\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("id", 1l)
		.when()
			.put("/{id}")
		.then()
			.statusCode(HttpStatus.OK.value());
		
	}
	
	@Test
	@Order(5)
	public void deveRetornarStatus204_QuandoApagarUmaDieta() {
		given()
			.accept(ContentType.JSON)
			.pathParam("id", 2L)
		.when()
			.delete("/{id}")
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	void prepararDados() {
		Dieta dieta = new Dieta();
		
		dieta.setNome("Café da Manhã");
		dieta.setDataCadastro(LocalDateTime.now());
		
		Dieta dieta2 = new Dieta();
		
		dieta2.setNome("Almoço");
		dieta2.setDataCadastro(LocalDateTime.now());
		
		cadastroDietaService.salvar(dieta);
		cadastroDietaService.salvar(dieta2);
	}
	
	void atualizarDados() {
		Dieta dieta = cadastroDietaService.buscarOuFalhar(1L);
		
		dieta.setNome("Café da Manhã Tarde");
		dieta.setDataCadastro(dieta.getDataCadastro());
		dieta.setDataAtualizacao(LocalDateTime.now());
		
		cadastroDietaService.salvar(dieta);
	}
	
	void apagarDados() {
		Dieta dieta = cadastroDietaService.buscarOuFalhar(2L);
		
		dietaRepository.delete(dieta);
	}
	
}