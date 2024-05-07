package com.sistemacontrolepeso.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.v1.model.input.DietaInput;
import com.sistemacontrolepeso.domain.model.Dieta;

@Component
public class DietaInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Dieta toDomainObject(DietaInput dietaInput) {
		return modelMapper.map(dietaInput, Dieta.class);
	}
	
	public void copytoDomain(DietaInput dietaInput, Dieta dieta) {
		modelMapper.map(dietaInput, dieta);
	}
}
