package com.sistemacontrolepeso.api.model.input;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import com.sistemacontrolepeso.domain.model.Pessoa;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoricoMedicoInput {

	@Schema(example = "Diabetes")
	private String descricao;
	
	@Schema(example = "Insulina")
	private String remedio;
	
	@Schema(example = "28/03/2024")
	private OffsetDateTime dataCadastro;
	
	@Schema(example = "04/04/2024")
	private OffsetDateTime dataAtualizacao;
	
	@Schema(example = "1")
	private Pessoa pessoa;
}
