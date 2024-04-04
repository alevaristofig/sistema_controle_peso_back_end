package com.sistemacontrolepeso.api.model.input;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlimentoInput {
	
	@Schema(example = "Arroz")
	private String nome;
	
	@Schema(example = "4 colheres")
	private String quantidade;
	
	@Schema(example = "100 calorias")
	private double calorias;
	
	@Schema(example = "28/03/2024")
	private LocalDateTime dataCadastro;
	
	@Schema(example = "03/04/2024")
	private LocalDateTime dataAtualizacao;
}
