package com.sistemacontrolepeso.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sistemacontrolepeso.domain.model.Peso;

public interface PesoRepository extends JpaRepository<Peso, Long> {
	
	Peso findTop1ByPessoaIdOrderByIdAsc(Long id);
	
	Peso findTop1ByPessoaIdOrderByIdDesc(Long id);
	
	Page<Peso> findAllByPessoaId(Long id,Pageable pageable);
}
