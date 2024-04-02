package com.sistemacontrolepeso.api.v1;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.controller.PesoController;

@Component
public class SistemaControlePesoLinks {

	public static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM));
	
	public static final TemplateVariables PROJECAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("projecao", VariableType.REQUEST_PARAM));
	
	public Link linkToPesos(Long pesoId, String rel) {
		return linkTo(methodOn(PesoController.class)
				.buscar(pesoId)).withRel(rel);
	}
	
	public Link linkToPesos(Long pesoId) {
		return linkToPesos(pesoId,IanaLinkRelations.SELF.value());
	}
	
	public Link linkToPesos(String rel) {
		return linkTo(PesoController.class).withRel(rel);
	}
	
	public Link linkToPesos() {
		return linkToPesos(IanaLinkRelations.SELF.value());
	}
	
}
