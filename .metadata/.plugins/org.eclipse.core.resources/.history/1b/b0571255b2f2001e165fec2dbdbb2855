package com.sistemacontrolepeso.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.model.DietaModel;
import com.sistemacontrolepeso.api.model.HistoricoMedicoModel;
import com.sistemacontrolepeso.domain.model.Dieta;
import com.sistemacontrolepeso.domain.model.HistoricoMedico;

@Component
public class HistoricoMedicoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public HistoricoMedicoModel toModel(HistoricoMedico historicoMedico) {
		return modelMapper.map(historicoMedico, HistoricoMedicoModel.class);
	}
	
	public List<HistoricoMedicoModel> toCollectionModel(List<HistoricoMedico> historicosMedico){
		return historicosMedico.stream()
				.map(historicoMedico -> toModel(historicoMedico))
				.collect(Collectors.toList());
	}
}
