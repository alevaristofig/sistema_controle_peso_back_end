package com.sistemacontrolepeso.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.controller.DietaController;
import com.sistemacontrolepeso.api.model.DietaModel;
import com.sistemacontrolepeso.domain.model.Dieta;

@Component
public class DietaModelAssembler extends RepresentationModelAssemblerSupport<Dieta, DietaModel> {

	public DietaModelAssembler() {
		super(DietaController.class, DietaModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public DietaModel toModel(Dieta dieta) {
		DietaModel dietaModel = createModelWithId(dieta.getId(), dieta);
		
		modelMapper.map(dieta, dietaModel);
		
		return dietaModel;
	}
	
	public List<DietaModel> toCollectionModel(List<Dieta> dietas){
		return dietas.stream()
				.map(dieta -> toModel(dieta))
				.collect(Collectors.toList());
	}
}
