package com.sistemacontrolepeso.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemacontrolepeso.domain.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	Optional<Pessoa> findByEmail(String email);
}
