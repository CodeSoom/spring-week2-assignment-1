package com.codesoom.assignment.system;

import java.util.NoSuchElementException;

// Exception Testing
public class DataNotFoundException extends NoSuchElementException {

    public DataNotFoundException(String data) {
        super(data + "not found");
    }
}

