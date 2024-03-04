package com.sistemacontrolepeso.api.model.input;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlimentoInput {
	
	private String nome;
	
	private String quantidade;
	
	private double calorias;
	
	private LocalDateTime dataCadastro;
	
	private LocalDateTime dataAtualizacao;
}
