package com.ecommerce.products.controller.advice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.ecommerce.products.dto.ErrorDto;
import com.ecommerce.products.exception.ProductNameExistsException;
import com.ecommerce.products.exception.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductControllerExceptionHandler {

  @ExceptionHandler(ProductNameExistsException.class)
  public ResponseEntity<ErrorDto> handleProductNameExistsException(ProductNameExistsException productNameExistsException){
    return
        ResponseEntity.badRequest()
            .body(new ErrorDto(productNameExistsException.getMessage(), BAD_REQUEST));
  }

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ErrorDto> handleProductNotFoundExcpetion(ProductNotFoundException productNotFoundException){
    return
        ResponseEntity.badRequest()
            .body(new ErrorDto(productNotFoundException.getMessage(), NOT_FOUND));
  }
}
