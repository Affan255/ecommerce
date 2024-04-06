package com.ecommerce.usermanagement.service;

import com.ecommerce.usermanagement.models.User;
import com.ecommerce.usermanagement.repository.UserRepository;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException("User with username " + username + " does not exist");
    }
    return new org.springframework.security.core.userdetails.User(username,
        user.getPassword(), true, true, true, true,
        getAuthorities(Arrays.asList(user.getRole())));

  }

  private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
    return
        roles.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
  }
}
