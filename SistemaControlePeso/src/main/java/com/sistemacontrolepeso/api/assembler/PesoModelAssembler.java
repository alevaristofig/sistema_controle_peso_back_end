package com.sistemacontrolepeso.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.model.PesoModel;
import com.sistemacontrolepeso.domain.model.Peso;

@Component
public class PesoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public PesoModel toModel(Peso peso) {
		return modelMapper.map(peso, PesoModel.class);
	}
	
	public List<PesoModel> toCollectionModel(List<Peso> pesos){
		return pesos.stream()
				.map(peso -> toModel(peso))
				.collect(Collectors.toList());
	}
}
