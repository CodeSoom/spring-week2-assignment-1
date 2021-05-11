package com.codesoom.assignment.exceptions;

import java.util.NoSuchElementException;

public class ItemNotFoundException extends NoSuchElementException {

    public ItemNotFoundException(String item) {
        super(item + " not found");
    }
}
