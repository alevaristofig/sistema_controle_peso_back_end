package com.sistemacontrolepeso.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.model.input.HistoricoMedicoInput;
import com.sistemacontrolepeso.domain.model.HistoricoMedico;

@Component
public class HistoricoMedicoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public HistoricoMedico toDomainObject(HistoricoMedicoInput historicoMedicoInput) {
		return modelMapper.map(historicoMedicoInput, HistoricoMedico.class);
	}
	
	public void copytoDomain(HistoricoMedicoInput historicoMedicoInput, HistoricoMedico historicoMedico) {
		modelMapper.map(historicoMedicoInput, historicoMedico);
	}
}
