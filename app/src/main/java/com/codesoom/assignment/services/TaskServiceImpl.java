package com.codesoom.assignment.services;


import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        return findTaskById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 아이디"));
    }

    @Override
    public List<Task> getTaskList() {
        return taskRepository.selectList();
    }


    @Override
    public Task updateTask(Task task) {
        getTaskById(task.getId());
        return taskRepository.update(task);
    }


    @Override
    public void deleteTask(Long id) {
        getTaskById(id);
        taskRepository.delete(id);
    }


    public Optional<Task> findTaskById(Long id){
        return Optional.ofNullable(taskRepository.select(id));
    }




}
