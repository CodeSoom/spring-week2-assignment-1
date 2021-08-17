package com.codesoom.assignment.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.NotFound;

@RestControllerAdvice
class GlobalControllerAdvice {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = RuntimeException.class)
  public ResponseEntity notFoundException(Exception e) {

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }
}
