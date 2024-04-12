package com.ecommerce.gateway.gatewayserver.config;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class JwtAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

  @Override
  public Collection<GrantedAuthority> convert(Jwt source) {
    List<String> jwtAuthorities = (List<String>) source.getClaims().get("authorities");
    return jwtAuthorities.stream()
        .map(rolename -> "ROLE_"+rolename)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }
}
