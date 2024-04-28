package com.sistemacontrolepeso.core.security.authorizationserver;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@Validated
@ConfigurationProperties("sistemacontrolepeso.auth")
public class SistemaControlePesoSecurityProperties {

	@NotBlank
	private String providerUrl;
}
