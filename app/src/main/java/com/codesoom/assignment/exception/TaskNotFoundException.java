package com.codesoom.assignment.exception;

public class TaskNotFoundException extends RuntimeException {

  public TaskNotFoundException(Long id) {
    super(String.format("Could not find task with id %d", id));
  }
}
