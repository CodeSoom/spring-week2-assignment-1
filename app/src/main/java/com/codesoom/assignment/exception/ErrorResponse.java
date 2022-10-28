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
    private final LocalDateTime occuredTime;
    private final String message;

    public static ErrorResponse from(final CommonException exception) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .occuredTime(exception.getOccuredTime())
                .build();
    }

    public static ErrorResponse from(final ConstraintViolationException exception) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
    }

    public static ErrorResponse from(final MethodArgumentNotValidException exception) {
        return ErrorResponse.builder()
                .message(exception.getBindingResult().getFieldErrors()
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
                .message(exception.getMessage())
                .build();
    }
}
