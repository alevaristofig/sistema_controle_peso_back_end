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

import com.sistemacontrolepeso.api.v1.assembler.HistoricoMedicoInputDisassembler;
import com.sistemacontrolepeso.api.v1.assembler.HistoricoMedicoModelAssembler;
import com.sistemacontrolepeso.api.v1.model.HistoricoMedicoModel;
import com.sistemacontrolepeso.api.v1.model.input.HistoricoMedicoInput;
import com.sistemacontrolepeso.api.v1.openapi.controller.HistoricoMedicoControllerOpenApi;
import com.sistemacontrolepeso.core.security.CheckSecurity;
import com.sistemacontrolepeso.domain.model.HistoricoMedico;
import com.sistemacontrolepeso.domain.repository.HistoricoMedicoRepository;
import com.sistemacontrolepeso.domain.service.CadastroHistoricoMedicoService;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(value = "/v1/historicomedico", produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	@CheckSecurity.HistoricosMedico.PodeConsultar
	@GetMapping("/listarhistoricomedicopaginacao/{id}")
	public PagedModel<HistoricoMedicoModel> listar(@PathVariable Long id, @PageableDefault(size = 10) Pageable pageable) {
		Page<HistoricoMedico> historicosMedicoPage = historicoMedicoRepository.findAllByPessoaId(id, pageable);
		
		PagedModel<HistoricoMedicoModel> historicosMedicoPagedModel = pagedResourcesAssembler
				.toModel(historicosMedicoPage,historicoMedicoModelAssembler);
		
		return historicosMedicoPagedModel;
	}
	
	@CheckSecurity.HistoricosMedico.PodeConsultar
	@GetMapping("/{id}")
	public HistoricoMedicoModel buscar(@PathVariable @Validated Long id) {
		HistoricoMedico historicoMedico = cadastroHistoricoMedicoService.buscarOuFalhar(id);
		
		return historicoMedicoModelAssembler.toModel(historicoMedico);
	}
	
	@CheckSecurity.HistoricosMedico.podeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public HistoricoMedicoModel adicionar(@RequestBody @Validated HistoricoMedicoInput historicoMedicoInput) {
		HistoricoMedico historicoMedico = historicoMedicoInputDisassembler.toDomainObject(historicoMedicoInput);
		
		historicoMedico = cadastroHistoricoMedicoService.salvar(historicoMedico);
		
		return historicoMedicoModelAssembler.toModel(historicoMedico);
	}
	
	@CheckSecurity.HistoricosMedico.podeEditar
	@PutMapping("/{id}")
	public HistoricoMedicoModel atualizar(@PathVariable Long id,
			@RequestBody @Validated HistoricoMedicoInput historicoMedicoInput) {
		HistoricoMedico historicoMedico = cadastroHistoricoMedicoService.buscarOuFalhar(id);
		
		historicoMedicoInputDisassembler.copytoDomain(historicoMedicoInput, historicoMedico);		
		
		historicoMedico = cadastroHistoricoMedicoService.salvar(historicoMedico);
		
		return historicoMedicoModelAssembler.toModel(historicoMedico);
		
	}
	
	@CheckSecurity.HistoricosMedico.podeEditar
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> remover(@PathVariable @Validated Long id) {
		HistoricoMedico historicoMedico = cadastroHistoricoMedicoService.buscarOuFalhar(id);
		
		historicoMedicoRepository.delete(historicoMedico);
		
		return ResponseEntity.noContent().build();
	}
}
