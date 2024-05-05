package com.sistemacontrolepeso.api.model;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import com.sistemacontrolepeso.api.model.input.PessoaIdInput;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoricoMedicoModel extends RepresentationModel<HistoricoMedicoModel> {

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Diabetes")
	private String descricao;
	
	@Schema(example = "Insulina")
	private String remedio;
	
	@Schema(example = "28/03/2024")
	private LocalDateTime dataCadastro;
	
	@Schema(example = "04/04/2024")
	private LocalDateTime dataAtualizacao;
	
	@Schema(example = "1")
	private PessoaIdInput pessoa;
}
