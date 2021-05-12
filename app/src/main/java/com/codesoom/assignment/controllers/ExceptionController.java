package com.codesoom.assignment.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class ExceptionController {
    private final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler({HttpClientErrorException.BadRequest.class})
    public ResponseEntity<String> taskTitleMustNotBeBlank(final RuntimeException ex) {
        logger.warn("error", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpClientErrorException.NotFound.class})
    public ResponseEntity<String> taskNotFound(final RuntimeException ex) {
        logger.warn("error", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({HttpServerErrorException.InternalServerError.class})
    public ResponseEntity<String> internalServerError(final RuntimeException ex) {
        logger.warn("error", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
