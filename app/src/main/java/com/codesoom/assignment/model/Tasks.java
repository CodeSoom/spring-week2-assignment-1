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

	public TaskDTO updateTask(long id, String title) {
		System.out.println("==============");
		System.out.println(title);
		System.out.println("==============");

		Task task = tasks.stream()
				.filter(t -> t.getId() == id)
				.findAny().orElseThrow(IllegalArgumentException::new);
		task.updateTitle(title);
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
