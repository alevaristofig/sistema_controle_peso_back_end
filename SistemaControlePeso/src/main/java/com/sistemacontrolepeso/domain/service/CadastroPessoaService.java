package com.sistemacontrolepeso.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemacontrolepeso.domain.exception.PessoaNaoEncontradaException;
import com.sistemacontrolepeso.domain.model.Pessoa;
import com.sistemacontrolepeso.domain.repository.PessoaRepository;

import jakarta.transaction.Transactional;

@Service
public class CadastroPessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@Transactional
	public Pessoa salvar(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	public Pessoa buscarOuFalhar(Long id) {
		return pessoaRepository.findById(id)
			.orElseThrow(() -> new PessoaNaoEncontradaException(id));
	}
}
