package com.sistemacontrolepeso.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.model.DietaModel;
import com.sistemacontrolepeso.api.model.HistoricoMedicoModel;
import com.sistemacontrolepeso.domain.model.Dieta;
import com.sistemacontrolepeso.domain.model.HistoricoMedico;

@Component
public class HistoricoMedicoModelAssembler extends RepresentationModelAssemblerSupport<
				HistoricoMedico, HistoricoMedicoModel> {

	public HistoricoMedicoModelAssembler() {
		super(HistoricoMedico.class, HistoricoMedicoModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public HistoricoMedicoModel toModel(HistoricoMedico historicosMedico) {
		HistoricoMedicoModel historicoMedicoModel = createModelWithId(historicosMedico.getId(), historicosMedico);
		
		modelMapper.map(historicosMedico, historicoMedicoModel);
		
		return historicoMedicoModel;
	}
	
	public List<HistoricoMedicoModel> toCollectionModel(List<HistoricoMedico> historicosMedico){
		return historicosMedico.stream()
				.map(historicoMedico -> toModel(historicoMedico))
				.collect(Collectors.toList());
	}
}
