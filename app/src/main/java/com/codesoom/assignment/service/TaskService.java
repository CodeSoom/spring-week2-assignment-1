package com.codesoom.assignment.service;

import java.util.List;
import java.util.Optional;

import com.codesoom.assignment.model.Task;

public interface TaskService {


    List<Task> getTaskList();


    Task createTask(Task task);


    Optional<Task> getTask(Long id);


    Task modifyTask(Task task, String title);


    void deleteTask(Task task);
}
