package com.codesoom.assignment.service;

import org.springframework.stereotype.Service;

import com.codesoom.assignment.dto.TaskDTO;
import com.codesoom.assignment.dto.TasksDTO;
import com.codesoom.assignment.model.Tasks;

@Service
public class TaskService {
	private Tasks tasks = new Tasks();

	public TasksDTO createTask(TaskDTO taskDTO) {
		tasks.add(taskDTO);
		TasksDTO tasksDTO = new TasksDTO(tasks);
		return tasksDTO;
	}

	public TasksDTO getTasks() {
		TasksDTO tasksDTO = new TasksDTO(tasks);
		return tasksDTO;
	}
}
