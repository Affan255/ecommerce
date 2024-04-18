package com.ecommerce.usermanagement.controller;

import com.ecommerce.usermanagement.dto.UserDto;
import com.ecommerce.usermanagement.exception.CartCreationException;
import com.ecommerce.usermanagement.exception.UserEmailAlreadyExistsException;
import com.ecommerce.usermanagement.models.User;
import com.ecommerce.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping(value = "/create")
  public ResponseEntity<User> addUser(@RequestBody UserDto userDto) throws UserEmailAlreadyExistsException, CartCreationException {
    User user = userService.addUser(userDto);
    return ResponseEntity.ok()
        .body(user);
  }
}
