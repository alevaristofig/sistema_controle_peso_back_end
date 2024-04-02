package com.sistemacontrolepeso.api.model;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PesoModel extends RepresentationModel<PesoModel> {

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "100.56")
	private double valor;
	
	@Schema(example = "34.98")
	private double imc;
	
	@Schema(example = "28/03/2024")
	private LocalDateTime data;
	
	@Schema(example = "João da Silva")
	private PessoaModel pessoa;
}
