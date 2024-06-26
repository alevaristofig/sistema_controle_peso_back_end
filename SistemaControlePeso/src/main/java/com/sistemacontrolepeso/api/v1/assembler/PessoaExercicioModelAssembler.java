package com.sistemacontrolepeso.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.v1.controller.PessoaExercicioController;
import com.sistemacontrolepeso.api.v1.model.PessoaExercicioModel;
import com.sistemacontrolepeso.domain.model.PessoaExercicio;

@Component
public class PessoaExercicioModelAssembler extends RepresentationModelAssemblerSupport<PessoaExercicio, PessoaExercicioModel> {

	public PessoaExercicioModelAssembler() {
		super(PessoaExercicioController.class, PessoaExercicioModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PessoaExercicioModel toModel(PessoaExercicio pessoaExercicio) {
		PessoaExercicioModel pessoaExercicioModel = createModelWithId(pessoaExercicio.getId(), pessoaExercicio);
		
		modelMapper.map(pessoaExercicio, pessoaExercicioModel);
		
		return pessoaExercicioModel;
	}
	
	public List<PessoaExercicioModel> toCollectionModel(List<PessoaExercicio> pessoasExercicio){
		return pessoasExercicio.stream()
				.map(pessoaExercicio -> toModel(pessoaExercicio))
				.collect(Collectors.toList());
	}
}
