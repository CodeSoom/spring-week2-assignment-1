package com.codesoom.assignment.exceptions;

import java.util.NoSuchElementException;

public class TaskNotFoundException extends ItemNotFoundException {

    public TaskNotFoundException() {
        super("Task");
    }
}
