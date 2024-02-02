package com.sistemacontrolepeso.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemacontrolepeso.domain.exception.PesoNaoEncontradoException;
import com.sistemacontrolepeso.domain.model.Peso;
import com.sistemacontrolepeso.domain.repository.PesoRepository;

import jakarta.transaction.Transactional;

@Service
public class CadastroPesoService {

	@Autowired
	private PesoRepository pesoRepository;
	
	@Transactional
	public Peso salvar(Peso peso) {
		return pesoRepository.save(peso);
	}
	
	public Peso buscarOuFalhar(Long id) {
		return pesoRepository.findById(id)
				.orElseThrow(() -> new PesoNaoEncontradoException(id)); 
	}
}
