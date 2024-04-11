package com.ecommerce.gateway.gatewayserver.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {

  @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
  String jwkSetUri;

  @Bean
  public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeExchange((authorize) -> authorize.pathMatchers("/products/api/products/**").hasAuthority("SCOPE_api.read"))
        .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec.jwt(Customizer.withDefaults()));
    httpSecurity.csrf(ServerHttpSecurity.CsrfSpec :: disable)
        .cors(ServerHttpSecurity.CorsSpec :: disable);

    return httpSecurity.build();
  }

  @Bean
  ReactiveJwtDecoder jwtDecoder() {
    return NimbusReactiveJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
  }

}

