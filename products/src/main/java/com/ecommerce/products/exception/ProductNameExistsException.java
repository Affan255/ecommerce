package com.ecommerce.products.exception;

public class ProductNameExistsException extends Exception {
  public ProductNameExistsException() {
  }

  public ProductNameExistsException(String message) {
    super(message);
  }
}
