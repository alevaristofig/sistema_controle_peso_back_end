package com.sistemacontrolepeso.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.controller.AlimentoController;
import com.sistemacontrolepeso.api.model.AlimentoModel;
import com.sistemacontrolepeso.domain.model.Alimento;

@Component
public class AlimentoModelAssembler extends RepresentationModelAssemblerSupport<Alimento, AlimentoModel>{

	public AlimentoModelAssembler() {
		super(AlimentoController.class, AlimentoModel.class);		
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public AlimentoModel toModel(Alimento alimento) {
		AlimentoModel alimentoModel = createModelWithId(alimento.getId(), alimento);
		
		modelMapper.map(alimento, alimentoModel);
		
		return alimentoModel;
	}
	
	public List<AlimentoModel> toCollectionModel(List<Alimento> alimentos){
		return alimentos.stream()
				.map(alimento -> toModel(alimento))
				.collect(Collectors.toList());
	}
}
