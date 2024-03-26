package com.sistemacontrolepeso.domain.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	private String email;
	
	@NotBlank
	@Column(nullable = false)
	private String endereco;
	
	@NotBlank
	@Column(nullable = false)
	private Double altura;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@OneToMany
	@JoinColumn(name = "pessoa_id")
	private List<HistoricoMedico> historicoMedico;
	
}
