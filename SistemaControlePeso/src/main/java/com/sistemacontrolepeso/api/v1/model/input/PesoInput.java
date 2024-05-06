package com.sistemacontrolepeso.api.v1.model.input;

import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
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
	private OffsetDateTime dataCadastro;
	
	@Schema(example = "28/03/2024")
	private OffsetDateTime dataAtualizacao;
	
	@Schema(example = "João da Silva")
	private PessoaIdInput pessoa;
}
