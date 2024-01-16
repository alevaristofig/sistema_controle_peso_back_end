package com.sistemacontrolepeso.api.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sistemacontrolepeso.api.assembler.PessoaInputDisassembler;
import com.sistemacontrolepeso.api.assembler.PessoaModelAssembler;
import com.sistemacontrolepeso.api.model.PessoaModel;
import com.sistemacontrolepeso.api.model.input.PessoaInput;
import com.sistemacontrolepeso.domain.model.Pessoa;
import com.sistemacontrolepeso.domain.service.CadastroPessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaInputDisassembler pessoaInputDisassembler;
	
	@Autowired
	private PessoaModelAssembler pessoaModelAssembler;
	
	@Autowired
	private CadastroPessoaService cadastroPessoaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PessoaModel salvar(@RequestBody PessoaInput pessoaInput) {
		Pessoa pessoa = pessoaInputDisassembler.toDomainObject(pessoaInput);
		
		pessoa.setData(new Date());
		
		pessoa = cadastroPessoaService.salvar(pessoa);
		
		return pessoaModelAssembler.toModel(pessoa);
	}
}
