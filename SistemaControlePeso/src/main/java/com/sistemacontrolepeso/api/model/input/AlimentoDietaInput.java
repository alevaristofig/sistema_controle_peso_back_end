package com.sistemacontrolepeso.api.model.input;

import java.time.LocalDateTime;

import com.sistemacontrolepeso.domain.model.Alimento;
import com.sistemacontrolepeso.domain.model.Dieta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlimentoDietaInput {
		
	private AlimentoIdInput alimentoId;
	
	private DietaIdInput dietaId;
	
	private LocalDateTime dataCriacao;
	
	private LocalDateTime dataAtualizacao;
	
}
