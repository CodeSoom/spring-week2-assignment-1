package com.codesoom.assignment.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.codesoom.assignment.dto.TaskDTO;
import com.codesoom.assignment.dto.TasksDTO;
import com.codesoom.assignment.dto.TitleDTO;
import com.codesoom.assignment.exception.TaskNotFoundException;

public class Tasks {
	List<Task> tasks = new ArrayList<>();
	private long id = 0;

	public TasksDTO add(TitleDTO titleDTO) {
		tasks.add(new Task(id, titleDTO.getTitle()));
		id += 1;
		return new TasksDTO(tasks);
	}

	public List<Task> getTasks() { return Collections.unmodifiableList(new ArrayList<>(tasks)); }

	public Task updateTask(long id, TitleDTO titleDTO) {
		Task task = tasks.stream().filter(t -> t.getId() == id).findAny().orElseThrow(()->new TaskNotFoundException(id));
		Task updatedTask = task.updateTitle(titleDTO.getTitle());
		tasks.remove(task);
		tasks.add(updatedTask);
		return updatedTask;
	}

	public TasksDTO deleteTask(long id) {
		Task task = tasks.stream().filter(t -> t.getId() == id).findAny().orElseThrow(()->new TaskNotFoundException(id));
		tasks.remove(task);
		return new TasksDTO(tasks);
	}

	@Override
	public String toString() {
		return "Tasks{" + "id=" + id + ", tasks=" + tasks + '}';
	}

}
