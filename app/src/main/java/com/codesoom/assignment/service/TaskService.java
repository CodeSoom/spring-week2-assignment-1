package com.codesoom.assignment.service;

import java.util.List;

import com.codesoom.assignment.model.Task;

public interface TaskService {

    List<Task> getTaskListService();


    Task createTaskService(Task task);


    Task getTaskService(Long id);


    Task modifyTaskService(Long id, String title);


    void deleteTaskService(Task task);
}
