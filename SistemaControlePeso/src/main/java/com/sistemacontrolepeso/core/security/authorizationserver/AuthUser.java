package com.sistemacontrolepeso.core.security.authorizationserver;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.sistemacontrolepeso.domain.model.Pessoa;

import lombok.Getter;

@Getter
public class AuthUser extends User {
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String fullName;
	
	public AuthUser(Pessoa pessoa, Collection<? extends GrantedAuthority> authorities) {			
		super(pessoa.getEmail(), pessoa.getSenha(), authorities);
		
		this.userId = pessoa.getId();
		this.fullName = pessoa.getEmail();
	}
	
	
}