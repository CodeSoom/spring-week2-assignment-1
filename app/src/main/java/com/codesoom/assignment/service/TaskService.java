package com.codesoom.assignment.service;

import org.springframework.stereotype.Service;

import com.codesoom.assignment.dto.TaskDTO;
import com.codesoom.assignment.dto.TasksDTO;
import com.codesoom.assignment.dto.TitleDTO;
import com.codesoom.assignment.model.Tasks;

@Service
public class TaskService {
	private final Tasks tasks = new Tasks();

	public TasksDTO createTask(TitleDTO titleDTO) {
		return tasks.add(titleDTO);
	}

	public TasksDTO getTasks() {
		return new TasksDTO(tasks);
	}

	public TaskDTO updateTasks(long id, TitleDTO titleDTO) {
		return new TaskDTO(tasks.updateTask(id, titleDTO));
	}

	public TasksDTO deleteTasks(long id) {
		return tasks.deleteTask(id);
	}
}
