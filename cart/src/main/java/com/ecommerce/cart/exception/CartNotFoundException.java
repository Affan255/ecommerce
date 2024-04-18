package com.ecommerce.cart.exception;

public class CartNotFoundException extends Exception{
  public CartNotFoundException() {
  }

  public CartNotFoundException(String message) {
    super(message);
  }
}
