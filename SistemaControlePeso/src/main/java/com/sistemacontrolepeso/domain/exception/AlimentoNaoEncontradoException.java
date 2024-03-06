package com.sistemacontrolepeso.domain.exception;

public class AlimentoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public AlimentoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public AlimentoNaoEncontradoException(Long alimentoId) {
		this(String.format("Não existe um cadastro de alimento com o código %d", alimentoId));
	}
}