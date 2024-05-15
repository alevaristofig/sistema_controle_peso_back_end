package com.sistemacontrolepeso.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.sistemacontrolepeso.api.v1.model.PessoaExercicioModel;
import com.sistemacontrolepeso.api.v1.model.input.PessoaExercicioInput;
import com.sistemacontrolepeso.core.springdoc.PageableParameter;
import com.sistemacontrolepeso.domain.model.PessoaExercicio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "PessoaExercicio")
public interface PessoaExercicioControllerOpenApi {
	
	@PageableParameter
	@Operation(summary = "Lista as Pessoas/Exercícios")
	PagedModel<PessoaExercicioModel> listar(@Parameter(description = "ID de uma pessoa", example = "1", required = true) Long id,
			@Parameter(hidden = true) Pageable pageable);

	@Operation(summary = "Cadastra um exercício para a pessoa", description = "Cadastra um exercício para a pessoa")
	PessoaExercicio adicionar(@RequestBody(description = "Representação de uma nova PessoaExercicio", required = true)
								PessoaExercicioInput pessoaExercicioInput);
}
