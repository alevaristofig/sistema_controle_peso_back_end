package com.sistemacontrolepeso.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistemacontrolepeso.domain.model.Peso;

public interface PesoRepository extends JpaRepository<Peso, Long> {
	
	Peso findTop1ByOrderByIdAsc();
	Peso findTop1ByOrderByIdDesc();
}
