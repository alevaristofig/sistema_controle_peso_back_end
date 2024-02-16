package com.sistemacontrolepeso.api.model;

import java.time.LocalDateTime;
import java.util.Date;

import com.sistemacontrolepeso.api.model.input.PessoaIdInput;
import com.sistemacontrolepeso.domain.model.Pessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PesoModel {

	private Long id;
	
	private double valor;
	
	private double imc;
	
	private LocalDateTime data;
	
	private PessoaModel pessoa;
}
