package com.sistemacontrolepeso.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.v1.SistemaControlePesoLinks;
import com.sistemacontrolepeso.api.v1.controller.PesoController;
import com.sistemacontrolepeso.api.v1.model.PesoModel;
import com.sistemacontrolepeso.domain.model.Peso;

@Component
public class PesoModelAssembler extends RepresentationModelAssemblerSupport<Peso, PesoModel> {
	
	@Autowired
	private SistemaControlePesoLinks sistemaControlePesoLinks;

	public PesoModelAssembler() {
		super(PesoController.class, PesoModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private SistemaControlePesoLinks links;
	
	
	@Override
	public PesoModel toModel(Peso peso) {
		PesoModel pesoModel = createModelWithId(peso.getId(), peso);
		
		modelMapper.map(peso,pesoModel);
				
		links.linkToPesos("pesos");
		
		return pesoModel;
	}
}
