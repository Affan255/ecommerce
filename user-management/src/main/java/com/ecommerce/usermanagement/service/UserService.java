package com.ecommerce.usermanagement.service;

import com.ecommerce.usermanagement.dto.CartDto;
import com.ecommerce.usermanagement.dto.UserDto;
import com.ecommerce.usermanagement.exception.CartCreationException;
import com.ecommerce.usermanagement.exception.UserEmailAlreadyExistsException;
import com.ecommerce.usermanagement.models.User;
import com.ecommerce.usermanagement.repository.UserRepository;
import com.ecommerce.usermanagement.service.client.CartFeignClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private CartFeignClient cartFeignClient;

  private User mapUserDtoToUser(UserDto userDto){
    User user = new User();
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setEmail(userDto.getEmail());
    user.setRole(userDto.getRole());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    return user;
  }

  @Transactional(rollbackOn = {UserEmailAlreadyExistsException.class,
      CartCreationException.class})
  public User addUser(UserDto userDto) throws UserEmailAlreadyExistsException, CartCreationException {

    User user = mapUserDtoToUser(userDto);
    if(userRepository.findByEmail(user.getEmail()) != null)
      throw new UserEmailAlreadyExistsException(user.getEmail());
    User savedUSer =  userRepository.save(user);

    // Create a cart for this user
    CartDto cartDto = new CartDto();
    cartDto.setUserId(savedUSer.getId());
    cartDto.setEmail(savedUSer.getEmail());
    try {
      cartFeignClient.createCart(cartDto);
    } catch (Exception exception){
        throw new CartCreationException("Cart for user " + userDto.getEmail() + " cannot be created!");
    }
    // Cart created successfully

    return savedUSer;

  }

}
