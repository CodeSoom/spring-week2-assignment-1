package com.codesoom.assignment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codesoom.assignment.model.Task;

@Service
public interface TaskService {


    List<Task> getTaskListService();


    Task createTaskService(Task task);


    Task getTaskService(Long id);


    Task modifyTaskService(Long id, String title);


    void deleteTaskService(Task task);
}
