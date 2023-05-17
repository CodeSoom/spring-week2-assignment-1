package com.codesoom.assignment.controllers;
import com.codesoom.assignment.dto.ErrorResponse;
import com.codesoom.assignment.exception.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TaskExceptionHandler {

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(TaskNotFoundException.class)
	public ErrorResponse taskNullException(TaskNotFoundException e) {
		return new ErrorResponse(e.getMessage());
	}
}
