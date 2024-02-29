package com.sistemacontrolepeso.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.model.ExercicioModel;
import com.sistemacontrolepeso.api.model.PessoaExercicioModel;
import com.sistemacontrolepeso.domain.model.Exercicio;
import com.sistemacontrolepeso.domain.model.PessoaExercicio;

@Component
public class PessoaExercicioModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public PessoaExercicioModel toModel(PessoaExercicio pessoaExercicio) {
		return modelMapper.map(pessoaExercicio, PessoaExercicioModel.class);
	}
	
	public List<PessoaExercicioModel> toCollectionModel(List<PessoaExercicio> pessoasExercicio){
		return pessoasExercicio.stream()
				.map(pessoaExercicio -> toModel(pessoaExercicio))
				.collect(Collectors.toList());
	}
}
