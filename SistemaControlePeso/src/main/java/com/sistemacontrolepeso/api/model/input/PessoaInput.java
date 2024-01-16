package com.sistemacontrolepeso.api.model.input;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaInput {

	public String nome;
	
	private String email;	
	
	private String endereco;
	
	private Double altura;
	
	private Date data;
}
