package com.codesoom.assignment.dto;

import java.util.ArrayList;
import java.util.List;

import com.codesoom.assignment.model.Task;
import com.codesoom.assignment.model.Tasks;

public class TasksDTO {
	List<Task> tasksDTO;

	public TasksDTO(Tasks tasks) {
		this.tasksDTO = tasks.getTasks();
	}

	public List<Task> getTasksDTO() {
		return tasksDTO;
	}
}
