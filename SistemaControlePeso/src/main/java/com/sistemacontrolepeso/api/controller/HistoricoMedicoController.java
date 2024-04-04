package com.sistemacontrolepeso.api.controller;

import java.time.LocalDateTime;
import java.util.List;

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

import com.sistemacontrolepeso.api.assembler.HistoricoMedicoInputDisassembler;
import com.sistemacontrolepeso.api.assembler.HistoricoMedicoModelAssembler;
import com.sistemacontrolepeso.api.model.HistoricoMedicoModel;
import com.sistemacontrolepeso.api.model.input.HistoricoMedicoInput;
import com.sistemacontrolepeso.api.v1.openapi.controller.HistoricoMedicoControllerOpenApi;
import com.sistemacontrolepeso.domain.model.HistoricoMedico;
import com.sistemacontrolepeso.domain.repository.HistoricoMedicoRepository;
import com.sistemacontrolepeso.domain.service.CadastroHistoricoMedicoService;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(value = "/historicomedico", produces = MediaType.APPLICATION_JSON_VALUE)
public class HistoricoMedicoController implements HistoricoMedicoControllerOpenApi {

	@Autowired
	private CadastroHistoricoMedicoService cadastroHistoricoMedicoService;
	
	@Autowired
	private HistoricoMedicoInputDisassembler historicoMedicoInputDisassembler;
	
	@Autowired
	private HistoricoMedicoModelAssembler historicoMedicoModelAssembler;
	
	@Autowired
	private HistoricoMedicoRepository historicoMedicoRepository;
	
	@Autowired
	private PagedResourcesAssembler<HistoricoMedico> pagedResourcesAssembler;
	
	@GetMapping
	public PagedModel<HistoricoMedicoModel> listar(@PageableDefault(size = 10) Pageable pageable) {
		Page<HistoricoMedico> historicosMedicoPage = historicoMedicoRepository.findAll(pageable);
		
		PagedModel<HistoricoMedicoModel> historicosMedicoPagedModel = pagedResourcesAssembler
				.toModel(historicosMedicoPage,historicoMedicoModelAssembler);
		
		return historicosMedicoPagedModel;
	}
	
	@GetMapping("/{id}")
	public HistoricoMedicoModel buscar(@PathVariable @Validated Long id) {
		HistoricoMedico historicoMedico = cadastroHistoricoMedicoService.buscarOuFalhar(id);
		
		return historicoMedicoModelAssembler.toModel(historicoMedico);
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public HistoricoMedicoModel adicionar(@RequestBody @Validated HistoricoMedicoInput historicoMedicoInput) {
		HistoricoMedico historicoMedico = historicoMedicoInputDisassembler.toDomainObject(historicoMedicoInput);
		
		historicoMedico.setDataCadastro(LocalDateTime.now());
		historicoMedico.setDataAtualizacao(null);
		historicoMedico = cadastroHistoricoMedicoService.salvar(historicoMedico);
		
		return historicoMedicoModelAssembler.toModel(historicoMedico);
	}
	
	@PutMapping("/{id}")
	public HistoricoMedicoModel atualizar(@PathVariable Long id,
			@RequestBody @Validated HistoricoMedicoInput historicoMedicoInput) {
		HistoricoMedico historicoMedico = cadastroHistoricoMedicoService.buscarOuFalhar(id);
		
		historicoMedicoInputDisassembler.copytoDomain(historicoMedicoInput, historicoMedico);
		historicoMedico.setDataAtualizacao(LocalDateTime.now());
		
		historicoMedico = cadastroHistoricoMedicoService.salvar(historicoMedico);
		
		return historicoMedicoModelAssembler.toModel(historicoMedico);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> remover(@PathVariable @Validated Long id) {
		HistoricoMedico historicoMedico = cadastroHistoricoMedicoService.buscarOuFalhar(id);
		
		historicoMedicoRepository.delete(historicoMedico);
		
		return ResponseEntity.noContent().build();
	}
}
