package com.sistemacontrolepeso.core.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class ResourceServerConfig {

	@Bean
	public SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception {		
		http.formLogin(form -> form
				.loginPage("/login")
				.permitAll())
				.logout((logout) -> logout.logoutRequestMatcher(
							new AntPathRequestMatcher("/logout", "GET")
						)
						.logoutSuccessUrl("http://localhost:3000/login")
						.clearAuthentication(true)
						.invalidateHttpSession(true)
						.deleteCookies()
						)
			.authorizeHttpRequests(authorize -> authorize
				.anyRequest().authenticated())			
			.oauth2ResourceServer(oauth2 -> oauth2
					.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
			);
		
		return http.build();
	}
	
	private JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
		
		converter.setJwtGrantedAuthoritiesConverter(jwt -> {
			JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
			
			//ler permissões vinda do scope
			Collection<GrantedAuthority> grantedAuthorites = authoritiesConverter.convert(jwt);
			
			//"authorities" é um campo costumizado no AuthorizationServerConfig
			List<String> authorities = jwt.getClaimAsStringList("authorities");
			
			if(authorities == null) {
				return grantedAuthorites;
			}
			
			grantedAuthorites.addAll(authorities
				.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList()));
			
			return  grantedAuthorites;
		});
		
		return converter;
	}
}
