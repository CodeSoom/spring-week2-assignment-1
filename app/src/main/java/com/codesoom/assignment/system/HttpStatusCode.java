package com.codesoom.assignment.system;

enum HttpStatusCode {
    OK(200, "OK"), Created(201, "Created"), NoContent(204, "No Content"), BadRequest(400, "Bad Request"), NotFound(404, "Not Found");

    final private int code;
    final private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private HttpStatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
