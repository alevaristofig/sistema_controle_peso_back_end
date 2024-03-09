package com.sistemacontrolepeso.domain.exception;

public class DietaNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public DietaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public DietaNaoEncontradoException(Long id) {
		this(String.format("Não existe um cadastro de dieta com o código %d", id));
	}

}
