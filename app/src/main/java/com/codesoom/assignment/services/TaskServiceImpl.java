package com.codesoom.assignment.services;


import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {


    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }


    @Override
    public Task createTask(Task task) {
        return taskRepository.insert(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.select(id);
    }

    @Override
    public List<Task> getTaskList() {
        return taskRepository.selectList();
    }


    @Override
    public Task updateTask(Task task) {
       return taskRepository.update(task);
    }


    @Override
    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }

}
