package com.sistemacontrolepeso.core.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	public @interface Pesos {
		
		@PreAuthorize("hasAuthority('CRIAR_EDITAR_DELETAR_PESOS')")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface podeEditar{}
		
		@PreAuthorize("hasAuthority('@sistemaControlePesoSecurity.podeConsultarPesos()')")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeConsultar {}
	}
}
