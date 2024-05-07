package com.sistemacontrolepeso.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.v1.SistemaControlePesoLinks;
import com.sistemacontrolepeso.api.v1.controller.AlimentoController;
import com.sistemacontrolepeso.api.v1.model.AlimentoModel;
import com.sistemacontrolepeso.domain.model.Alimento;

@Component
public class AlimentoModelAssembler extends RepresentationModelAssemblerSupport<Alimento, AlimentoModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private SistemaControlePesoLinks links;
	
	public AlimentoModelAssembler() {
		super(AlimentoController.class, AlimentoModel.class);		
	}

	
	@Override
	public AlimentoModel toModel(Alimento alimento) {
		AlimentoModel alimentoModel = createModelWithId(alimento.getId(), alimento);
		
		modelMapper.map(alimento, alimentoModel);
		
		links.linkToAlimentos();
		
		return alimentoModel;
	}
	
	public List<AlimentoModel> toCollectionModel(List<Alimento> alimentos){
		return alimentos.stream()
				.map(alimento -> toModel(alimento))
				.collect(Collectors.toList());
	}
}
