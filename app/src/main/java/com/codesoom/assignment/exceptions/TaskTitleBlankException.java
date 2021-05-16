package com.codesoom.assignment.exceptions;

public class TaskTitleBlankException extends RuntimeException {

   public TaskTitleBlankException() {
       super("Task title is blank");
   }

}
