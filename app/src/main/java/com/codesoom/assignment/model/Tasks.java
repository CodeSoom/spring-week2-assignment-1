package com.codesoom.assignment.model;

import java.util.ArrayList;
import java.util.List;

import com.codesoom.assignment.dto.TaskDTO;

public class Tasks {
	private long id = 0;
	List<Task> tasks = new ArrayList<>();

	public void add(TaskDTO taskDTO) {
		Task task = new Task(id,taskDTO.getTitle());
		id += 1;
		tasks.add(task);
	}

	public List<Task> getTasks() {
		return tasks;
	}
}
