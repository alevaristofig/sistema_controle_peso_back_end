package com.sistemacontrolepeso.api.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExercicioModel {

	private Long id;
	
	private String nome;
	
	private Long tempo;
	
	private int frequencia;
	
	private LocalDateTime dataCadastro;
	
	private LocalDateTime dataAtualizar;
}