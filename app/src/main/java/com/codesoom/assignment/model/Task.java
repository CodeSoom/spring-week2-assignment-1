package com.codesoom.assignment.model;

public class Task {
	private final Long id;
	private String title;

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

	public void updateTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Task{" +
			"id=" + id +
			", title='" + title + '\'' +
			'}';
	}
}
