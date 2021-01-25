package com.codesoom.assignment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Id")
public class NotFoundException extends RuntimeException {
    public String notFound(){
        return "";
    }
}
