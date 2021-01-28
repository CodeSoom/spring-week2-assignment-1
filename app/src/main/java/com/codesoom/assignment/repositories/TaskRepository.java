package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TaskRepository {

    private Map<Long, Task> taskMap = Collections.synchronizedMap(new LinkedHashMap<>());

    public List<Task> findAll() {
        return new ArrayList<>(taskMap.values());
    }

    public Task save(Task task) {
        taskMap.put(task.getId(), task);

        return task;
    }

    public Optional<Task> findOne(Long id) {
        return Optional.ofNullable(taskMap.get(id));
    }

    public void delete(Long id) {
        taskMap.remove(id);
    }

    public void removeAll() {
        taskMap.clear();
    }

}
