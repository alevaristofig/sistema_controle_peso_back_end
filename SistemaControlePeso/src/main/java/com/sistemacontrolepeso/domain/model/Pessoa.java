package com.sistemacontrolepeso.domain.model;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	
	@NotNull
	@Column(nullable = false)
	private Double altura;
	
	@NotBlank
	@Column(nullable = false)
	private String senha;
	
	@NotNull
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(nullable = true, columnDefinition = "datetime")
	private OffsetDateTime dataAtualizacao;
	
	@OneToMany
	@JoinColumn(name = "pessoa_id")
	private List<HistoricoMedico> historicoMedico;
	
	@OneToMany
	@JoinColumn(name = "pessoa_id")
	private List<Alimento> alimento;
	
	@OneToMany
	@JoinColumn(name = "pessoa_id")
	private List<Dieta> dieta;
	
	@OneToMany
	@JoinColumn(name = "pessoa_id")
	private List<Exercicio> exercicio;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "pessoa_grupo", joinColumns = @JoinColumn(name = "pessoa_id"),
				inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	private Set<Grupo> grupos = new HashSet<>();
	
}
