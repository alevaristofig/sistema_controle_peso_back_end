package com.sistemacontrolepeso.api.model.input;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaExercicioInput {

	private PessoaIdInput pessoaId;
	
	private ExercicioIdInput exercicioId;
	
	private char treino;
	
	private LocalDateTime data;
}
