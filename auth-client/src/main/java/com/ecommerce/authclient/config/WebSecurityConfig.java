package com.ecommerce.authclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.cors(AbstractHttpConfigurer::disable)
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests((requests) -> {
          try {
            requests
                .requestMatchers("/api/**").authenticated()
                .and()
                .oauth2Login((oauthLogin) -> oauthLogin.loginPage("/oauth2/authorization/api-client-oidc"))
                    .oauth2Client(Customizer.withDefaults());
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        });
    return http.build();
  }
}
