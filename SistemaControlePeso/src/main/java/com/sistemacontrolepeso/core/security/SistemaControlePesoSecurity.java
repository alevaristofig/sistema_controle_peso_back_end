package com.sistemacontrolepeso.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SistemaControlePesoSecurity {
	
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public boolean isAutenticado() {
		return getAuthentication().isAuthenticated();
	}
	
	public boolean podeConsultarPesos() {
		return isAutenticado();
	}
}