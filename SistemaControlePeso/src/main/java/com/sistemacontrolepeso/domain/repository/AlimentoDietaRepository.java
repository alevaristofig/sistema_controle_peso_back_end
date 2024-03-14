package com.sistemacontrolepeso.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sistemacontrolepeso.domain.model.AlimentoDieta;
import com.sistemacontrolepeso.domain.model.Dieta;

public interface AlimentoDietaRepository extends JpaRepository<AlimentoDieta, Long> {

	@Query("from AlimentoDieta where dieta = :dieta")
	List<AlimentoDieta> buscarAlimentoDietaId(@Param("dieta") Dieta dieta);
}
