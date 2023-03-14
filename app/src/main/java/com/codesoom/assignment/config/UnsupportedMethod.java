package com.codesoom.assignment.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnsupportedMethod extends Throwable {
    public UnsupportedMethod(String msg) {
        super(msg);
    }
}
