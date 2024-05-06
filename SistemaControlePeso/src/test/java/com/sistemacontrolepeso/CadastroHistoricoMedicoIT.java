package com.sistemacontrolepeso;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.sistemacontrolepeso.domain.model.Dieta;
import com.sistemacontrolepeso.domain.model.HistoricoMedico;
import com.sistemacontrolepeso.domain.model.Pessoa;
import com.sistemacontrolepeso.domain.repository.HistoricoMedicoRepository;
import com.sistemacontrolepeso.domain.service.CadastroHistoricoMedicoService;
import com.sistemacontrolepeso.domain.service.CadastroPessoaService;
import com.sistemacontrolepeso.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CadastroHistoricoMedicoIT {

	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CadastroPessoaService cadastroPessoaService;
	
	@Autowired
	private CadastroHistoricoMedicoService cadastroHistoricoMedico;
	
	@Autowired
	private HistoricoMedicoRepository historicoMedicoRepository;
	
	
	@BeforeEach
	public void setUp() {
		RestAssured.port = port;
		RestAssured.basePath = "/historicomedico";
		
		databaseCleaner.clearTables();
		prepararDados();
		atualizarDados();
		deletarDados();
	}
	

	@Test
	@Order(1)
	public void deveRetornarStatus201_QuandoCadastrarHistoricoMedico() {
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
	@Order(2)
	public void deveRetornarNumeroDeHistoricosMedicoMaiorQueZero_QuandoListarHistoricosMedicos() {
		Response response = given().accept(ContentType.JSON).when().get();
		
		JsonPath resp = response.then().extract().jsonPath();
		int results = resp.getList("id").size();
		
		assertTrue(results > 0, "O resultado não é maior que 0");
	}
	
	@Test
	@Order(3)
	public void deveConter1HistoricoMedico_QuandoConsultarHistoricoMedicoComId() {
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
	public void deveRetornarStatus200_QuandoAtualiazarUmHistoricoMedico() {
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
	public void deveRetornarStatus204_QuandoApagarHistoricoMedico() {
		given()
			.accept(ContentType.JSON)
			.pathParam("id", 1L)
		.when()
			.delete("/{id}")
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	
	private void prepararDados() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Alexandre");
		pessoa.setAltura(1.70);
		pessoa.setEmail("alevaristofig@gmail.com");
		pessoa.setEndereco("Rua Anhanguera, 109 - Santa Tereza, 31.015.090, Belo Horizonte MG");
		pessoa.setDataCadastro(OffsetDateTime.now());
		
		cadastroPessoaService.salvar(pessoa);
		
		HistoricoMedico historicoMedico = new HistoricoMedico();
		historicoMedico.setDescricao("Colesterol Alto");
		historicoMedico.setRemedio("Sinvastatina");
		historicoMedico.setDataCadastro(OffsetDateTime.now());
		historicoMedico.setDataAtualizacao(null);
		historicoMedico.setPessoa(pessoa);	
		
		HistoricoMedico historicoMedico2 = new HistoricoMedico();
		historicoMedico2.setDescricao("Obsidade");
		historicoMedico2.setRemedio("Ozempic");
		historicoMedico2.setDataCadastro(OffsetDateTime.now());
		historicoMedico2.setDataAtualizacao(null);
		historicoMedico2.setPessoa(pessoa);			
		
		cadastroHistoricoMedico.salvar(historicoMedico);
		cadastroHistoricoMedico.salvar(historicoMedico2);
	}
	
	void atualizarDados() {
		Pessoa pessoa = cadastroPessoaService.buscarOuFalhar(1L);
		HistoricoMedico historicoMedico = cadastroHistoricoMedico.buscarOuFalhar(1L);
		
		historicoMedico.setDescricao("Diabetes");
		historicoMedico.setRemedio("Insulina");
		historicoMedico.setDataAtualizacao(OffsetDateTime.now());
		historicoMedico.setDataCadastro(OffsetDateTime.now());
		historicoMedico.setPessoa(pessoa);
		
		cadastroHistoricoMedico.salvar(historicoMedico);
	}
	
	void deletarDados() {
		HistoricoMedico historicoMedico = cadastroHistoricoMedico.buscarOuFalhar(2L);
		
		historicoMedicoRepository.delete(historicoMedico);
	}
}
