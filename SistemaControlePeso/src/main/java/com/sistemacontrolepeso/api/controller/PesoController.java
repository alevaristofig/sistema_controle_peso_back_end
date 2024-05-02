package com.sistemacontrolepeso.api.controller;

import java.time.OffsetDateTime;

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

import com.sistemacontrolepeso.api.assembler.PesoInputDisassembler;
import com.sistemacontrolepeso.api.assembler.PesoModelAssembler;
import com.sistemacontrolepeso.api.model.PesoModel;
import com.sistemacontrolepeso.api.model.input.PesoInput;
import com.sistemacontrolepeso.api.v1.openapi.controller.PesoControllerOpenApi;
import com.sistemacontrolepeso.core.security.CheckSecurity;
import com.sistemacontrolepeso.domain.model.Peso;
import com.sistemacontrolepeso.domain.repository.PesoRepository;
import com.sistemacontrolepeso.domain.service.CadastroPesoService;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(value = "/pesos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PesoController implements PesoControllerOpenApi {

	@Autowired
	private CadastroPesoService cadastroPesoService;
	
	@Autowired
	private PesoInputDisassembler pesoInputDisassembler;
	
	@Autowired
	private PesoModelAssembler pesoModelAssembler;
	
	@Autowired
	private PagedResourcesAssembler<Peso> pagedResourcesAssembler;
	
	@Autowired
	private PesoRepository pesoRepository;
	
	@CheckSecurity.Pesos.PodeConsultar
	@GetMapping
	public PagedModel<PesoModel> listar(@PageableDefault(size = 10) Pageable pageable){
		Page<Peso> pesosPage = pesoRepository.findAll(pageable);
		
		PagedModel<PesoModel> pesosPageModel = pagedResourcesAssembler
				.toModel(pesosPage,pesoModelAssembler);
		
		return pesosPageModel;
	}
	
	@CheckSecurity.Pesos.PodeConsultar
	@GetMapping("/{id}")
	public PesoModel buscar(@PathVariable Long id) {
		Peso peso = cadastroPesoService.buscarOuFalhar(id);
		
		return pesoModelAssembler.toModel(peso);
	}
	
	@GetMapping("/buscarprimeiropeso")
	public PesoModel buscarPrimeiroPeso() {
		Peso peso = pesoRepository.findTop1ByOrderByIdAsc();
		
		return pesoModelAssembler.toModel(peso);
	}
	
	@GetMapping("/buscarultimoropeso")
	public PesoModel buscarUltimoPeso() {
		Peso peso = pesoRepository.findTop1ByOrderByIdDesc();
		
		return pesoModelAssembler.toModel(peso);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PesoModel adicionar(@RequestBody PesoInput pesoInput) {
		System.out.println(OffsetDateTime.now());
		Peso peso = pesoInputDisassembler.toDomainObject(pesoInput);		
				
		peso = cadastroPesoService.salvar(peso);
		
		PesoModel pesoModel = pesoModelAssembler.toModel(peso);
		
		return pesoModel;
	}
	
	@PutMapping("/{id}")
	public PesoModel atualizar(@PathVariable Long id, @RequestBody PesoInput pesoInput) {		
		Peso peso = cadastroPesoService.buscarOuFalhar(id);
		
		pesoInputDisassembler.copyToDomain(pesoInput, peso);
		
		peso = cadastroPesoService.salvar(peso);
		
		return pesoModelAssembler.toModel(peso);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Peso peso = cadastroPesoService.buscarOuFalhar(id);
		
		pesoRepository.delete(peso);
		
		return ResponseEntity.noContent().build();
	}
}
