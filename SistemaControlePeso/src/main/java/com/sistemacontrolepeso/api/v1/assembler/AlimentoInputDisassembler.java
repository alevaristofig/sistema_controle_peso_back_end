package com.sistemacontrolepeso.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.v1.model.input.AlimentoInput;
import com.sistemacontrolepeso.domain.model.Alimento;

@Component
public class AlimentoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Alimento toDomainObject(AlimentoInput alimentoInput) {
		return modelMapper.map(alimentoInput, Alimento.class);
	}
	
	public void copytoDomain(AlimentoInput alimentoInput, Alimento alimento) {
		modelMapper.map(alimentoInput, alimento);
	}
}
