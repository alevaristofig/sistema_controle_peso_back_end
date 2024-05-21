package com.sistemacontrolepeso.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemacontrolepeso.api.v1.SistemaControlePesoLinks;
import com.sistemacontrolepeso.core.security.SistemaControlePesoSecurity;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {
	
	@Autowired
	private SistemaControlePesoLinks sistemaControlePesoLinks;
	
	@Autowired
	private SistemaControlePesoSecurity sistemaControlePesoSecurity;
	
	@GetMapping
	@Operation(hidden = true)
	public RootEntryPointModel root() {
		var rootEntryPointModel = new RootEntryPointModel();
		
		if(sistemaControlePesoSecurity.podeConsultar()) {
			rootEntryPointModel.add(sistemaControlePesoLinks.linkToPessoas("pessoas"));
			rootEntryPointModel.add(sistemaControlePesoLinks.linkToPesos("pesos"));
			rootEntryPointModel.add(sistemaControlePesoLinks.linkToPessoaExericico("pessoaexercicio"));
			rootEntryPointModel.add(sistemaControlePesoLinks.linkToExercicios("exercicios"));
			rootEntryPointModel.add(sistemaControlePesoLinks.linkToAlimentos("alimentos"));
			rootEntryPointModel.add(sistemaControlePesoLinks.linkToDietas("dietas"));
			rootEntryPointModel.add(sistemaControlePesoLinks.linkToAlimentoDieta("alimentodieta"));
			rootEntryPointModel.add(sistemaControlePesoLinks.linkToHistoricoMedico("historicomedico"));
		}
		
		return rootEntryPointModel;
				
	}

	private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel>{}
}