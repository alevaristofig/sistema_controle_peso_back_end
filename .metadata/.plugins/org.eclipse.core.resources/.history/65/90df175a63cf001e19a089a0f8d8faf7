package com.sistemacontrolepeso.domain.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
public class PessoaExercicioPK {

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(name = "exercicio_id")
	private Exercicio exercicio;
}
