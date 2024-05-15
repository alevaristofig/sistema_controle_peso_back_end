package com.sistemacontrolepeso.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemacontrolepeso.domain.model.Alimento;

public interface AlimentoRepositoy extends JpaRepository<Alimento, Long> {

	Page<Alimento> findAllByPessoaId(Long id,Pageable pageable);
}
