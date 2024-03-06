package com.sistemacontrolepeso.api.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlimentoModel {

	private Long id;
	
	private String nome;
	
	private String quantidade;
	
	private double calorias;
	
	private LocalDateTime dataCadastro;
	
	private LocalDateTime dataAtualizacao;
}