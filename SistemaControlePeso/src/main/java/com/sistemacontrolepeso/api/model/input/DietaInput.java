package com.sistemacontrolepeso.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DietaInput {

	@Schema(example = "Café da Manhã")
	private String nome;
}
