package com.sistemacontrolepeso.api.model;

import java.time.LocalDateTime;

import com.sistemacontrolepeso.api.model.input.AlimentoIdInput;
import com.sistemacontrolepeso.api.model.input.DietaIdInput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlimentoDietaModel {

	private Long id;
	
	private LocalDateTime dataCadastro;
			
	private LocalDateTime dataAtualizacao;
	
	private AlimentoIdInput alimento;
	
	private DietaIdInput dieta;
}
