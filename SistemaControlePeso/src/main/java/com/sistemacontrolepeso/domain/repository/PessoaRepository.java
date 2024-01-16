package com.sistemacontrolepeso.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemacontrolepeso.domain.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
