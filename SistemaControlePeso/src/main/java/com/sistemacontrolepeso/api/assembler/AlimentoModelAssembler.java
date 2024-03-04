package com.sistemacontrolepeso.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.model.AlimentoModel;
import com.sistemacontrolepeso.domain.model.Alimento;

@Component
public class AlimentoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public AlimentoModel toModel(Alimento alimento) {
		return modelMapper.map(alimento, AlimentoModel.class);
	}
	
	public List<AlimentoModel> toCollectionModel(List<Alimento> alimentos){
		return alimentos.stream()
				.map(alimento -> toModel(alimento))
				.collect(Collectors.toList());
	}
}
