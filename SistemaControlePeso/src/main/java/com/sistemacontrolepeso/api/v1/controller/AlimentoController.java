package com.sistemacontrolepeso.api.v1.controller;

import java.time.OffsetDateTime;
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

import com.sistemacontrolepeso.api.v1.assembler.AlimentoInputDisassembler;
import com.sistemacontrolepeso.api.v1.assembler.AlimentoModelAssembler;
import com.sistemacontrolepeso.api.v1.model.AlimentoModel;
import com.sistemacontrolepeso.api.v1.model.input.AlimentoInput;
import com.sistemacontrolepeso.api.v1.openapi.controller.AlimentoControllerOpenApi;
import com.sistemacontrolepeso.core.security.CheckSecurity;
import com.sistemacontrolepeso.domain.model.Alimento;
import com.sistemacontrolepeso.domain.repository.AlimentoRepositoy;
import com.sistemacontrolepeso.domain.service.CadastroAlimentoService;


@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(value = "/v1/alimentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlimentoController implements AlimentoControllerOpenApi {

	@Autowired
	private CadastroAlimentoService cadastroAlimentoService;
	
	@Autowired
	private AlimentoInputDisassembler alimentoInputDisassembler;
	
	@Autowired
	private AlimentoModelAssembler alimentoModelAssembler;
	
	@Autowired
	private AlimentoRepositoy alimentoRepositoy;
	
	@Autowired
	private PagedResourcesAssembler<Alimento> pagedResourcesAssembler;
	
	@CheckSecurity.Alimentos.PodeConsultar
	@GetMapping("/{id}")
	public AlimentoModel buscar(@PathVariable Long id) {
		Alimento alimento = cadastroAlimentoService.buscarOuFalhar(id);
		
		return alimentoModelAssembler.toModel(alimento);
	}
	
	@CheckSecurity.Alimentos.PodeConsultar
	@GetMapping("/listaralimentospaginacao/{id}")
	public PagedModel<AlimentoModel> listar(@PathVariable Long id, @PageableDefault(size = 10) Pageable pageable){
		Page<Alimento> alimentoPage = alimentoRepositoy.findAllByPessoaId(id, pageable);
		
		PagedModel<AlimentoModel> alimentosPagedModel = pagedResourcesAssembler
				.toModel(alimentoPage,alimentoModelAssembler);
		
		return alimentosPagedModel;
	}
	
	@CheckSecurity.Alimentos.PodeConsultar
	@GetMapping("/listaralimentos")
	public List<AlimentoModel> listarSemPaginacao(){
		List<Alimento> alimentos = alimentoRepositoy.findAll();
		
		return  alimentoModelAssembler.toCollectionModel(alimentos);
	}
	
	@CheckSecurity.Alimentos.podeEditar
	@PutMapping("/{id}")
	public AlimentoModel atualizar(@PathVariable Long id, @RequestBody AlimentoInput alimentoInput) {
		Alimento alimento = cadastroAlimentoService.buscarOuFalhar(id);
		
		alimentoInputDisassembler.copytoDomain(alimentoInput, alimento);
		alimento.setDataAtualizacao(OffsetDateTime.now());
		
		alimento = cadastroAlimentoService.salvar(alimento);
		
		return alimentoModelAssembler.toModel(alimento);
	}
	
	@CheckSecurity.Alimentos.podeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AlimentoModel adicionar(@RequestBody @Validated AlimentoInput alimentoInput) {
		
		Alimento alimento = alimentoInputDisassembler.toDomainObject(alimentoInput);
		
		alimento = cadastroAlimentoService.salvar(alimento);
		
		return alimentoModelAssembler.toModel(alimento);
		
	}
	
	@CheckSecurity.Alimentos.podeEditar
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Alimento alimento = cadastroAlimentoService.buscarOuFalhar(id);
		
		alimentoRepositoy.delete(alimento);
		
		return ResponseEntity.noContent().build();
	}
}
