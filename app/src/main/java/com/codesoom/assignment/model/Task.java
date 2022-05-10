package com.codesoom.assignment.model;

public class Task {
	private Long id;
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
}
