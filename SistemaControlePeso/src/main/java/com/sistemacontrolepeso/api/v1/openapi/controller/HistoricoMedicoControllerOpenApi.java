package com.sistemacontrolepeso.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import com.sistemacontrolepeso.api.v1.model.HistoricoMedicoModel;
import com.sistemacontrolepeso.api.v1.model.input.HistoricoMedicoInput;
import com.sistemacontrolepeso.core.springdoc.PageableParameter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "HistoricoMedico")
public interface HistoricoMedicoControllerOpenApi {
	
	@PageableParameter
	@Operation(summary = "Lista os Históricos Médicos")
	PagedModel<HistoricoMedicoModel> listar(@Parameter(hidden = true) Pageable pageable);

	@Operation(summary = "Busca um histórico médico por Id",
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID do histórico médico inválido",
									content = @Content(schema = @Schema(ref = "Problema"))
							),
					@ApiResponse(responseCode = "404", description = "Histórico médico não encontrado",
									content = @Content(schema = @Schema(ref = "Problema"))
							)
	})
	HistoricoMedicoModel buscar(@Parameter(description = "ID de um histórico médico", 
								example = "1", required = true) Long id);
	
	@Operation(summary = "Cadastra um histórico médico", description = "Cadastra um histórico médico")
	HistoricoMedicoModel adicionar(@RequestBody(description = "Representação de um novo histórico médico", required = true)
								   HistoricoMedicoInput historicoMedicoInput);
	
	@Operation(summary = "Atualizar um Histórico Médico por ID",
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID do histórico médico inválido",
						content = @Content(schema = @Schema(ref = "Problema"))
					),
					@ApiResponse(responseCode = "404", description = "Histórico médico não encontrado",
							content = @Content(schema = @Schema(ref = "Problema")))
	})
	HistoricoMedicoModel atualizar(@Parameter(description = "ID de um histórico médico", 
								   example = "1", required = true) Long id,
			 					   @RequestBody(description = "Representação de um histórico médico com dados atualizados", 
			 					   required = true)
								   HistoricoMedicoInput historicoMedicoInput);
	
	@Operation(summary = "Excluir um histórico médico por ID",
			responses = {
					@ApiResponse(responseCode = "204"),
					@ApiResponse(responseCode = "400", description = "ID do histórico médico inválido",
								content = @Content(schema = @Schema(ref = "Problema"))
					),
					@ApiResponse(responseCode = "404", description = "Histórico Médico não encontrado",
							content = @Content(schema = @Schema(ref = "Problema"))
					)
	})
	ResponseEntity<Void> remover(@Parameter(description = "ID de um históricom médico",
								 example = "1", required = true) Long id);
}
