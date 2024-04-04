package com.sistemacontrolepeso.api.model;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DietaModel extends RepresentationModel<DietaModel>{

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Café da Manhã")
	private String nome;
	
	@Schema(example = "28/03/2024")
	private LocalDateTime dataCadastro;
	
	@Schema(example = "04/04/2024")
	private LocalDateTime dataAtualizacao;
}
