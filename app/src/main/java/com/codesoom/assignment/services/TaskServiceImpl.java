package com.codesoom.assignment.services;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskBuilder;
import com.codesoom.assignment.repositories.TaskRepository;
import com.codesoom.assignment.utils.IdGenerator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final IdGenerator idGenerator;

    public TaskServiceImpl(TaskRepository taskRepository, IdGenerator idGenerator) {
        this.taskRepository = taskRepository;
        this.idGenerator = idGenerator;
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task addTask(Task task) {
        Long newId = idGenerator.generateId();
        TaskBuilder taskBuilder = new TaskBuilder().id(newId).title(task.getTitle());
        return taskRepository.save(taskBuilder.build());
    }

    @Override
    public Task getTask(Long id) {
        Optional<Task> task = taskRepository.findOne(id);
        return task.orElseThrow();
    }

    @Override
    public Task updateTask(Long id, Task newTask) {
        Task task = taskRepository.findOne(id).orElseThrow();
        TaskBuilder taskBuilder = new TaskBuilder().title(newTask.getTitle()).id(task.getId());
        return taskRepository.save(taskBuilder.build());
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.findOne(id).orElseThrow();
        taskRepository.delete(id);
    }
}
