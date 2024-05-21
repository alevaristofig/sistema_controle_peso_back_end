package com.sistemacontrolepeso.api.v1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sistemacontrolepeso.api.v1.assembler.PessoaInputDisassembler;
import com.sistemacontrolepeso.api.v1.assembler.PessoaModelAssembler;
import com.sistemacontrolepeso.api.v1.model.PessoaModel;
import com.sistemacontrolepeso.api.v1.model.input.PessoaInput;
import com.sistemacontrolepeso.api.v1.model.input.PessoaSenhaInput;
import com.sistemacontrolepeso.api.v1.openapi.controller.PessoaControllerOpenApi;
import com.sistemacontrolepeso.core.security.CheckSecurity;
import com.sistemacontrolepeso.domain.model.Pessoa;
import com.sistemacontrolepeso.domain.repository.PessoaRepository;
import com.sistemacontrolepeso.domain.service.CadastroPessoaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "v1/pessoas", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaController implements PessoaControllerOpenApi {
	
	@Autowired
	private PessoaInputDisassembler pessoaInputDisassembler;
	
	@Autowired
	private PessoaModelAssembler pessoaModelAssembler;
	
	@Autowired
	private CadastroPessoaService cadastroPessoaService;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@GetMapping
	public CollectionModel<PessoaModel> listar(){
		List<Pessoa> pessoas = pessoaRepository.findAll();
		
		return pessoaModelAssembler.toCollectionModel(pessoas);
	}
	
	@CheckSecurity.Pessoas.PodeConsultar
	@GetMapping("/{id}")
	public PessoaModel buscar(@PathVariable Long id) {
		Pessoa pessoa = cadastroPessoaService.buscarOuFalhar(id);
		
		return pessoaModelAssembler.toModel(pessoa);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PessoaModel adicionar(@RequestBody PessoaInput pessoaInput) {		
		Pessoa pessoa = pessoaInputDisassembler.toDomainObject(pessoaInput);			
		
		pessoa = cadastroPessoaService.salvar(pessoa);
		
		return pessoaModelAssembler.toModel(pessoa);
	}
	
	@CheckSecurity.Pessoas.podeEditar
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PessoaModel atualizar(@PathVariable Long id, @RequestBody PessoaInput pessoaInput) {
		Pessoa pessoa = cadastroPessoaService.buscarOuFalhar(id);
		
		pessoaInputDisassembler.copytoDomain(pessoaInput, pessoa);
		
		pessoa = cadastroPessoaService.salvar(pessoa);
		
		return pessoaModelAssembler.toModel(pessoa);
	}
	
	@CheckSecurity.Pessoas.PodeConsultar
	@GetMapping("/buscardadostoken/{token}")
	public Pessoa buscarDadosToken(@PathVariable String token) {		
		return  (Pessoa) pessoaRepository.buscarIdEmailToken(token);
	}
	
	//@CheckSecurity.Pessoas.podeEditar
	@DeleteMapping("/removertoken/{token}")
	public void removerToken(@PathVariable String token) {
		pessoaRepository.removerToken(token);
	}
	
	@PutMapping("/recuperarsenha")
	public void recuperarSenha(@RequestBody PessoaSenhaInput pessoaSenha) {
		Optional<Pessoa> pessoaOptional = pessoaRepository.findByEmail(pessoaSenha.getEmail());
		Pessoa pessoa = pessoaOptional.get();
		
		pessoaInputDisassembler.copytoDomain(pessoaSenha, pessoa);
		cadastroPessoaService.salvar(pessoa);
	}
}
