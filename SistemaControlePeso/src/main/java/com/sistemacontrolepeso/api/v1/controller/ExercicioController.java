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
import org.springframework.http.ResponseEntity;
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

import com.sistemacontrolepeso.api.v1.assembler.ExercicioInputDisassembler;
import com.sistemacontrolepeso.api.v1.assembler.ExercicioModelAssembler;
import com.sistemacontrolepeso.api.v1.model.ExercicioModel;
import com.sistemacontrolepeso.api.v1.model.input.ExercicioInput;
import com.sistemacontrolepeso.api.v1.openapi.controller.ExercicioControllerOpenApi;
import com.sistemacontrolepeso.core.security.CheckSecurity;
import com.sistemacontrolepeso.domain.model.Exercicio;
import com.sistemacontrolepeso.domain.repository.ExercicioRepository;
import com.sistemacontrolepeso.domain.service.CadastroExercicioService;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(value = "/v1/exercicios", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExercicioController implements ExercicioControllerOpenApi {

	@Autowired
	private CadastroExercicioService cadastroExercicioService;
	
	@Autowired
	private ExercicioRepository exercicioRepository;
	
	@Autowired
	private ExercicioInputDisassembler exercicioInputDisassembler;
	
	@Autowired
	private ExercicioModelAssembler exercicioModelAssembler;
	
	@Autowired
	private PagedResourcesAssembler<Exercicio> pagedResourcesAssembler;
	
	@CheckSecurity.Exercicios.PodeConsultar
	@GetMapping("/listarexerciciospaginacao/{id}")
	public PagedModel<ExercicioModel> listar(@PathVariable Long id, @PageableDefault(size = 10) Pageable pageable){
		Page<Exercicio> exerciciosPage = exercicioRepository.findAllByPessoaId(id, pageable);
				
		
		PagedModel<ExercicioModel> exerciciosPagedModel = pagedResourcesAssembler
				.toModel(exerciciosPage,exercicioModelAssembler);
		
		return exerciciosPagedModel;
	}
	
	@CheckSecurity.Exercicios.PodeConsultar
	@GetMapping("/listarexercicios")
	public List<ExercicioModel> listar() {
		List<Exercicio> exercicios = exercicioRepository.findAll();
		
		return exercicioModelAssembler.toCollectionModel(exercicios);
	}
	
	@CheckSecurity.Exercicios.PodeConsultar
	@GetMapping("/{id}")
	public ExercicioModel buscar(@PathVariable Long id) {
		
		Exercicio exercicio = cadastroExercicioService.buscarOuFalhar(id);
		
		return exercicioModelAssembler.toModel(exercicio);
	}
	
	@CheckSecurity.Exercicios.podeEditar
	@PutMapping("/{exercicioId}")
	@ResponseStatus(HttpStatus.OK)
	public ExercicioModel atualizar(@PathVariable Long exercicioId, @RequestBody ExercicioInput exercicioInput) {
		
		Exercicio exercicio = cadastroExercicioService.buscarOuFalhar(exercicioId);
		
		exercicioInputDisassembler.copytoDomain(exercicioInput, exercicio);
		
		cadastroExercicioService.salvar(exercicio);
		
		return exercicioModelAssembler.toModel(exercicio);
	}
	
	@CheckSecurity.Exercicios.podeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ExercicioModel salvar(@RequestBody ExercicioInput exercicioInput) {
		
		Exercicio exercicio = exercicioInputDisassembler.toDomainObject(exercicioInput);
		
		cadastroExercicioService.salvar(exercicio);
		
		return exercicioModelAssembler.toModel(exercicio);
	}
	
	@CheckSecurity.Exercicios.podeEditar
	@DeleteMapping("/{exercicioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> remover(@PathVariable Long exercicioId) {
		Exercicio exercicio = cadastroExercicioService.buscarOuFalhar(exercicioId);
		
		exercicioRepository.delete(exercicio);
		
		return ResponseEntity.noContent().build();
	}
	
}
