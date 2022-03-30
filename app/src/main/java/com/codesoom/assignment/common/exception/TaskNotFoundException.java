package com.codesoom.assignment.common.exception;


/**
 * @description : 찾는 Task가 없을 경우 TaskNotFoundException 예외를 발생시킨다.
 */
public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException() {
        super();
    }

}
