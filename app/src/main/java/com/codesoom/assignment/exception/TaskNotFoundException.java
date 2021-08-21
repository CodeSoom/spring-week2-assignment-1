package com.codesoom.assignment.exception;

/**
 * 할 일이 발견되지 않은 경우 발생하는 예외입니다.
 */
public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(String id) {
        super(String.format("%s 를 할 일 리스트에서 찾을 수 없습니다.", id));
    }
}
