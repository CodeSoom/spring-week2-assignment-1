package com.codesoom.assignment.exception.custom;

import com.codesoom.assignment.exception.CustomBaseException;


public class TaskNotFound extends CustomBaseException {
    private static final String MESSAGE = "존재하지 않는 할 일 입니다.";

    public TaskNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
