package com.codesoom.assignment.models;

public enum HttpStatus {
    HTTP_OK(200), HTTP_CREATE(201), HTTP_NO_CONTENT(204), HTTP_NOT_FOUND(404);

    private int status;

    HttpStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
