package com.codesoom.assignment.services;

import com.codesoom.assignment.utils.IdGenerator;
import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final IdGenerator idGenerator;

    public TaskService(TaskRepository taskRepository, IdGenerator idGenerator) {
        this.taskRepository = taskRepository;
        this.idGenerator = idGenerator;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task addTask(Task task) {
        Long newId = idGenerator.generateId();
        task.setId(newId);

        return taskRepository.save(task);
    }

    public Task getTask(Long id) {
        Optional<Task> task = taskRepository.findOne(id);

        return task.orElseThrow(()-> new TaskNotFoundException("Not found task with id = " + id));
    }

    public Task updateTask(Long id, Task newTask) {
        Task task = getTask(id);
        task.update(newTask);

        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        if (!isExist(id)) {
            throw new TaskNotFoundException("Not found task with id = " + id);
        }

        taskRepository.delete(id);
    }

    private boolean isExist(Long id) {
        return taskRepository.findOne(id).isPresent();
    }

}
