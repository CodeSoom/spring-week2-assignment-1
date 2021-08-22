package com.codesoom.assignment.service;

import com.codesoom.assignment.repository.TaskRepository;
import com.codesoom.assignment.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Task Entity와 관련된 서비스를 담당.
 */
@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Task create(Task task){
        return taskRepository.create(task);
    }

    public Task update(Long id, Task source){
        return taskRepository.update(id, source);
    }

    public boolean delete(Long id){
        return taskRepository.delete(id);
    }
}
