package com.sistemacontrolepeso.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.model.DietaModel;
import com.sistemacontrolepeso.domain.model.Dieta;

@Component
public class DietaModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public DietaModel toModel(Dieta dieta) {
		return modelMapper.map(dieta, DietaModel.class);
	}
	
	public List<DietaModel> toCollectionModel(List<Dieta> dietas){
		return dietas.stream()
				.map(dieta -> toModel(dieta))
				.collect(Collectors.toList());
	}
}