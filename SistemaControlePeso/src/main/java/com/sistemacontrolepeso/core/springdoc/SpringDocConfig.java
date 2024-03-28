package com.sistemacontrolepeso.core.springdoc;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sistemacontrolepeso.api.exceptionhandler.Problem;

import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class SpringDocConfig {
	
	private static final String badRequestResponse = "BadRequestResponse";
	private static final String notFoundResponse = "NotFoundResponse";
	private static final String notAcceptableResponse = "NotAcceptableResponse";
	private static final String internalServerErrorResponse = "InternalServerErrorResponse";

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Sistema de Controle de Peso")
						.version("v1")
						.description("REST API Sistema de Controle de Peso")
						.license(new License()
								.name("Apache 2.0")
								.url("http://springdoc.com")
						)
				).externalDocs(new ExternalDocumentation()
						.description("Sistema de Controle de Peso")
						.url("https://sistemacontrolpeso.com.br")
				).tags(Arrays.asList(
						new Tag().name("Pessoa").description("Gerencia as pessoas")
				)).components(new Components()
						.schemas(gerarSchemas())
						.responses(gerarResponses())
				);
	}
	
	private Map<String, Schema> gerarSchemas(){
		final Map<String, Schema> schemaMap = new HashMap<>();
		
		Map<String, Schema> problemSchema = ModelConverters.getInstance().read(Problem.class);
		Map<String, Schema> problemObjectSchema = ModelConverters.getInstance().read(Problem.Object.class);
		
		schemaMap.putAll(problemSchema);
		schemaMap.putAll(problemObjectSchema);
		
		return schemaMap;
	}
	
	private Map<String, ApiResponse> gerarResponses(){
		final Map<String, ApiResponse> apiResponseMap = new HashMap<>();
		
		Content content = new Content()
				.addMediaType(APPLICATION_JSON_VALUE, 
						new MediaType().schema(new Schema<Problem>().$ref("Problema")));
		
		apiResponseMap.put(badRequestResponse, new ApiResponse()
                .description("Requisição inválida")
                .content(content));
		
		apiResponseMap.put(notFoundResponse, new ApiResponse()
                .description("Recurso não encontrado")
                .content(content));
		
		apiResponseMap.put(notAcceptableResponse, new ApiResponse()
                .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
                .content(content));
		
		apiResponseMap.put(internalServerErrorResponse, new ApiResponse()
                .description("Erro interno no servidor")
                .content(content));
		
		return apiResponseMap;
	}
}
