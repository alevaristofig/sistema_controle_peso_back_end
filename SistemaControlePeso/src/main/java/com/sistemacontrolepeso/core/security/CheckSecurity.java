package com.sistemacontrolepeso.core.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {
	
	public @interface Pessoas {
		@PreAuthorize("@sistemaControlePesoSecurity.podeConsultar()")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeConsultar {}
	}

	public @interface Pesos {
		
		@PreAuthorize("hasAuthority('CRIAR_EDITAR_DELETAR_PESOS')")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface podeEditar{}
		
		@PreAuthorize("@sistemaControlePesoSecurity.podeConsultar()")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeConsultar {}
	}
	
	public @interface PessoasExercicios {
		@PreAuthorize("@sistemaControlePesoSecurity.podeConsultar()")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeConsultar {}
	}
	
	public @interface Exercicios {
		@PreAuthorize("@sistemaControlePesoSecurity.podeConsultar()")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeConsultar {}
	}
	
	public @interface Alimentos {
		@PreAuthorize("@sistemaControlePesoSecurity.podeConsultar()")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeConsultar {}
	}
	
	public @interface Dietas {
		@PreAuthorize("@sistemaControlePesoSecurity.podeConsultar()")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeConsultar {}
	}
	
	public @interface HistoricosMedico {
		@PreAuthorize("@sistemaControlePesoSecurity.podeConsultar()")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeConsultar {}
	}
	
}
