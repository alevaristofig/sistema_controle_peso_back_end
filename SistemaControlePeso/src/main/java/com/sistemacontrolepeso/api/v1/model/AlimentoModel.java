package com.sistemacontrolepeso.api.v1.model;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlimentoModel extends RepresentationModel<AlimentoModel> {

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Arroz")
	private String nome;
	
	@Schema(example = "4 colheres")
	private String quantidade;
	
	@Schema(example = "100 calorias")
	private double calorias;
	
	@Schema(example = "28/03/2024")
	private OffsetDateTime dataCadastro;
	
	@Schema(example = "03/04/2024")
	private OffsetDateTime dataAtualizacao;
}
