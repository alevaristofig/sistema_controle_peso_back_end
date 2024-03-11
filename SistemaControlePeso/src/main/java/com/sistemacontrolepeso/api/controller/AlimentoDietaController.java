package com.sistemacontrolepeso.api.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sistemacontrolepeso.api.model.input.AlimentoDietaInput;
import com.sistemacontrolepeso.domain.model.Alimento;
import com.sistemacontrolepeso.domain.model.AlimentoDieta;
import com.sistemacontrolepeso.domain.model.Dieta;
import com.sistemacontrolepeso.domain.service.CadastroAlimentoDietaService;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(value = "/alimentodieta", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlimentoDietaController {

	@Autowired
	private CadastroAlimentoDietaService cadastroAlimentoDietaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AlimentoDieta salvar(@RequestBody @Validated AlimentoDietaInput alimentoDietaInput) {
		
		Dieta dieta = new Dieta();
		dieta.setId(alimentoDietaInput.getDietaId().getId());
		
		Alimento alimento = new Alimento();
		alimento.setId(alimentoDietaInput.getDietaId().getId());
		
		AlimentoDieta alimentoDieta = new AlimentoDieta();
		alimentoDieta.setDieta(dieta);
		alimentoDieta.setAlimento(alimento);
		alimentoDieta.setDataAtualizacao(LocalDateTime.now());
		alimentoDieta.setDataAtualizacao(null);
		
		return cadastroAlimentoDietaService.salvar(alimentoDieta);
	}
}
