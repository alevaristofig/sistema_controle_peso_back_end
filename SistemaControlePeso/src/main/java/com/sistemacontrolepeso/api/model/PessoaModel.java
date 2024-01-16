package com.sistemacontrolepeso.api.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaModel {

	public Long id;
	
	public String nome;
	
	private String email;	
	
	private String endereco;
	
	private Double altura;
	
	private Date data;
}
