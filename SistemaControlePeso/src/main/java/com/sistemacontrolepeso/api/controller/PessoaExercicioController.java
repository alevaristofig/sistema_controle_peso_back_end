package com.sistemacontrolepeso.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sistemacontrolepeso.api.assembler.PessoaExercicioModelAssembler;
import com.sistemacontrolepeso.api.model.PessoaExercicioModel;
import com.sistemacontrolepeso.api.model.input.PessoaExercicioInput;
import com.sistemacontrolepeso.domain.model.Exercicio;
import com.sistemacontrolepeso.domain.model.Pessoa;
import com.sistemacontrolepeso.domain.model.PessoaExercicio;
import com.sistemacontrolepeso.domain.repository.PessoaExercicioRepository;
import com.sistemacontrolepeso.domain.service.CadastroPessoaExercicioService;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(value = "/pessoaexercicio", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaExercicioController {

	@Autowired
	private CadastroPessoaExercicioService cadastroPessoaExercicioService;
	
	@Autowired
	private PessoaExercicioRepository pessoaExercicioRepository;
	
	@Autowired
	private PessoaExercicioModelAssembler pessoaExercicioModelAssembler;
	
	@GetMapping
	public List<PessoaExercicioModel> listar() {
		List<PessoaExercicio> pessoaExercicio = pessoaExercicioRepository.findAll();
		
		return pessoaExercicioModelAssembler.toCollectionModel(pessoaExercicio);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PessoaExercicio salvar(@Validated @RequestBody PessoaExercicioInput pessoaExercicioInput) {
		Pessoa pessoa = new Pessoa();		
		pessoa.setId(pessoaExercicioInput.getPessoaId().getId());
		
		Exercicio exercicio = new Exercicio();
		exercicio.setId(pessoaExercicioInput.getExercicioId().getId());
		
		PessoaExercicio pessoaExercicio = new PessoaExercicio();
		pessoaExercicio.setPessoa(pessoa);
		pessoaExercicio.setExercicio(exercicio);
		pessoaExercicio.setTreino(pessoaExercicioInput.getTreino());
		pessoaExercicio.setData(LocalDateTime.now());
		
		return cadastroPessoaExercicioService.salvar(pessoaExercicio);
		
	}
}