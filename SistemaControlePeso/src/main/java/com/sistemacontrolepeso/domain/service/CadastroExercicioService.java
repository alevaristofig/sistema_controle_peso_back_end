package com.sistemacontrolepeso.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemacontrolepeso.domain.exception.ExercicioNaoEncontradoException;
import com.sistemacontrolepeso.domain.model.Exercicio;
import com.sistemacontrolepeso.domain.repository.ExercicioRepository;

import jakarta.transaction.Transactional;

@Service
public class CadastroExercicioService {

	@Autowired
	private ExercicioRepository exercicioRepository;
	
	@Transactional
	public Exercicio salvar(Exercicio exercicio) {
		return exercicioRepository.save(exercicio);
	}
	
	public Exercicio buscarOuFalhar(Long id) {
		return exercicioRepository.findById(id)
				.orElseThrow(() -> new ExercicioNaoEncontradoException(id));
	}
}