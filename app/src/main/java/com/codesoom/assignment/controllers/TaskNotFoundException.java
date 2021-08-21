package com.codesoom.assignment.controllers;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(Long id) {
        super("찾을 수 없습니다."+id);
    }

}
