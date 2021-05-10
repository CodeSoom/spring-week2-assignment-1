package com.codesoom.assignment.exceptions;

import java.util.NoSuchElementException;

public class DoesNotExistException extends NoSuchElementException {

    public DoesNotExistException() {
        super("does not exist");
    }
}
