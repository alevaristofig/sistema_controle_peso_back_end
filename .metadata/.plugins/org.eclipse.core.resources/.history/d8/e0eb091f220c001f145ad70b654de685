package com.sistemacontrolepeso.api.v1.model;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;

import com.sistemacontrolepeso.api.model.input.PessoaIdInput;
import com.sistemacontrolepeso.domain.model.Exercicio;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaExercicioModel extends RepresentationModel<PessoaExercicioModel> {

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "1")
	private PessoaIdInput pessoaId;
	
	@Schema(example = "1")
	private Exercicio exercicio;
	
	@Schema(example = "S")
	private char treino;
	
	@Schema(example = "28/03/2024")
	private OffsetDateTime data;
}
