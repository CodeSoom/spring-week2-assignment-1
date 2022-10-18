package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.BaseTask;
import com.codesoom.assignment.models.TaskDto;
import com.codesoom.assignment.utils.TaskIdGenerator;
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
    private final Map<Long, BaseTask> taskMap = new ConcurrentHashMap<>();

    @Autowired
    public TaskRepositoryImpl(TaskIdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Collection<BaseTask> findAllTasks() {
        return Collections.unmodifiableCollection(taskMap.values());
    }

    public Optional<BaseTask> findById(Long id) {
        final BaseTask task = taskMap.get(id);
        return Optional.ofNullable(task);
    }

    public BaseTask addTask(TaskDto dto) {
        dto.setId(idGenerator.allocateId());
        final BaseTask task = dto.toTask();
        taskMap.put(task.getId(), task);
        return task;
    }

    public Optional<BaseTask> changeTitle(BaseTask task) {
        if (!taskMap.containsKey(task.getId())) {
            return Optional.empty();
        }

        taskMap.put(task.getId(), task);
        return Optional.of(task);
    }

    public Optional<BaseTask> deleteById(Long id) {
        final BaseTask removedTask = taskMap.remove(id);
        return Optional.ofNullable(removedTask);
    }
}
