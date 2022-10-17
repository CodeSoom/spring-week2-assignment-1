package com.codesoom.assignment.repositories;

import com.codesoom.assignment.utils.TaskIdGenerator;
import com.codesoom.assignment.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final TaskIdGenerator idGenerator;
    private final Map<Long, Task> taskMap = new ConcurrentHashMap<>();

    @Autowired
    public TaskRepositoryImpl(TaskIdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Collection<Task> findAllTasks() {
        return Collections.unmodifiableCollection(taskMap.values());
    }

    public Optional<Task> findById(Long id) {
        final Task task = taskMap.get(id);
        return Optional.ofNullable(task);
    }

    public Task addTask(Task task) {
        task.setId(idGenerator.allocateId());
        taskMap.put(task.getId(), task);
        return task;
    }

    public Optional<Task> changeTitle(Long id, String newTitle) {
        if (!taskMap.containsKey(id)) {
            return Optional.empty();
        }

        final Task changedTask = new Task(id, newTitle);
        taskMap.put(changedTask.getId(), changedTask);
        return Optional.of(changedTask);
    }

    public Optional<Task> deleteById(Long id) {
        final Task removedTask = taskMap.remove(id);
        return Optional.ofNullable(removedTask);
    }
}
