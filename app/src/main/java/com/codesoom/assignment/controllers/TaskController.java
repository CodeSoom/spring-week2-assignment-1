package com.codesoom.assignment.controllers;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.codesoom.assignment.dto.TaskDTO;
import com.codesoom.assignment.dto.TasksDTO;
import com.codesoom.assignment.dto.TitleDTO;
import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.service.TaskService;


@RestController
public class TaskController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final TaskService taskService;

	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@PostMapping("/tasks")
	public ResponseEntity<TasksDTO> createTask(@RequestBody TitleDTO titleDTO) {
		return ResponseEntity.ok().body(taskService.createTask(titleDTO));
	}

	@GetMapping("/tasks")
	public ResponseEntity<TasksDTO> getTask() {
		return ResponseEntity.ok().body(taskService.getTasks());
	}

	@PatchMapping("/tasks/{id}")
	public ResponseEntity<TaskDTO> updateTask(@RequestBody TitleDTO titleDTO, @PathVariable long id) {
		return ResponseEntity.ok().body(taskService.updateTasks(id, titleDTO));
	}

	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<TasksDTO> deleteTask(@PathVariable long id) {
		return ResponseEntity.ok().body(taskService.deleteTasks(id));
	}

	@ExceptionHandler(value = TaskNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void TaskNoFoundException(TaskNotFoundException taskNotFoundException) {
		logger.info(String.format("[%s] %s", LocalDateTime.now(), taskNotFoundException.getMessage()));
	}
}
