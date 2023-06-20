package com.codesoom.assignment.repositories;


import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TaskMapRepository implements TaskRepository{

    private static Map<Long,Task> taskMap = new HashMap<>();
    private static Long newId = 0L;

    @Override
    public Task insert(Task task) {
        task.setId(generateId());
        taskMap.put(task.getId(), task);
        return task;
    }

    @Override
    public Task select(Long id) {
        return taskMap.get(id);
    }

    @Override
    public List<Task> selectAll() {
        return new ArrayList<>(taskMap.values());
    }

    @Override
    public Task update(Task source) {
        Task task = taskMap.get(source.getId());
        task.setTitle(source.getTitle());
        return task;
    }

    @Override
    public void delete(Long id) {
        taskMap.remove(id);
    }

    public Long generateId() {
        newId += 1;
        return newId;
    }

}
