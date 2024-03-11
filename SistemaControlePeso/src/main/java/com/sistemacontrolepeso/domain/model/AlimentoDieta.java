package com.sistemacontrolepeso.domain.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class AlimentoDieta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@CreationTimestamp
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataCriacao;
	
	@CreationTimestamp
	@Column(columnDefinition = "datetime")		
	private LocalDateTime dataAtualizacao;
	
	@ManyToOne
	@JoinColumn(name = "alimento_id")
	private Alimento alimento;
	
	@ManyToOne
	@JoinColumn(name = "dieta_id")
	private Dieta dieta;
}