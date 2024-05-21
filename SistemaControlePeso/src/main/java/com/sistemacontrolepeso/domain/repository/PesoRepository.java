package com.sistemacontrolepeso.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sistemacontrolepeso.domain.model.Peso;

public interface PesoRepository extends JpaRepository<Peso, Long> {
	
	Optional<Peso> findTop1ByPessoaIdOrderByIdAsc(Long id);
	
	Optional<Peso> findTop1ByPessoaIdOrderByIdDesc(Long id);
	
	Page<Peso> findAllByPessoaId(Long id,Pageable pageable);
}
