package com.sistemacontrolepeso.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sistemacontrolepeso.domain.model.PessoaExercicio;


public interface PessoaExercicioRepository extends JpaRepository<PessoaExercicio, Long> {

	@Query("select new com.sistemacontrolepeso.domain.model.dto.PessoaExercicio("
			+ "pe.exercicio.id, count(pe), e.nome) from PessoaExercicio pe "
			+ "inner join pe.exercicio e "
			+ "where treino= :treino "
			+ "group by  pe.exercicio")
	public List<com.sistemacontrolepeso.domain.model.dto.PessoaExercicio> listarQuantidadeTreinos(
			@Param("treino") char treino);
}
