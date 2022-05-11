package com.codesoom.assignment.dto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.codesoom.assignment.model.Task;
import com.codesoom.assignment.model.Tasks;

public class TasksDTO {
	List<TaskDTO> tasksDTO;

	public TasksDTO(Tasks tasks) {
		this.tasksDTO = mapper(tasks.getTasks());
	}

	public TasksDTO(List<Task> tasks) {
		this.tasksDTO = mapper(tasks);
	}

	public List<TaskDTO> mapper(List<Task> tasks) {
		List<TaskDTO> taskDTOs = tasks.stream()
			.map(task -> new TaskDTO(task))
			.collect(Collectors.toList());
		return taskDTOs;
	}

	public List<TaskDTO> getTasksDTO() {
		return Collections.unmodifiableList(tasksDTO);
	}

	@Override
	public String toString() {
		return "TasksDTO{" +
			"tasksDTO=" + tasksDTO +
			'}';
	}
}
