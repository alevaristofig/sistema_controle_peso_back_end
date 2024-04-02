package com.sistemacontrolepeso.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.sistemacontrolepeso.api.model.PesoModel;
import com.sistemacontrolepeso.api.model.input.PesoInput;
import com.sistemacontrolepeso.api.model.input.PessoaInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Peso")
public interface PesoControllerOpenApi {

	@Operation(summary = "Lista os pesos")
	CollectionModel<PesoModel> listar();
	
	@Operation(summary = "Busca um peso por Id",
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID do peso inválido",
									content = @Content(schema = @Schema(ref = "Problema"))
							),
					@ApiResponse(responseCode = "404", description = "Peso não encontrado",
									content = @Content(schema = @Schema(ref = "Problema"))
							)
	})
	PesoModel buscar(@Parameter(description = "ID de um peso", example = "1", required = true) Long id);
	
	@Operation(summary = "Cadastra um Peso", description = "Cadastra um Peso")
	PesoModel adicionar(@RequestBody(description = "Representação de um novo Peso", required = true)
	  					PesoInput pesoInput);
	
	@Operation(summary = "Atualizar um Peso por ID",
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID do Peso inválido",
						content = @Content(schema = @Schema(ref = "Problema"))
					),
					@ApiResponse(responseCode = "404", description = "Peso não encontrado",
							content = @Content(schema = @Schema(ref = "Problema")))
	})
	PesoModel atualizar(@Parameter(description = "ID de um Peso", example = "1", required = true) Long id,
			@RequestBody(description = "Representação de um peso com dados atualizados", required = true) 
			PesoInput pesoInput);
	
	@Operation(summary = "Excluir um peso por ID",
			responses = {
					@ApiResponse(responseCode = "204"),
					@ApiResponse(responseCode = "400", description = "ID do peso inválido",
								content = @Content(schema = @Schema(ref = "Problema"))
					),
					@ApiResponse(responseCode = "404", description = "Peso não encontrado",
							content = @Content(schema = @Schema(ref = "Problema"))
					)
	})
	ResponseEntity<Void> remover(@Parameter(description = "ID de um peso", example = "1", required = true) Long id);
}
