package com.codesoom.assignment.exception;

import java.util.NoSuchElementException;

public class DataNotFoundException extends NoSuchElementException {
    public DataNotFoundException() {
    }

    public DataNotFoundException(String s) {
        super(s);
    }
}
