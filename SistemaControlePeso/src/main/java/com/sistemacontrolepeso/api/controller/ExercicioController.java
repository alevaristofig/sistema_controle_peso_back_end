package com.sistemacontrolepeso.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sistemacontrolepeso.api.assembler.ExercicioInputDisassembler;
import com.sistemacontrolepeso.api.assembler.ExercicioModelAssembler;
import com.sistemacontrolepeso.api.model.ExercicioModel;
import com.sistemacontrolepeso.api.model.input.ExercicioInput;
import com.sistemacontrolepeso.domain.model.Exercicio;
import com.sistemacontrolepeso.domain.model.Peso;
import com.sistemacontrolepeso.domain.repository.ExercicioRepository;
import com.sistemacontrolepeso.domain.service.CadastroExercicioService;

@RestController
@RequestMapping(value = "/exercicio", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExercicioController {

	@Autowired
	private CadastroExercicioService cadastroExercicioService;
	
	@Autowired
	private ExercicioRepository exercicioRepository;
	
	@Autowired
	private ExercicioInputDisassembler exercicioInputDisassembler;
	
	@Autowired
	private ExercicioModelAssembler exercicioModelAssembler;
	
	@GetMapping
	public List<ExercicioModel> listar(){
		List<Exercicio> exercicios = exercicioRepository.findAll();
		
		return exercicioModelAssembler.toCollectionModel(exercicios);
	}
	
	@GetMapping("/{exercicioId}")
	public ExercicioModel buscar(@PathVariable Long exercicioId) {
		System.out.println("entrou get controller");
		Exercicio exercicio = cadastroExercicioService.buscarOuFalhar(exercicioId);
		
		return exercicioModelAssembler.toModel(exercicio);
	}
	
	@PutMapping("/{exercicioId}")
	@ResponseStatus(HttpStatus.OK)
	public ExercicioModel atualizar(@PathVariable Long exercicioId, @RequestBody ExercicioInput exercicioInput) {
		System.out.println("entrou put controller");
		Exercicio exercicio = cadastroExercicioService.buscarOuFalhar(exercicioId);
		
		exercicioInputDisassembler.copytoDomain(exercicioInput, exercicio);
		
		cadastroExercicioService.salvar(exercicio);
		
		return exercicioModelAssembler.toModel(exercicio);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ExercicioModel salvar(@RequestBody ExercicioInput exercicioInput) {
		Exercicio exercicio = exercicioInputDisassembler.toDomainObject(exercicioInput);
		
		cadastroExercicioService.salvar(exercicio);
		
		return exercicioModelAssembler.toModel(exercicio);
	}
	
	@DeleteMapping("/{exercicioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long exercicioId) {
		Exercicio exercicio = cadastroExercicioService.buscarOuFalhar(exercicioId);
		
		exercicioRepository.delete(exercicio);
	}
	
}
