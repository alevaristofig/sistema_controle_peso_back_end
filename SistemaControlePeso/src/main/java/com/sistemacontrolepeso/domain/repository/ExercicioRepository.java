package com.sistemacontrolepeso.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemacontrolepeso.domain.model.Exercicio;

public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {

}
