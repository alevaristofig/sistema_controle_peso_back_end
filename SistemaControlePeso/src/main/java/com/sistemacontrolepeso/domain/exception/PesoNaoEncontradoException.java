package com.sistemacontrolepeso.domain.exception;

public class PesoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public PesoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public PesoNaoEncontradoException(Long id) {
		this(String.format("Não existe um cadastro de peso com o código %d", id));
	}
}
