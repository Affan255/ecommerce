package com.ecommerce.cart.exception;

public class CartProductNotFoundException extends Exception{
  public CartProductNotFoundException() {
  }

  public CartProductNotFoundException(String message) {
    super(message);
  }
}
