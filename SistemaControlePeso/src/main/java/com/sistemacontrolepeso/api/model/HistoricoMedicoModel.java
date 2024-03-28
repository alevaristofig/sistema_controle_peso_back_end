package com.sistemacontrolepeso.api.model;

import java.time.LocalDateTime;

import com.sistemacontrolepeso.api.model.input.PessoaIdInput;
import com.sistemacontrolepeso.domain.model.Pessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoricoMedicoModel {

	private Long id;
	
	private String descricao;
	
	private String remedio;
	
	private LocalDateTime dataCadastro;
	
	private LocalDateTime dataAtualizacao;
	
	private PessoaIdInput pessoa;
}