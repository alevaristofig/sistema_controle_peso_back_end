package com.sistemacontrolepeso.api.model;

import java.time.LocalDateTime;

import com.sistemacontrolepeso.api.model.input.PessoaIdInput;
import com.sistemacontrolepeso.domain.model.Exercicio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaExercicioModel {

	private Long id;
	
	private PessoaIdInput pessoaId;
	
	private Exercicio exercicio;
	
	private char treino;
	
	private LocalDateTime data;
}
