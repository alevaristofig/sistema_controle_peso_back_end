package com.sistemacontrolepeso.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemacontrolepeso.domain.model.PessoaExercicio;
import com.sistemacontrolepeso.domain.repository.PessoaExercicioRepository;

import jakarta.transaction.Transactional;

@Service
public class CadastroPessoaExercicioService {

	@Autowired
	PessoaExercicioRepository pessoaExercicioRepository;
	
	@Transactional
	public PessoaExercicio salvar(PessoaExercicio pessoaExercicio) {
		return pessoaExercicioRepository.save(pessoaExercicio);
	}
}
