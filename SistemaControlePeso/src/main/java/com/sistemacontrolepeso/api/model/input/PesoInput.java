package com.sistemacontrolepeso.api.model.input;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PesoInput {

	private double valor;
	
	private double imc;
	
	private LocalDateTime data;
	
	private PessoaIdInput pessoa;
}
