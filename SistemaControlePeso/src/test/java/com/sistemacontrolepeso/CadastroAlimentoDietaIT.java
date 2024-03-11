package com.sistemacontrolepeso;

import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.sistemacontrolepeso.domain.model.Alimento;
import com.sistemacontrolepeso.domain.model.AlimentoDieta;
import com.sistemacontrolepeso.domain.model.Dieta;
import com.sistemacontrolepeso.domain.service.CadastroAlimentoDietaService;
import com.sistemacontrolepeso.domain.service.CadastroAlimentoService;
import com.sistemacontrolepeso.domain.service.CadastroDietaService;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroAlimentoDietaIT {

	@LocalServerPort
	private int port;
	
	@Autowired
	private CadastroAlimentoService cadastroAlimentoService;
	
	@Autowired
	private CadastroDietaService cadastroDietaService;
	
	@Autowired
	private CadastroAlimentoDietaService cadastroAlimentoDietaService;
	
	@BeforeEach
	public void setUp() {
		RestAssured.port = port;
		RestAssured.basePath = "/alimentodieta";
		
		prepararDados();
	}
	
	@Test
	public void deverRetornarStatus204_QuandoCadastrarAlimentosParaDieta() {
		given()
			.body("{ \"id\": \"1\" }")
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());			
	}
	
	void prepararDados() {
		Alimento alimento = new Alimento();
		alimento.setNome("Café com Leite");
		alimento.setQuantidade("200ml");
		alimento.setCalorias(90L);
		alimento.setDataCadastro(LocalDateTime.now());
		alimento.setDataAtualizacao(null);
		
		cadastroAlimentoService.salvar(alimento);
		
		Dieta dieta = new Dieta();
		
		dieta.setNome("Café da Manhã");
		dieta.setDataCadastro(LocalDateTime.now());
		
		cadastroDietaService.salvar(dieta);
		
		AlimentoDieta alimentoDieta = new AlimentoDieta();
		alimentoDieta.setAlimento(alimento);
		alimentoDieta.setDieta(dieta);
		alimentoDieta.setDataCriacao(LocalDateTime.now());
		alimentoDieta.setDataAtualizacao(null);
		
		cadastroAlimentoDietaService.salvar(alimentoDieta);
	}
}