package com.codesoom.assignment.domain.service;

import com.codesoom.assignment.domain.entity.Task;
import com.codesoom.assignment.domain.persistences.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTask() {
        return this.taskRepository.findAll();
    }

    public Optional<Task> getTask(Long id) {
        return this.taskRepository.findById(id);
    }

    public Task register(Task task) {
        return this.taskRepository.save(task);
    }

    public Optional<Task> modifyTask(Long id, String newTitle) {
        Optional<Task> task = this.taskRepository.findById(id);
        if (!task.isPresent()) {
            return Optional.empty();
        }

        Task modifiedTask = task.get();
        modifiedTask.setTitle(newTitle);
        return Optional.of(this.taskRepository.save(modifiedTask)); // TODO - Null일 가능성이 없지만, Optional 리턴 타입을 맞추기 위해 Optional.of()로 wrapping. 이럴 필요가 있을까요?
    }

    public Optional<Task> deleteTask(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);
        if (!task.isPresent()) {
            return Optional.ofNullable(null);
        }

        this.taskRepository.delete(task.get());
        return task;
    }
}
