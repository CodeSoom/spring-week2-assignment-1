package com.codesoom.assignment.services;

import javax.servlet.http.HttpServletResponse;

public abstract class Response {
    private final HttpServletResponse response;

    protected Response(HttpServletResponse response) {
        this.response = response;
    }

    public Object send(Object content) {
        return content;
    }

    protected abstract int httpStatusCode();
}
