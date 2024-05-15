package com.sistemacontrolepeso.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemacontrolepeso.domain.model.Exercicio;

public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
	
	Page<Exercicio> findAllByPessoaId(Long id,Pageable pageable);

}
