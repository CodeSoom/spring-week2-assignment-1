package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class MapTaskRepository implements TaskRepository {

    private Map<Long, Task> tasks = new HashMap<>();
    private Long id = 0L;

    @Override
    public Task join(Task task) {

        id++;
        task.setId(id);
        tasks.put(task.getId(), task);

        return task;

    }

    @Override
    public Optional<Task> findTask(Long taskId) {

        return Optional.ofNullable(tasks.get(taskId));

    }

    @Override
    public Collection<Task> findAll(){

        return tasks.values();

    }

    @Override
    public Optional<Task> updateTask(Long taskId, Task task) {

        Optional<Task> taskObject = findTask(taskId);
        if(taskObject.isPresent()) {
            taskObject.get().setTitle(task.getTitle());
        }
        return taskObject;

    }

    @Override
    public boolean deleteTask(Long taskId) {

        if(findTask(taskId).isPresent()) {
            tasks.remove(taskId);
            return true;
        }

        return false;

    }


}
