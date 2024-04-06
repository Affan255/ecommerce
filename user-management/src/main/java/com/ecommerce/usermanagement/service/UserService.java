package com.ecommerce.usermanagement.service;

import com.ecommerce.usermanagement.dto.UserDto;
import com.ecommerce.usermanagement.exception.UserEmailAlreadyExistsException;
import com.ecommerce.usermanagement.models.User;
import com.ecommerce.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  private User mapUserDtoToUser(UserDto userDto){
    User user = new User();
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setEmail(userDto.getEmail());
    user.setRole(userDto.getRole());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    return user;
  }
  public User addUser(UserDto userDto) throws UserEmailAlreadyExistsException {
    User user = mapUserDtoToUser(userDto);
    if(userRepository.findByEmail(user.getEmail()) != null)
      throw new UserEmailAlreadyExistsException(user.getEmail());
    return userRepository.save(user);
  }

}
