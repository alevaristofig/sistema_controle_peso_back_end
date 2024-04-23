package com.sistemacontrolepeso.api.model.input;

import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaInput {

	@Schema(example = "João da Silva")
	@NotBlank
	public String nome;
	
	@Schema(example = "joaosilva@email.com.br")
	@NotBlank
	private String email;	
	
	@Schema(example = "Praça Sete de Setembro, s/n - Centro, Belo Horizonte - MG - 30160-041 ")
	@NotBlank
	private String endereco;
	
	@Schema(example = "1.70")
	@NotNull
	private double altura;
	
	@Schema(example = "$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W")
	@NotBlank
	private String senha;
	
	@Schema(example = "22/04/2024")
	@NotNull
	private OffsetDateTime dataCadastro;
	
	@Schema(example = "22/04/2024")
	private OffsetDateTime dataAtualizacao;
}
