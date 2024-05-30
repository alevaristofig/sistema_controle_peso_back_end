package com.sistemacontrolepeso.api.v1.model;

import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExercicioModel extends RepresentationModel<ExercicioModel> {

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Esteira")
	private String nome;
	
	@Schema(example = "45")
	private Long tempo;
	
	@Schema(example = "3")
	private int frequencia;
	
	@Schema(example = "28/03/2024")
	private OffsetDateTime dataCadastro;
	
	@Schema(example = "03/04/2024")
	private OffsetDateTime dataAtualizacao;
}
