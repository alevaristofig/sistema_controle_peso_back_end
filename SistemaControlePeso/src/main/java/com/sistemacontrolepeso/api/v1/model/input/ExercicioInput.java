package com.sistemacontrolepeso.api.v1.model.input;

import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExercicioInput {

	@Schema(example = "Esteira")
	private String nome;
	
	@Schema(example = "45")
	private Long tempo;
	
	@Schema(example = "3")
	private int frequencia;
	
	@Schema(example = "João da Silva")
	private PessoaIdInput pessoa;
	
	@Schema(example = "28/03/2024")
	private OffsetDateTime dataCadastro;
	
	@Schema(example = "03/04/2024")
	private OffsetDateTime dataAtualizar;
}
