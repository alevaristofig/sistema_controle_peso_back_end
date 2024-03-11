package com.sistemacontrolepeso.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemacontrolepeso.domain.exception.DietaNaoEncontradoException;
import com.sistemacontrolepeso.domain.model.Dieta;
import com.sistemacontrolepeso.domain.repository.DietaRepository;

import jakarta.transaction.Transactional;

@Service
public class CadastroDietaService {

	@Autowired
	private DietaRepository dietaRepository;
	
	@Transactional
	public Dieta salvar(Dieta dieta) {
		return dietaRepository.save(dieta);
	}
	
	public Dieta buscarOuFalhar(Long id) {
		return dietaRepository.findById(id)
				.orElseThrow(() -> new DietaNaoEncontradoException(id));
	}
}