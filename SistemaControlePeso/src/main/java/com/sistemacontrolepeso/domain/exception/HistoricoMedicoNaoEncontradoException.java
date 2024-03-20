package com.sistemacontrolepeso.domain.exception;

public class HistoricoMedicoNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public HistoricoMedicoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public HistoricoMedicoNaoEncontradoException(Long id) {
		this(String.format("Não existe um cadastro de histórico médico com o código %d", id));
	}
}
