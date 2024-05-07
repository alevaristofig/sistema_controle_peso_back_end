package com.sistemacontrolepeso.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.sistemacontrolepeso.api.model.PessoaModel;
import com.sistemacontrolepeso.api.model.input.PessoaInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pessoa")
public interface PessoaControllerOpenApi {

	@Operation(summary = "Lista as pessoas")
	CollectionModel<PessoaModel> listar();
	
	@Operation(summary = "Busca uma pessoa por Id",
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID da pessoa inválida",
									content = @Content(schema = @Schema(ref = "Problema"))
							),
					@ApiResponse(responseCode = "404", description = "Pessoa não encontrada",
									content = @Content(schema = @Schema(ref = "Problema"))
							)
	})
	PessoaModel buscar(@Parameter(description = "ID de uma pessoa", example = "1", required = true) Long id);
	
	@Operation(summary = "Cadastra uma Pessoa", description = "Cadastra uma Pessoa")
	PessoaModel adicionar(@RequestBody(description = "Representação de uma nova Pessoa", required = true)
						  PessoaInput pessoaInput);
	
	@Operation(summary = "Atualizar um Pessoa por ID",
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID da Pessoa inválida",
						content = @Content(schema = @Schema(ref = "Problema"))
					),
					@ApiResponse(responseCode = "404", description = "Pessoa não encontrada",
							content = @Content(schema = @Schema(ref = "Problema")))
	})
	PessoaModel atualizar(@Parameter(description = "ID de uma Pessoa", example = "1", required = true) Long id,
			@RequestBody(description = "Representação de uma pessoa com dados atualizados", required = true) 
			PessoaInput pessoaInput);
}
