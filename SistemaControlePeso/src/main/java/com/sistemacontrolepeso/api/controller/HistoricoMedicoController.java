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

import com.sistemacontrolepeso.api.assembler.HistoricoMedicoInputDisassembler;
import com.sistemacontrolepeso.api.assembler.HistoricoMedicoModelAssembler;
import com.sistemacontrolepeso.api.model.HistoricoMedicoModel;
import com.sistemacontrolepeso.api.model.input.HistoricoMedicoInput;
import com.sistemacontrolepeso.domain.model.HistoricoMedico;
import com.sistemacontrolepeso.domain.repository.HistoricoMedicoRepository;
import com.sistemacontrolepeso.domain.service.CadastroHistoricoMedicoService;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(value = "/historicomedico", produces = MediaType.APPLICATION_JSON_VALUE)
public class HistoricoMedicoController {

	@Autowired
	private CadastroHistoricoMedicoService cadastroHistoricoMedicoService;
	
	@Autowired
	private HistoricoMedicoInputDisassembler historicoMedicoInputDisassembler;
	
	@Autowired
	private HistoricoMedicoModelAssembler historicoMedicoModelAssembler;
	
	@Autowired
	private HistoricoMedicoRepository historicoMedicoRepository;
	
	@GetMapping("/{id}")
	public HistoricoMedicoModel buscar(@PathVariable @Validated Long id) {
		HistoricoMedico historicoMedico = cadastroHistoricoMedicoService.buscarOuFalhar(id);
		
		return historicoMedicoModelAssembler.toModel(historicoMedico);
	}
	
	@GetMapping
	public List<HistoricoMedicoModel> listar() {
		List<HistoricoMedico> historicoMedico = historicoMedicoRepository.findAll();
		
		return historicoMedicoModelAssembler.toCollectionModel(historicoMedico);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public HistoricoMedicoModel salvar(@RequestBody @Validated HistoricoMedicoInput historicoMedicoInput) {
		HistoricoMedico historicoMedico = historicoMedicoInputDisassembler.toDomainObject(historicoMedicoInput);
		
		historicoMedico.setDataCadastro(LocalDateTime.now());
		historicoMedico.setDataAtualizacao(null);
		historicoMedico = cadastroHistoricoMedicoService.salvar(historicoMedico);
		
		return historicoMedicoModelAssembler.toModel(historicoMedico);
	}
	
	@PutMapping("/{id}")
	public HistoricoMedicoModel atualizar(@RequestBody @Validated HistoricoMedicoInput historicoMedicoInput, 
			@PathVariable Long id) {
		HistoricoMedico historicoMedico = cadastroHistoricoMedicoService.buscarOuFalhar(id);
		
		historicoMedicoInputDisassembler.copytoDomain(historicoMedicoInput, historicoMedico);
		historicoMedico.setDataAtualizacao(LocalDateTime.now());
		
		historicoMedico = cadastroHistoricoMedicoService.salvar(historicoMedico);
		
		return historicoMedicoModelAssembler.toModel(historicoMedico);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagar(@PathVariable @Validated Long id) {
		HistoricoMedico historicoMedico = cadastroHistoricoMedicoService.buscarOuFalhar(id);
		
		historicoMedicoRepository.delete(historicoMedico);
	}
}