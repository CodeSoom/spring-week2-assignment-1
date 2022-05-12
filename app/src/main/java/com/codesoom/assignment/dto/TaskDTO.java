package com.codesoom.assignment.dto;

import com.codesoom.assignment.model.Task;

public class TaskDTO {

	private final Long id;
	private final String title;

	public TaskDTO(Long id, String title) {
		this.id = id;
		this.title = title;
	}

	public TaskDTO(Task task) {
		this.id = task.getId();
		this.title = task.getTitle();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return "TaskDTO{" + "id=" + id + ", title='" + title + '\'' + '}';
	}
}
