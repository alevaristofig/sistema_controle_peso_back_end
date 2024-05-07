package com.sistemacontrolepeso.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.model.ExercicioModel;
import com.sistemacontrolepeso.api.model.input.ExercicioInput;
import com.sistemacontrolepeso.domain.model.Exercicio;

@Component
public class ExercicioInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Exercicio toDomainObject(ExercicioInput exercicioInput) {
		return modelMapper.map(exercicioInput, Exercicio.class);
	}
	
	public void copytoDomain(ExercicioInput exercicioInput, Exercicio exercicio) {
		modelMapper.map(exercicioInput, exercicio);
	}
}
