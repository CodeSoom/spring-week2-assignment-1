package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskIdGenerator;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TaskRepository {

    private final TaskIdGenerator taskIdGenerator;
    private Map<Long, Task> taskMap = new ConcurrentHashMap<>();

    public TaskRepository(TaskIdGenerator taskIdGenerator) {
        this.taskIdGenerator = taskIdGenerator;
    }

    public Collection<Task> getTaskList() {
        return taskMap.values();
    }

    public Optional<Task> getTaskById(Long id) {
        return Optional.ofNullable(taskMap.get(id));
    }

    public Task postTask(Task task) {
        Task newTask = new Task(taskIdGenerator.getNextSequence());
        newTask.setTitle(task.getTitle());
        taskMap.put(newTask.getId(), newTask);
        return newTask;
    }

    public Optional<Task> putTask(Long id, Task task) {
        Optional<Task> oldTask = Optional.ofNullable(taskMap.get(id));
        oldTask.ifPresent(thisTask -> {
            thisTask.setTitle(task.getTitle());
            taskMap.put(thisTask.getId(), thisTask);
        });
        return oldTask;
    }

    public Optional<Task> patchTask(Long id, Task task) {
        Optional<Task> oldTask = Optional.ofNullable(taskMap.get(id));
        oldTask.ifPresent(thisTask -> {
            thisTask.setTitle(task.getTitle());
            taskMap.put(thisTask.getId(), thisTask);
        });
        return oldTask;
    }

    public Optional<Task> deleteTask(Long id) {
        Task task = taskMap.get(id);
        taskMap.remove(id);
        return Optional.ofNullable(task);
    }
}
