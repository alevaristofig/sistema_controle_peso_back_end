package com.sistemacontrolepeso.domain.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaExercicio {

	private long quantidade;
	private long exercicio_id;
	private String nome;
	
	public PessoaExercicio(long exercicio_id, long quantidade, String nome) {
	//public PessoaExercicio(String nome) {
		this.quantidade = quantidade;
		this.exercicio_id = exercicio_id;
		this.nome = nome;
	}
}