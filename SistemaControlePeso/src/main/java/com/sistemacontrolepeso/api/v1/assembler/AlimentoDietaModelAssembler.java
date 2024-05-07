package com.sistemacontrolepeso.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.v1.model.AlimentoDietaModel;
import com.sistemacontrolepeso.domain.model.AlimentoDieta;

@Component
public class AlimentoDietaModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public AlimentoDietaModel toModel(AlimentoDieta alimentoDieta) {
		return modelMapper.map(alimentoDieta, AlimentoDietaModel.class);
	}
	
	public List<AlimentoDietaModel> toCollectionModel(List<AlimentoDieta> alimentosDieta){
		return alimentosDieta.stream()
				.map(alimentoDieta -> toModel(alimentoDieta))
				.collect(Collectors.toList());
	}
}
