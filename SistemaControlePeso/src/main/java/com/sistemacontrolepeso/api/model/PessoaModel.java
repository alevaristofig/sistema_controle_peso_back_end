package com.sistemacontrolepeso.api.model;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaModel extends RepresentationModel<PessoaModel> {

	@Schema(example = "1")
	public Long id;
	
	@Schema(example = "João da Silva")
	public String nome;
	
	@Schema(example = "joaosilva@email.com.br")
	private String email;	
	
	@Schema(example = "Praça Sete de Setembro, s/n - Centro, Belo Horizonte - MG - 30160-041 ")
	private String endereco;
	
	@Schema(example = "1.70")
	private Double altura;
	
	@Schema(example = "28/03/2024")
	private Date data;
}
