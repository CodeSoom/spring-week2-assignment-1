package com.codesoom.assignment.exception;

public class DuplicateException extends IllegalStateException{
    public DuplicateException() {
        super();
    }

    public DuplicateException(String s) {
        super(s);
    }

    public DuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateException(Throwable cause) {
        super(cause);
    }
}
