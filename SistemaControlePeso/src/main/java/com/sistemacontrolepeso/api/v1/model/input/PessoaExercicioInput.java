package com.sistemacontrolepeso.api.v1.model.input;

import java.time.OffsetDateTime;

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
	
	@Schema(example = "28/03/2024")
	private OffsetDateTime dataCadastro;
}
