package com.sistemacontrolepeso.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import com.sistemacontrolepeso.api.v1.model.DietaModel;
import com.sistemacontrolepeso.api.v1.model.input.DietaInput;
import com.sistemacontrolepeso.core.springdoc.PageableParameter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Dieta")
public interface DietaControllerOpenApi {
	
	@PageableParameter
	@Operation(summary = "Lista as Dietas")
	PagedModel<DietaModel> listar(@Parameter(description = "ID de uma pessoa", example = "1", required = true) Long id,
			@Parameter(hidden = true) Pageable pageable);

	@Operation(summary = "Busca uma dieta por Id",
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID da dieta inválido",
									content = @Content(schema = @Schema(ref = "Problem"))
							),
					@ApiResponse(responseCode = "404", description = "Dieta não encontrada",
									content = @Content(schema = @Schema(ref = "Problem"))
							)
	})
	DietaModel buscar(@Parameter(description = "ID de uma dieta", example = "1", required = true) Long id);
	
	@Operation(summary = "Cadastra uma Dieta", description = "Cadastra uma Dieta")
	DietaModel adicionar(@RequestBody(description = "Representação de uma nova dieta", required = true)
						 DietaInput dietaInput);
	
	@Operation(summary = "Atualizar uma Dieta por ID",
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID da Dieta inválida",
						content = @Content(schema = @Schema(ref = "Problem"))
					),
					@ApiResponse(responseCode = "404", description = "Dieta não encontrada",
							content = @Content(schema = @Schema(ref = "Problem")))
	})
	DietaModel atualizar(@Parameter(description = "ID de uma Dieta", example = "1", required = true) Long id,
						 @RequestBody(description = "Representação de uma dieta com dados atualizados", required = true)
						 DietaInput dietaInput);
	
	@Operation(summary = "Excluir uma dieta por ID",
			responses = {
					@ApiResponse(responseCode = "204"),
					@ApiResponse(responseCode = "400", description = "ID da dieta inválida",
								content = @Content(schema = @Schema(ref = "Problem"))
					),
					@ApiResponse(responseCode = "404", description = "Dieta não encontrado",
							content = @Content(schema = @Schema(ref = "Problem"))
					)
	})
	ResponseEntity<Void> remover(@Parameter(description = "ID de uma dieta", example = "1", required = true) Long id);
}
