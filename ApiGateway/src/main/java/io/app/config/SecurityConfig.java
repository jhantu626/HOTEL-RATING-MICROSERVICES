package io.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig{

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
//		return http.csrf(csrf->csrf.disable())
//				.authorizeExchange(auth->auth.anyExchange()
//						.authenticated())
//				.oauth2Login(Customizer.withDefaults())
//				.oauth2ResourceServer(oauth->oauth.jwt(Customizer.withDefaults()))
//				.build();
		
	}


}
