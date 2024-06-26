package com.sistemacontrolepeso.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.sistemacontrolepeso.api.v1.assembler.AlimentoDietaInputDisassembler;
import com.sistemacontrolepeso.api.v1.assembler.AlimentoDietaModelAssembler;
import com.sistemacontrolepeso.api.v1.model.AlimentoDietaModel;
import com.sistemacontrolepeso.api.v1.model.input.AlimentoDietaInput;
import com.sistemacontrolepeso.api.v1.openapi.controller.AlimentoDietaControllerOpenApi;
import com.sistemacontrolepeso.core.security.CheckSecurity;
import com.sistemacontrolepeso.domain.model.Alimento;
import com.sistemacontrolepeso.domain.model.AlimentoDieta;
import com.sistemacontrolepeso.domain.model.Dieta;
import com.sistemacontrolepeso.domain.repository.AlimentoDietaRepository;
import com.sistemacontrolepeso.domain.service.CadastroAlimentoDietaService;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(value = "/v1/alimentodieta", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlimentoDietaController implements AlimentoDietaControllerOpenApi {

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
	
	@CheckSecurity.AlimentosDietas.podeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AlimentoDieta adicionar(@RequestBody @Validated AlimentoDietaInput alimentoDietaInput) {
		
		Dieta dieta = new Dieta();
		dieta.setId(alimentoDietaInput.getDietaId().getId());
		
		Alimento alimento = new Alimento();
		alimento.setId(alimentoDietaInput.getAlimentoId().getId());
		
		AlimentoDieta alimentoDieta = new AlimentoDieta();
		alimentoDieta.setDieta(dieta);
		alimentoDieta.setAlimento(alimento);
		
		return cadastroAlimentoDietaService.salvar(alimentoDieta);
	}
	
	@CheckSecurity.AlimentosDietas.podeEditar
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
		
		alimentoDieta = cadastroAlimentoDietaService.salvar(alimentoDieta);
		
		return alimentoDietaModelAssembler.toModel(alimentoDieta);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		AlimentoDieta alimentoDieta = cadastroAlimentoDietaService.buscarOuFalhar(id);
		
		alimentoRepository.delete(alimentoDieta);
		
		return ResponseEntity.noContent().build();
	}
}
