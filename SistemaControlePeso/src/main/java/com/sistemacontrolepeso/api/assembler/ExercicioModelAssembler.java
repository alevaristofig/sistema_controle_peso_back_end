package com.sistemacontrolepeso.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.model.ExercicioModel;
import com.sistemacontrolepeso.domain.model.Exercicio;

@Component
public class ExercicioModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ExercicioModel toModel(Exercicio exercicio) {
		return modelMapper.map(exercicio, ExercicioModel.class);
	}
	
	public List<ExercicioModel> toCollectionModel(List<Exercicio> exercicios){
		return exercicios.stream()
				.map(exercicio -> toModel(exercicio))
				.collect(Collectors.toList());
	}
}
