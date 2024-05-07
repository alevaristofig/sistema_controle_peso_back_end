package com.sistemacontrolepeso.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.v1.SistemaControlePesoLinks;
import com.sistemacontrolepeso.api.v1.controller.ExercicioController;
import com.sistemacontrolepeso.api.v1.model.ExercicioModel;
import com.sistemacontrolepeso.domain.model.Exercicio;

@Component
public class ExercicioModelAssembler extends RepresentationModelAssemblerSupport<Exercicio, ExercicioModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private SistemaControlePesoLinks links;
	
	public ExercicioModelAssembler() {
		super(ExercicioController.class, ExercicioModel.class);		
	}

	
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
