package com.codesoom.assignment.exceptions;

public class TaskNotFoundException extends RuntimeException {

   public TaskNotFoundException(Long id) {
       super("Task not found: " + id);
   }

}
