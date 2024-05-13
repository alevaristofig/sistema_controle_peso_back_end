package com.sistemacontrolepeso.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaSenhaInput {
	
	@Schema(example = "joaosilva@email.com.br")
	@NotBlank
	private String email;

	@Schema(example = "$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W")
	@NotBlank
	private String senha;
}
