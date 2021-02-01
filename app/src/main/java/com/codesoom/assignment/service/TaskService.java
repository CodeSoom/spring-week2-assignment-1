package com.codesoom.assignment.service;

import java.util.List;

import com.codesoom.assignment.model.Task;

public interface TaskService {


    List<Task> getTaskList();


    Task createTask(Task task);


    Task getTask(Long id);


    Task modifyTask(Task source, Long id);


    void deleteTask(Long id);
}
