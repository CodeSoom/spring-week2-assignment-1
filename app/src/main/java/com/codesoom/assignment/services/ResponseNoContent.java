package com.codesoom.assignment.services;

import javax.servlet.http.HttpServletResponse;

public class ResponseNoContent extends Response {
    public ResponseNoContent(HttpServletResponse response) {
        super(response);
        response.setStatus(httpStatusCode());
    }

    @Override
    public Object send(Object content) {
        return super.send(content);
    }

    @Override
    protected int httpStatusCode() {
        return 204;
    }
}
