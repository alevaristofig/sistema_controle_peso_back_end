package com.sistemacontrolepeso.api.v1.model;

import java.time.OffsetDateTime;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaModel extends RepresentationModel<PessoaModel> {

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "João da Silva")
	private String nome;
	
	@Schema(example = "joaosilva@email.com.br")
	private String email;	
	
	@Schema(example = "Praça Sete de Setembro, s/n - Centro, Belo Horizonte - MG - 30160-041 ")
	private String endereco;
	
	@Schema(example = "1.70")
	private Double altura;
	
	@Schema(example = "$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W")
	private String senha;
	
	@Schema(example = "28/03/2024")
	private OffsetDateTime dataCadastro;
	
	@Schema(example = "28/03/2024")
	private OffsetDateTime dataAtualizacao;
}
