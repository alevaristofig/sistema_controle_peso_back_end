package com.sistemacontrolepeso.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemacontrolepeso.domain.model.AlimentoDieta;
import com.sistemacontrolepeso.domain.repository.AlimentoDietaRepository;

import jakarta.transaction.Transactional;

@Service
public class CadastroAlimentoDietaService {

	@Autowired
	private AlimentoDietaRepository alimentoDietaRepository;
	
	@Transactional
	public AlimentoDieta salvar(AlimentoDieta alimentoDieta) {
		return alimentoDietaRepository.save(alimentoDieta);
	}
}