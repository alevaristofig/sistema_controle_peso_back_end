package com.sistemacontrolepeso.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemacontrolepeso.domain.model.Dieta;

public interface DietaRepository extends JpaRepository<Dieta, Long>{
	
	Page<Dieta> findAllByPessoaId(Long id,Pageable pageable);
}
