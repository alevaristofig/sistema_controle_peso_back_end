package com.sistemacontrolepeso.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.model.PessoaModel;
import com.sistemacontrolepeso.domain.model.Pessoa;

@Component
public class PessoaModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public PessoaModel toModel(Pessoa pessoa) {
		return modelMapper.map(pessoa, PessoaModel.class);
	}
	
	public List<PessoaModel> toCollectionModel(List<Pessoa> pessoas){
		return pessoas.stream()
					.map(pessoa -> toModel(pessoa))
					.collect(Collectors.toList());
	}
}
