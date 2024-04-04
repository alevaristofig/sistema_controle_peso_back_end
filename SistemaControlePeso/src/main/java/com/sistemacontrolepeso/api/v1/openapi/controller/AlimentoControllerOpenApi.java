package com.sistemacontrolepeso.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import com.sistemacontrolepeso.api.model.AlimentoModel;
import com.sistemacontrolepeso.api.model.input.AlimentoInput;
import com.sistemacontrolepeso.core.springdoc.PageableParameter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Alimento")
public interface AlimentoControllerOpenApi {
	
	@PageableParameter
	@Operation(summary = "Lista os alimentos")
	PagedModel<AlimentoModel> listar(@Parameter(hidden = true) Pageable pageable);
	
	@Operation(summary = "Busca um alimento por Id",
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID do alimento inválido",
									content = @Content(schema = @Schema(ref = "Problema"))
							),
					@ApiResponse(responseCode = "404", description = "Alimento não encontrado",
									content = @Content(schema = @Schema(ref = "Problema"))
							)
	})
	AlimentoModel buscar(@Parameter(description = "ID de um alimento", example = "1", required = true) Long id);
	
	@Operation(summary = "Cadastra um Alimento", description = "Cadastra um Alimento")
	AlimentoModel adicionar(@RequestBody(description = "Representação de um novo Alimento", required = true)
							 AlimentoInput alimentoInput);
	
	@Operation(summary = "Atualizar um Alimento por ID",
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID do Alimento inválido",
						content = @Content(schema = @Schema(ref = "Problema"))
					),
					@ApiResponse(responseCode = "404", description = "Alimento não encontrado",
							content = @Content(schema = @Schema(ref = "Problema")))
	})
	AlimentoModel atualizar(@Parameter(description = "ID de um Alimento", example = "1", required = true) Long id,
							@RequestBody(description = "Representação de um alimento com dados atualizados", required = true) 
						    AlimentoInput alimentoInput);
	
	@Operation(summary = "Excluir um alimento por ID",
			responses = {
					@ApiResponse(responseCode = "204"),
					@ApiResponse(responseCode = "400", description = "ID do alimento inválido",
								content = @Content(schema = @Schema(ref = "Problema"))
					),
					@ApiResponse(responseCode = "404", description = "Alimento não encontrado",
							content = @Content(schema = @Schema(ref = "Problema"))
					)
	})
	ResponseEntity<Void> remover(@Parameter(description = "ID de um alimento", example = "1", required = true) Long id);
}