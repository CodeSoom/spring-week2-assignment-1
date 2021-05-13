package com.codesoom.assignment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// BAD_REQUEST에서 NOT_FOUND로 수정 -> 차이점 파악할 것
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ErrorCreate extends RuntimeException {
}