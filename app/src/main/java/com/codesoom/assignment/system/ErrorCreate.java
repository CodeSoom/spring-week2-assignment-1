package com.codesoom.assignment.system;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErrorCreate extends RuntimeException {
    public ErrorCreate(String errormsg) {
        super(errormsg);
    }

}
