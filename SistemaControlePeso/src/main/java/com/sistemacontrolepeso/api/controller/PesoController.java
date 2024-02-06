package com.sistemacontrolepeso.api.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.sistemacontrolepeso.api.assembler.PesoInputDisassembler;
import com.sistemacontrolepeso.api.assembler.PesoModelAssembler;
import com.sistemacontrolepeso.api.model.PesoModel;
import com.sistemacontrolepeso.api.model.input.PesoInput;
import com.sistemacontrolepeso.domain.model.Peso;
import com.sistemacontrolepeso.domain.repository.PesoRepository;
import com.sistemacontrolepeso.domain.service.CadastroPesoService;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(value = "/peso", produces = MediaType.APPLICATION_JSON_VALUE)
public class PesoController {

	@Autowired
	private CadastroPesoService cadastroPesoService;
	
	@Autowired
	private PesoInputDisassembler pesoInputDisassembler;
	
	@Autowired
	private PesoModelAssembler pesoModelAssembler;
	
	@Autowired
	private PesoRepository pesoRepository;
	
	@GetMapping
	public List<PesoModel> listar(){
		List<Peso> peso = pesoRepository.findAll();
		
		return pesoModelAssembler.toCollectionModel(peso);
	}
	
	@GetMapping("/{pesoId}")
	public PesoModel buscar(@PathVariable Long pesoId) {
		Peso peso = cadastroPesoService.buscarOuFalhar(pesoId);
		
		return pesoModelAssembler.toModel(peso);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PesoModel salvar(@RequestBody PesoInput pesoInput) {
		Peso peso = pesoInputDisassembler.toDomainObject(pesoInput);
		
		peso = cadastroPesoService.salvar(peso);
		
		return pesoModelAssembler.toModel(peso);
	}
	
	@PutMapping("/{pesoId}")
	public PesoModel atualizar(@PathVariable Long pesoId, @RequestBody PesoInput pesoInput) {
		Peso peso = cadastroPesoService.buscarOuFalhar(pesoId);
		System.out.println(pesoInput.getData());
		pesoInputDisassembler.copyToDomain(pesoInput, peso);
		
		peso = cadastroPesoService.salvar(peso);
		
		return pesoModelAssembler.toModel(peso);
	}
	
	@DeleteMapping("/{pesoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long pesoId) {
		Peso peso = cadastroPesoService.buscarOuFalhar(pesoId);
		
		pesoRepository.delete(peso);
	}
}