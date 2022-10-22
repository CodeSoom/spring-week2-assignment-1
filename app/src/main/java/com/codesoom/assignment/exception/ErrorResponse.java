package com.codesoom.assignment.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Getter
@Builder
@Slf4j
public class ErrorResponse {
    private final LocalDateTime ERROR_OCCURED_TIME = LocalDateTime.now();
    private final String ERROR_MESSAGE;

    public static ErrorResponse from(final CommonException exception) {
        return ErrorResponse.builder()
                .ERROR_MESSAGE(exception.getMessage())
                .build();
    }

    public static ErrorResponse from(final ConstraintViolationException exception) {
        return ErrorResponse.builder()
                .ERROR_MESSAGE(exception.getMessage())
                .build();
    }

    public static ErrorResponse from(final MethodArgumentNotValidException exception) {
        return ErrorResponse.builder()
                .ERROR_MESSAGE(exception.getBindingResult().getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()).toString()
                )
                .build();
    }

    public static ErrorResponse from(HttpServletRequest request, final Exception exception) {
        log.error("[ERROR-]\t{}\t{}\t{}", request.getMethod(), request.getRequestURI(), 500, exception.getStackTrace());
        log.error("{}", exception.getStackTrace());

        return ErrorResponse.builder()
                .ERROR_MESSAGE(exception.getMessage())
                .build();
    }
}
