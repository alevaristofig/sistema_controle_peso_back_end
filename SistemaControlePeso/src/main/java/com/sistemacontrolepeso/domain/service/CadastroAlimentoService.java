package com.sistemacontrolepeso.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemacontrolepeso.domain.exception.AlimentoNaoEncontradoException;
import com.sistemacontrolepeso.domain.model.Alimento;
import com.sistemacontrolepeso.domain.repository.AlimentoRepositoy;

import jakarta.transaction.Transactional;

@Service
public class CadastroAlimentoService {

	@Autowired
	private AlimentoRepositoy alimentoRepositoy;
	
	@Transactional
	public Alimento salvar(Alimento alimento) {
		return alimentoRepositoy.save(alimento);
	}
	
	public Alimento buscarOuFalhar(Long alimentoId) {
		return alimentoRepositoy.findById(alimentoId)
				.orElseThrow(() -> new AlimentoNaoEncontradoException(alimentoId));
	}
}
