package com.sistemacontrolepeso.api.v1.openapi.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import com.sistemacontrolepeso.api.v1.model.ExercicioModel;
import com.sistemacontrolepeso.api.v1.model.input.ExercicioInput;
import com.sistemacontrolepeso.core.springdoc.PageableParameter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Exercicio")
public interface ExercicioControllerOpenApi {
	
	@PageableParameter
	@Operation(summary = "Lista os exercícios")
	PagedModel<ExercicioModel> listar(@Parameter(hidden = true) Pageable pageable);
	
	@Operation(summary = "Lista os exercícios sem paginação")
	List<ExercicioModel> listar();

	@Operation(summary = "Busca um exercício por Id",
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID do exercício inválido",
									content = @Content(schema = @Schema(ref = "Problema"))
							),
					@ApiResponse(responseCode = "404", description = "Exercício não encontrado",
									content = @Content(schema = @Schema(ref = "Problema"))
							)
	})
	ExercicioModel buscar(@Parameter(description = "ID de um exercício", example = "1", required = true) Long id);
	
	@Operation(summary = "Cadastra um Exercício", description = "Cadastra um Exercício")
	ExercicioModel salvar(@RequestBody(description = "Representação de um novo Exercício", required = true)
						  ExercicioInput exercicioInput);
	
	@Operation(summary = "Atualizar um Exercício por ID",
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID do Exercício inválido",
						content = @Content(schema = @Schema(ref = "Problema"))
					),
					@ApiResponse(responseCode = "404", description = "Exercício não encontrado",
							content = @Content(schema = @Schema(ref = "Problema")))
	})
	ExercicioModel atualizar(@Parameter(description = "ID de um Exercício", example = "1", required = true) Long id,
			@RequestBody(description = "Representação de um exercício com dados atualizados", required = true)
			ExercicioInput exercicioInput);
	
	@Operation(summary = "Excluir um exercício por ID",
			responses = {
					@ApiResponse(responseCode = "204"),
					@ApiResponse(responseCode = "400", description = "ID do exercício inválido",
								content = @Content(schema = @Schema(ref = "Problema"))
					),
					@ApiResponse(responseCode = "404", description = "Exercício não encontrado",
							content = @Content(schema = @Schema(ref = "Problema"))
					)
	})
	ResponseEntity<Void> remover(@Parameter(description = "ID de um exercício", 
		example = "1", required = true) Long id);
}
