package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.model.Task;
import com.codesoom.assignment.task.TaskService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
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
	public Task getTask(@PathVariable int id) throws TaskNotFoundException {
		return taskService.findTask(id);
	}

	@PostMapping("")
	public Task createTask(@RequestBody Task task) throws TaskNotFoundException {
		return taskService.create(task);
	}

	@PutMapping("{id}")
	public Task updateTaskTitle(@PathVariable int id, @RequestBody Task task) throws TaskNotFoundException {
		return taskService.putTask(id, task);
	}

	@DeleteMapping("/{id}")
	public void deleteTask(@PathVariable int id) throws TaskNotFoundException {
		taskService.deleteTask(id);
	}
}
