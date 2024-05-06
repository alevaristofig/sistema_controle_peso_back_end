package com.sistemacontrolepeso.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.model.input.PesoInput;
import com.sistemacontrolepeso.domain.model.Peso;

@Component
public class PesoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Peso toDomainObject(PesoInput pesoInput) {
		return modelMapper.map(pesoInput, Peso.class);
	}
	
	public void copyToDomain(PesoInput pesoInput, Peso peso) {
		modelMapper.map(pesoInput, peso);
	}
}
