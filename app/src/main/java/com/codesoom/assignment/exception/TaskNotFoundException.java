package com.codesoom.assignment.exception;

public class TaskNotFoundException extends RuntimeException {
	public static final String NOT_EXIST = "Task with id %d does not exist";

	public TaskNotFoundException() {
	}
	public TaskNotFoundException(long id) {
		super(String.format(NOT_EXIST, id));
	}
}
