package com.sistemacontrolepeso.api.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DietaModel {

	private Long id;
	
	private String nome;
	
	private LocalDateTime dataCadastro;
	
	private LocalDateTime dataAtualizacao;
}
