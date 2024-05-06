package com.sistemacontrolepeso.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaExercicioInput {

	@Schema(example = "1")
	private PessoaIdInput pessoaId;
	
	@Schema(example = "1")
	private ExercicioIdInput exercicioId;
	
	@Schema(example = "S")
	private char treino;
}
