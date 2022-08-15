package com.codesoom.assignment.exceptions;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(Long id){
        super(String.valueOf(id));
    }
}
