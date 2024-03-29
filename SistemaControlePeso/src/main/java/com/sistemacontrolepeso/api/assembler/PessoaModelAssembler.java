package com.sistemacontrolepeso.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.controller.PessoaController;
import com.sistemacontrolepeso.api.model.PessoaModel;
import com.sistemacontrolepeso.domain.model.Pessoa;

@Component
public class PessoaModelAssembler extends RepresentationModelAssemblerSupport<Pessoa, PessoaModel> {

	public PessoaModelAssembler() {
		super(PessoaController.class, PessoaModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	public PessoaModel toModel(Pessoa pessoa) {
		return modelMapper.map(pessoa, PessoaModel.class);
	}
	
	public CollectionModel<PessoaModel> toCollectionModel(Iterable<? extends Pessoa> pessoas){
		CollectionModel<PessoaModel> collectionModel = super.toCollectionModel(pessoas);
		
		return collectionModel;
	}
}
