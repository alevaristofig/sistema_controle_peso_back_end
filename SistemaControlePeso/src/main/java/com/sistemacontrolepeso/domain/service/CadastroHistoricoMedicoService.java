package com.sistemacontrolepeso.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemacontrolepeso.domain.exception.HistoricoMedicoNaoEncontradoException;
import com.sistemacontrolepeso.domain.model.HistoricoMedico;
import com.sistemacontrolepeso.domain.repository.HistoricoMedicoRepository;

import jakarta.transaction.Transactional;

@Component
public class CadastroHistoricoMedicoService {

	@Autowired
	private HistoricoMedicoRepository historicoMedicoRepository;
	
	@Transactional
	public HistoricoMedico salvar(HistoricoMedico historicoMedico) {
		return historicoMedicoRepository.save(historicoMedico);
	}
	
	public HistoricoMedico buscarOuFalhar(Long id) {
		return historicoMedicoRepository.findById(id)
				.orElseThrow(() -> new HistoricoMedicoNaoEncontradoException(id));
	}
}
