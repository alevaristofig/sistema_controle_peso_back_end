package com.sistemacontrolepeso.api.model.input;

import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PesoInput {

	@Schema(example = "100.56")
	@NotNull
	private double valor;
	
	@Schema(example = "34.98")
	private double imc;
	
	@Schema(example = "28/03/2024")
	private LocalDateTime data;
	
	@Schema(example = "João da Silva")
	private PessoaIdInput pessoa;
}
