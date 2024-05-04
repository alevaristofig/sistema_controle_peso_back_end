package com.sistemacontrolepeso.api.model.input;

import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DietaInput {

	@Schema(example = "Café da Manhã")
	private String nome;
	
	@Schema(example = "28/03/2024")
	private OffsetDateTime dataCadastro;
	
	@Schema(example = "04/04/2024")
	private OffsetDateTime dataAtualizacao;
}
