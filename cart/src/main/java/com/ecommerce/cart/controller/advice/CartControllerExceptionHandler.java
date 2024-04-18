package com.ecommerce.cart.controller.advice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.ecommerce.cart.dto.ErrorDto;
import com.ecommerce.cart.exception.CartNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CartControllerExceptionHandler {

  @ExceptionHandler(CartNotFoundException.class)
  public ResponseEntity<ErrorDto> handleCartNotFoundExcpetion(CartNotFoundException cartNotFoundException){
    return
        ResponseEntity.badRequest()
            .body(new ErrorDto(cartNotFoundException.getMessage(), NOT_FOUND));
  }


}
