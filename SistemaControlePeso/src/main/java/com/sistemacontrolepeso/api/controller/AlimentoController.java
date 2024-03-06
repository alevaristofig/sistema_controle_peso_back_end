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

import com.sistemacontrolepeso.api.assembler.AlimentoInputDisassembler;
import com.sistemacontrolepeso.api.assembler.AlimentoModelAssembler;
import com.sistemacontrolepeso.api.model.AlimentoModel;
import com.sistemacontrolepeso.api.model.input.AlimentoInput;
import com.sistemacontrolepeso.domain.model.Alimento;
import com.sistemacontrolepeso.domain.repository.AlimentoRepositoy;
import com.sistemacontrolepeso.domain.service.CadastroAlimentoService;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(value = "/alimentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlimentoController {

	@Autowired
	private CadastroAlimentoService cadastroAlimentoService;
	
	@Autowired
	private AlimentoInputDisassembler alimentoInputDisassembler;
	
	@Autowired
	private AlimentoModelAssembler alimentoModelAssembler;
	
	@Autowired
	private AlimentoRepositoy alimentoRepositoy;
	
	@GetMapping("/{id}")
	public AlimentoModel buscar(@PathVariable Long id) {
		Alimento alimento = cadastroAlimentoService.buscarOuFalhar(id);
		
		return alimentoModelAssembler.toModel(alimento);
	}
	
	@GetMapping
	public List<AlimentoModel> listar(){
		List<Alimento> alimentos = alimentoRepositoy.findAll();
		
		return alimentoModelAssembler.toCollectionModel(alimentos);
	}
	
	@PutMapping("/{id}")
	public AlimentoModel atualizar(@PathVariable Long id, @RequestBody AlimentoInput alimentoInput) {
		Alimento alimento = cadastroAlimentoService.buscarOuFalhar(id);
		
		alimentoInputDisassembler.copytoDomain(alimentoInput, alimento);
		alimento.setDataCadastro(LocalDateTime.now());
		alimento.setDataAtualizacao(LocalDateTime.now());
		
		alimento = cadastroAlimentoService.salvar(alimento);
		
		return alimentoModelAssembler.toModel(alimento);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AlimentoModel salvar(@RequestBody @Validated AlimentoInput alimentoInput) {
		Alimento alimento = alimentoInputDisassembler.toDomainObject(alimentoInput);
		alimento.setDataCadastro(LocalDateTime.now());
		alimento.setDataAtualizacao(null);
		
		alimento = cadastroAlimentoService.salvar(alimento);
		
		return alimentoModelAssembler.toModel(alimento);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagar(@PathVariable Long id) {
		Alimento alimento = cadastroAlimentoService.buscarOuFalhar(id);
		
		alimentoRepositoy.delete(alimento);
	}
}