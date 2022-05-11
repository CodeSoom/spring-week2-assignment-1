package com.codesoom.assignment.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.codesoom.assignment.dto.TaskDTO;
import com.codesoom.assignment.dto.TasksDTO;

public class Tasks {
	List<Task> tasks = new ArrayList<>();
	private long id = 0;

	public TasksDTO add(TaskDTO taskDTO) {
		tasks.add(new Task(id, taskDTO.getTitle()));
		id += 1;
		return new TasksDTO(tasks);
	}

	public List<Task> getTasks() {
		return Collections.unmodifiableList(tasks);
	}

	public TaskDTO updateTask(long id, TaskDTO taskDTO) {
		Task task = tasks.stream()
			.filter(t -> t.getId() == id)
			.findAny().orElseThrow(IllegalArgumentException::new);
		task.updateTitle(taskDTO.getTitle());
		return new TaskDTO(task);
	}

	public TasksDTO deleteTask(long id) {
		Task task = tasks.stream()
			.filter(t -> t.getId() == id)
			.findAny()
			.orElseThrow(IllegalArgumentException::new);
		tasks.remove(task);
		return new TasksDTO(tasks);
	}

	@Override
	public String toString() {
		return "Tasks{" +
			"id=" + id +
			", tasks=" + tasks +
			'}';
	}

}
