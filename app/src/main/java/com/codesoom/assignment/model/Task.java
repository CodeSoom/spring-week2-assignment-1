package com.codesoom.assignment.model;

public class Task {
	private final Long id;
	private final String title;

	public Task(Long id, String title) {
		this.id = id;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public Long getId() {
		return id;
	}

	public Task updateTitle(String title) {
		return new Task(id, title);
	}

	@Override
	public String toString() {
		return "Task{" +
			"id=" + id +
			", title='" + title + '\'' +
			'}';
	}
}
