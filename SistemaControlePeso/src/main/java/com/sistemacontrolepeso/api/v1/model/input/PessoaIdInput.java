package com.sistemacontrolepeso.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaIdInput {

	@Schema(example = "1")
	private Long id;
}
