package com.codesoom.assignment.service;

import org.springframework.stereotype.Service;

import com.codesoom.assignment.dto.TaskDTO;
import com.codesoom.assignment.dto.TasksDTO;
import com.codesoom.assignment.model.Tasks;

@Service
public class TaskService {
	private final Tasks tasks = new Tasks();

	public TasksDTO createTask(TaskDTO taskDTO) {
		return tasks.add(taskDTO);
	}

	public TasksDTO getTasks() {
		return new TasksDTO(tasks);
	}

	public TaskDTO updateTasks(long id, TaskDTO taskDTO) {
		return tasks.updateTask(id, taskDTO);
	}

	public TasksDTO deleteTasks(long id) {
		return tasks.deleteTask(id);
	}
}
