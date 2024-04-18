package com.ecommerce.usermanagement.controller.advice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.ecommerce.usermanagement.dto.ErrorDto;
import com.ecommerce.usermanagement.exception.CartCreationException;
import com.ecommerce.usermanagement.exception.UserEmailAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerExceptionHandler {

  @ExceptionHandler(UserEmailAlreadyExistsException.class)
  public ResponseEntity<ErrorDto> handleUserEmailAlreadyExistsException(UserEmailAlreadyExistsException userEmailAlreadyExistsException){
    return
        ResponseEntity.badRequest()
            .body(new ErrorDto(userEmailAlreadyExistsException.getMessage(), BAD_REQUEST));
  }

  @ExceptionHandler(CartCreationException.class)
  public ResponseEntity<ErrorDto> handleCartCreationException(CartCreationException cartCreationException) {
    return
        ResponseEntity.internalServerError()
            .body(new ErrorDto(cartCreationException.getMessage(), INTERNAL_SERVER_ERROR));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDto> handleException(Exception exception){
    return
        ResponseEntity.badRequest()
            .body(new ErrorDto(exception.getMessage(), INTERNAL_SERVER_ERROR));
  }
}
