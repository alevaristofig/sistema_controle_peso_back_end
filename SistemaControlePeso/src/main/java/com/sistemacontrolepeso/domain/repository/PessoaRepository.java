package com.sistemacontrolepeso.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import com.sistemacontrolepeso.domain.model.Pessoa;

import jakarta.transaction.Transactional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	Optional<Pessoa> findByEmail(String email);
	
	@Query(value = "select p.id, p.email from oauth2_authorization oauth "
			+ "inner join INNER JOIN pessoa p ON p.email = oauth.principal_name "
			+ "where oauth.access_token_value= :token",
			nativeQuery = true)
	Pessoa buscarIdEmailToken(@Param("token") String token);
	
	@Modifying
	@Transactional
	@Query(value = "delete from oauth2_authorization "
			+ "where access_token_value= :token",
			nativeQuery = true)
	void removerToken(@Param("token") String token);
}
