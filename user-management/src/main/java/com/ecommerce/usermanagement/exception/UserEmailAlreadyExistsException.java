package com.ecommerce.usermanagement.exception;

public class UserEmailAlreadyExistsException extends Exception{
  private String email;
  public UserEmailAlreadyExistsException(String email) {
    super("User with email " + email + " already exists!");
  }

}
