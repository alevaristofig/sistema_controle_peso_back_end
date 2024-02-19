package com.sistemacontrolepeso.domain.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class PessoaExercicio {

	@JsonIgnore
	@EqualsAndHashCode.Include
	@EmbeddedId
	private PessoaExercicioPK id = new PessoaExercicioPK();
	
	@CreationTimestamp
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataAtualizar;
	
	public PessoaExercicio(Pessoa pessoa, Exercicio exercicio) {
		id.setPessoa(pessoa);
		id.setExercicio(exercicio);		
	}
}