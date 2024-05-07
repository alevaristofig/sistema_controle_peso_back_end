package com.sistemacontrolepeso.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.v1.model.input.PessoaInput;
import com.sistemacontrolepeso.domain.model.Pessoa;

@Component
public class PessoaInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Pessoa toDomainObject(PessoaInput pessoaInput) {
		return modelMapper.map(pessoaInput, Pessoa.class);
	}
	
	public void copytoDomain(PessoaInput pessoaInput, Pessoa pessoa) {
		modelMapper.map(pessoaInput, pessoa);
	}
}
