package com.sistemacontrolepeso.api.model.input;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExercicioInput {

	private String nome;
	
	private Long tempo;
	
	private int frequencia;
	
	private LocalDateTime dataCadastro;
	
	private LocalDateTime dataAtualizar;
}
