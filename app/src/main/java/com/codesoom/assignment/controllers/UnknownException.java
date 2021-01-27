package com.codesoom.assignment.controllers;

public class UnknownException extends Exception {
    public String getMessage() {
        return "UnknownException";
    }
}
