package com.sistemacontrolepeso.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.controller.ExercicioController;
import com.sistemacontrolepeso.api.model.ExercicioModel;
import com.sistemacontrolepeso.api.v1.SistemaControlePesoLinks;
import com.sistemacontrolepeso.domain.model.Exercicio;

@Component
public class ExercicioModelAssembler extends RepresentationModelAssemblerSupport<Exercicio, ExercicioModel> {

	public ExercicioModelAssembler() {
		super(ExercicioController.class, ExercicioModel.class);		
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private SistemaControlePesoLinks links;
	
	@Override
	public ExercicioModel toModel(Exercicio exercicio) {
		ExercicioModel exercicioModel = createModelWithId(exercicio.getId(), exercicio);
		
		modelMapper.map(exercicio,exercicioModel);
		
		links.linkToExercicios("exercicios");
		
		return exercicioModel;
	}
	
	public List<ExercicioModel> toCollectionModel(List<Exercicio> exercicios){
		return exercicios.stream()
				.map(exercicio -> toModel(exercicio))
				.collect(Collectors.toList());
	}
}
