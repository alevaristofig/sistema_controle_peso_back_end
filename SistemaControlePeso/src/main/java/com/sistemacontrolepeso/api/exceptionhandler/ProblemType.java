package com.sistemacontrolepeso.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de Sistema"),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = "http://localhost"+path;
		this.title = title;
	}
}
