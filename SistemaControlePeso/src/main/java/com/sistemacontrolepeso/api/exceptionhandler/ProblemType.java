package com.sistemacontrolepeso.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = "http://localhost"+path;
		this.title = title;
	}
}
