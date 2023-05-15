package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.model.Task;
import com.codesoom.assignment.task.TaskService;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

	private TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping("")
	public List<Task> getTasks() {
		return taskService.getAllTasks();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Task> getTask(@PathVariable int id) {
		try {
			return ResponseEntity.ok(taskService.findTask(id));
		} catch (TaskNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("")
	public ResponseEntity<Task> createTask(@RequestBody Task task) throws TaskNotFoundException {
		Task created = taskService.create(task);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@PutMapping("{id}")
	public ResponseEntity<Task> updateTaskTitle(@PathVariable int id, @RequestBody Task task) {
		try {
			return ResponseEntity.ok(taskService.putTask(id, task));
		} catch (TaskNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Task> deleteTask(@PathVariable int id) {
		try {
			taskService.deleteTask(id);
			return ResponseEntity.noContent().build();
		} catch (TaskNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
