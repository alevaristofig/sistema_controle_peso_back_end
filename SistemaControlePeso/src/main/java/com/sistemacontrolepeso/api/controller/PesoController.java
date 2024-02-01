package com.sistemacontrolepeso.api.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sistemacontrolepeso.api.assembler.PesoInputDisassembler;
import com.sistemacontrolepeso.api.assembler.PesoModelAssembler;
import com.sistemacontrolepeso.api.model.PesoModel;
import com.sistemacontrolepeso.api.model.input.PesoInput;
import com.sistemacontrolepeso.domain.model.Peso;
import com.sistemacontrolepeso.domain.service.CadastroPesoService;

@RestController
@RequestMapping("/peso")
public class PesoController {

	@Autowired
	private CadastroPesoService cadastroPesoService;
	
	@Autowired
	private PesoInputDisassembler pesoInputDisassembler;
	
	@Autowired
	private PesoModelAssembler pesoModelAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PesoModel salvar(@RequestBody PesoInput pesoInput) {
		Peso peso = pesoInputDisassembler.toDomainObject(pesoInput);
		
		peso.setData(new Date());
		
		peso = cadastroPesoService.salvar(peso);
		
		return pesoModelAssembler.toModel(peso);
	}
}
