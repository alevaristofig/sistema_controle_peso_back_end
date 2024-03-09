package com.sistemacontrolepeso.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
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

import com.sistemacontrolepeso.api.assembler.DietaInputDisassembler;
import com.sistemacontrolepeso.api.assembler.DietaModelAssembler;
import com.sistemacontrolepeso.api.model.DietaModel;
import com.sistemacontrolepeso.api.model.input.DietaInput;
import com.sistemacontrolepeso.domain.model.Dieta;
import com.sistemacontrolepeso.domain.repository.DietaRepository;
import com.sistemacontrolepeso.domain.service.CadastroDietaService;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(value = "/dietas", produces = MediaType.APPLICATION_JSON_VALUE)
public class DietaController {

	@Autowired
	private DietaRepository dietaRepository;
	
	@Autowired
	private CadastroDietaService cadastroDietaService;
	
	@Autowired
	private DietaInputDisassembler dietaInputDisassembler;
	
	@Autowired
	private DietaModelAssembler dietaModelAssembler;
	
	@GetMapping
	public List<DietaModel> listar() {
		List<Dieta> dietas = dietaRepository.findAll();
		
		return dietaModelAssembler.toCollectionModel(dietas);
	}
	
	@GetMapping("/{id}")
	public DietaModel buscar(@PathVariable Long id) {
		Dieta dieta = cadastroDietaService.buscarOuFalhar(id);
		
		return dietaModelAssembler.toModel(dieta);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DietaModel salvar(@RequestBody @Validated DietaInput dietaInput) {
		Dieta dieta = dietaInputDisassembler.toDomainObject(dietaInput);
		
		dieta.setDataAtualizacao(LocalDateTime.now());
		dieta.setDataAtualizacao(null);
		
		dieta = cadastroDietaService.salvar(dieta);
		
		return dietaModelAssembler.toModel(dieta);
	}
	
	@PutMapping("/{id}")
	public DietaModel atualizar(@PathVariable Long id, @RequestBody @Validated DietaInput dietaInput) {
		Dieta dieta = cadastroDietaService.buscarOuFalhar(id);
		
		dietaInputDisassembler.copytoDomain(dietaInput, dieta);
		
		dieta = cadastroDietaService.salvar(dieta);
		
		return dietaModelAssembler.toModel(dieta);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagar(@PathVariable Long id) {
		Dieta dieta = cadastroDietaService.buscarOuFalhar(id);
		
		dietaRepository.delete(dieta);
	}
}
