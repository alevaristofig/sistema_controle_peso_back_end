package com.sistemacontrolepeso.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.api.v1.model.input.AlimentoDietaInput;
import com.sistemacontrolepeso.domain.model.AlimentoDieta;

@Component
public class AlimentoDietaInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public void copytoDomain(AlimentoDietaInput alimentoDietaInput, AlimentoDieta alimentoDieta) {
		modelMapper.map(alimentoDietaInput, alimentoDieta);
	}
}
