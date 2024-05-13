package com.sistemacontrolepeso.api.v1.model.input;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlimentoDietaInput {
		
	private AlimentoIdInput alimentoId;
	
	private DietaIdInput dietaId;
	
	private OffsetDateTime dataCadastro;
	
	private OffsetDateTime dataAtualizacao;
	
}
