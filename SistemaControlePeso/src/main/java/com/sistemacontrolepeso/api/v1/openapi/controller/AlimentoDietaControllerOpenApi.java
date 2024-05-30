package com.sistemacontrolepeso.api.v1.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.sistemacontrolepeso.api.v1.model.AlimentoDietaModel;
import com.sistemacontrolepeso.api.v1.model.input.AlimentoDietaInput;
import com.sistemacontrolepeso.domain.model.AlimentoDieta;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "AlimentoDieta")
public interface AlimentoDietaControllerOpenApi {

	@Operation(summary = "Busca os alimentos por Id do Alimento/Dieta",
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID do alimento/dieta inválido",
									content = @Content(schema = @Schema(ref = "Problem"))
							),
					@ApiResponse(responseCode = "404", description = "Alimento/Dieta não encontrado",
									content = @Content(schema = @Schema(ref = "Problem"))
							)
	})
	List<AlimentoDietaModel> buscar(@Parameter(description = "ID de uma dieta", example = "1", required = true) Long id);
	
	@Operation(summary = "Cadastra os alimentos para a Dieta", description = "Cadastra os alimentos para a Dieta")
	AlimentoDieta adicionar(@RequestBody(description = "Representação de um novo alimento/dieta", required = true)
			AlimentoDietaInput alimentoDietaInput);
	
	@Operation(summary = "Atualizar os alimentos da dieta por ID",
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID do alimento/dieta inválido",
						content = @Content(schema = @Schema(ref = "Problem"))
					),
					@ApiResponse(responseCode = "404", description = "Alimento/Dieta não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
	})
	AlimentoDietaModel atualizar(@Parameter(description = "ID de um alimento/dieta", example = "1", required = true) Long id,
			 @RequestBody(description = "Representação de um alimento/dieta com dados atualizados", required = true)
						  AlimentoDietaInput alimentoDietaInput);
	
	@Operation(summary = "Excluir um alimento/dieta por ID",
			responses = {
					@ApiResponse(responseCode = "204"),
					@ApiResponse(responseCode = "400", description = "ID do alimento/dieta inválido",
								content = @Content(schema = @Schema(ref = "Problem"))
					),
					@ApiResponse(responseCode = "404", description = "Alimento/Dieta não encontrado",
							content = @Content(schema = @Schema(ref = "Problem"))
					)
	})
	ResponseEntity<Void> remover(@Parameter(description = "ID de um alimento/dieta", example = "1", required = true) Long id);
}
