package com.sistemacontrolepeso.domain.exception;

public class ExercicioNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public ExercicioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public ExercicioNaoEncontradoException(Long id) {
		this(String.format("Não existe um cadastro de exercício com o código %d", id));
	}
}
