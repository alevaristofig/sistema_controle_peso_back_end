package com.sistemacontrolepeso.api.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

import com.sistemacontrolepeso.api.assembler.AlimentoDietaInputDisassembler;
import com.sistemacontrolepeso.api.assembler.AlimentoDietaModelAssembler;
import com.sistemacontrolepeso.api.model.AlimentoDietaModel;
import com.sistemacontrolepeso.api.model.DietaModel;
import com.sistemacontrolepeso.api.model.input.AlimentoDietaInput;
import com.sistemacontrolepeso.api.model.input.DietaInput;
import com.sistemacontrolepeso.domain.model.Alimento;
import com.sistemacontrolepeso.domain.model.AlimentoDieta;
import com.sistemacontrolepeso.domain.model.Dieta;
import com.sistemacontrolepeso.domain.repository.AlimentoDietaRepository;
import com.sistemacontrolepeso.domain.service.CadastroAlimentoDietaService;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(value = "/alimentodieta", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlimentoDietaController {

	@Autowired
	private CadastroAlimentoDietaService cadastroAlimentoDietaService;
	
	@Autowired
	private AlimentoDietaModelAssembler alimentoDietaModelAssembler;
	
	@Autowired
	private AlimentoDietaInputDisassembler alimentoDietaInputDisassembler;
	
	@Autowired
	private AlimentoDietaRepository alimentoRepository;
	
	@GetMapping("/{id}")
	public List<AlimentoDietaModel> buscar(@PathVariable Long id) {
		Dieta dieta = new Dieta();
		dieta.setId(id);
		
		List<AlimentoDieta> alimentoDieta = alimentoRepository.buscarAlimentoDietaId(dieta);
		
		return alimentoDietaModelAssembler.toCollectionModel(alimentoDieta);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AlimentoDieta salvar(@RequestBody @Validated AlimentoDietaInput alimentoDietaInput) {
		
		Dieta dieta = new Dieta();
		dieta.setId(alimentoDietaInput.getDietaId().getId());
		
		Alimento alimento = new Alimento();
		alimento.setId(alimentoDietaInput.getAlimentoId().getId());
		
		AlimentoDieta alimentoDieta = new AlimentoDieta();
		alimentoDieta.setDieta(dieta);
		alimentoDieta.setAlimento(alimento);
		alimentoDieta.setDataCriacao(LocalDateTime.now());
		alimentoDieta.setDataAtualizacao(null);
		
		return cadastroAlimentoDietaService.salvar(alimentoDieta);
	}
	
	@PutMapping("/{id}")
	public AlimentoDietaModel atualizar(@PathVariable Long id, 
										@RequestBody @Validated AlimentoDietaInput alimentoDietaInput) {
		AlimentoDieta alimentoDieta = cadastroAlimentoDietaService.buscarOuFalhar(id);
		
		Dieta dieta = new Dieta();
		dieta.setId(alimentoDietaInput.getDietaId().getId());
		
		Alimento alimento = new Alimento();
		alimento.setId(alimentoDietaInput.getAlimentoId().getId());
		
		alimentoDieta.setDieta(dieta);
		alimentoDieta.setAlimento(alimento);
		alimentoDieta.setDataCriacao(alimentoDietaInput.getDataCriacao());
		alimentoDieta.setDataAtualizacao(LocalDateTime.now());
		
		alimentoDieta = cadastroAlimentoDietaService.salvar(alimentoDieta);
		
		return alimentoDietaModelAssembler.toModel(alimentoDieta);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagar(@PathVariable Long id) {
		AlimentoDieta alimentoDieta = cadastroAlimentoDietaService.buscarOuFalhar(id);
		
		alimentoRepository.delete(alimentoDieta);
	}
}
