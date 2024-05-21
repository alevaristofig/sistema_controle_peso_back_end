package com.sistemacontrolepeso.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

import com.sistemacontrolepeso.api.v1.assembler.DietaInputDisassembler;
import com.sistemacontrolepeso.api.v1.assembler.DietaModelAssembler;
import com.sistemacontrolepeso.api.v1.model.DietaModel;
import com.sistemacontrolepeso.api.v1.model.input.DietaInput;
import com.sistemacontrolepeso.api.v1.openapi.controller.DietaControllerOpenApi;
import com.sistemacontrolepeso.core.security.CheckSecurity;
import com.sistemacontrolepeso.domain.model.Dieta;
import com.sistemacontrolepeso.domain.repository.DietaRepository;
import com.sistemacontrolepeso.domain.service.CadastroDietaService;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(value = "/v1/dietas", produces = MediaType.APPLICATION_JSON_VALUE)
public class DietaController implements DietaControllerOpenApi {

	@Autowired
	private DietaRepository dietaRepository;
	
	@Autowired
	private CadastroDietaService cadastroDietaService;
	
	@Autowired
	private DietaInputDisassembler dietaInputDisassembler;
	
	@Autowired
	private DietaModelAssembler dietaModelAssembler;
	
	@Autowired
	private PagedResourcesAssembler<Dieta> pagedResourcesAssembler;
	
	@CheckSecurity.Dietas.PodeConsultar
	@GetMapping("/listardietaspaginacao/{id}")
	public PagedModel<DietaModel> listar(@PathVariable Long id, @PageableDefault(size = 10) Pageable pageable) {
		Page<Dieta> dietasPage = dietaRepository.findAllByPessoaId(id, pageable);				
		
		PagedModel<DietaModel> dietasPagedModel = pagedResourcesAssembler
				.toModel(dietasPage,dietaModelAssembler);
		
		return dietasPagedModel;
	}
	
	@CheckSecurity.Dietas.PodeConsultar
	@GetMapping("/{id}")
	public DietaModel buscar(@PathVariable Long id) {
		Dieta dieta = cadastroDietaService.buscarOuFalhar(id);
		
		return dietaModelAssembler.toModel(dieta);
	}
	
	@CheckSecurity.Dietas.podeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DietaModel adicionar(@RequestBody @Validated DietaInput dietaInput) {
		Dieta dieta = dietaInputDisassembler.toDomainObject(dietaInput);
		
		dieta = cadastroDietaService.salvar(dieta);
		
		return dietaModelAssembler.toModel(dieta);
	}
	
	@CheckSecurity.Dietas.podeEditar
	@PutMapping("/{id}")
	public DietaModel atualizar(@PathVariable Long id, @RequestBody @Validated DietaInput dietaInput) {
		Dieta dieta = cadastroDietaService.buscarOuFalhar(id);
		
		dietaInputDisassembler.copytoDomain(dietaInput, dieta);
		
		dieta = cadastroDietaService.salvar(dieta);
		
		return dietaModelAssembler.toModel(dieta);
	}
	
	@CheckSecurity.Dietas.podeEditar
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Dieta dieta = cadastroDietaService.buscarOuFalhar(id);
		
		dietaRepository.delete(dieta);
		
		return ResponseEntity.noContent().build();
	}
}