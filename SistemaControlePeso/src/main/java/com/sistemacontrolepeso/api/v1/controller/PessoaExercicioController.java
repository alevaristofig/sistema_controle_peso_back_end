package com.sistemacontrolepeso.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sistemacontrolepeso.api.v1.assembler.PessoaExercicioModelAssembler;
import com.sistemacontrolepeso.api.v1.model.PessoaExercicioModel;
import com.sistemacontrolepeso.api.v1.model.input.PessoaExercicioInput;
import com.sistemacontrolepeso.api.v1.openapi.controller.PessoaExercicioControllerOpenApi;
import com.sistemacontrolepeso.core.security.CheckSecurity;
import com.sistemacontrolepeso.domain.model.Exercicio;
import com.sistemacontrolepeso.domain.model.Pessoa;
import com.sistemacontrolepeso.domain.model.PessoaExercicio;
import com.sistemacontrolepeso.domain.repository.PessoaExercicioRepository;
import com.sistemacontrolepeso.domain.service.CadastroPessoaExercicioService;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(value = "/v1/pessoaexercicio", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaExercicioController implements PessoaExercicioControllerOpenApi {

	@Autowired
	private CadastroPessoaExercicioService cadastroPessoaExercicioService;
	
	@Autowired
	private PessoaExercicioRepository pessoaExercicioRepository;
	
	@Autowired
	private PessoaExercicioModelAssembler pessoaExercicioModelAssembler;
	
	@Autowired
	private PagedResourcesAssembler<PessoaExercicio> pagedResourcesAssembler;
	
	@CheckSecurity.PessoasExercicios.PodeConsultar
	@GetMapping("/{id}")
	public PagedModel<PessoaExercicioModel> listar(@PathVariable Long id, @PageableDefault(size = 10) Pageable pageable) {
		Page<PessoaExercicio> pessoaExercicioPage = pessoaExercicioRepository.findAllByPessoaId(id, pageable);				
		
		PagedModel<PessoaExercicioModel> pessoaExercicioPageModel = pagedResourcesAssembler
				.toModel(pessoaExercicioPage,pessoaExercicioModelAssembler);
		
		return pessoaExercicioPageModel;
	}
	
	@CheckSecurity.PessoasExercicios.podeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PessoaExercicio adicionar(@Validated @RequestBody PessoaExercicioInput pessoaExercicioInput) {
		Pessoa pessoa = new Pessoa();		
		pessoa.setId(pessoaExercicioInput.getPessoaId().getId());
		
		Exercicio exercicio = new Exercicio();
		exercicio.setId(pessoaExercicioInput.getExercicioId().getId());
		
		PessoaExercicio pessoaExercicio = new PessoaExercicio();
		pessoaExercicio.setPessoa(pessoa);
		pessoaExercicio.setExercicio(exercicio);
		pessoaExercicio.setTreino(pessoaExercicioInput.getTreino());
		
		return cadastroPessoaExercicioService.salvar(pessoaExercicio);
		
	}
	
	@CheckSecurity.PessoasExercicios.PodeConsultar
	@GetMapping("/listartreinos/{id}/{treino}")
	public List<com.sistemacontrolepeso.domain.model.dto.PessoaExercicio> listarTreinos(@PathVariable char treino, @PathVariable Long id) {
		return pessoaExercicioRepository.listarQuantidadeTreinos(treino, id);
	}
}
