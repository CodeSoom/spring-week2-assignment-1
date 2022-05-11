package com.codesoom.assignment.model;

import java.util.ArrayList;
import java.util.List;

import com.codesoom.assignment.dto.TaskDTO;

public class Tasks {
	List<Task> tasks = new ArrayList<>();
	private long id = 0;

	public void add(TaskDTO taskDTO) {
		Task task = new Task(id, taskDTO.getTitle());
		id += 1;
		tasks.add(task);
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public TaskDTO updateTask(long id, TaskDTO taskDTO) {
		Task task = tasks.stream()
			.filter(t -> t.getId() == id)
			.findAny().orElseThrow(IllegalArgumentException::new);
		task.updateTitle(taskDTO.getTitle());
		return new TaskDTO(task);
	}

	@Override
	public String toString() {
		return "Tasks{" +
			"id=" + id +
			", tasks=" + tasks +
			'}';
	}
}
