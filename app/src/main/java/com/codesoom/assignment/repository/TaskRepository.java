package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;
    private final static String NO_SEARCH_ID = "ID Error empty";

    // Task all
    public List<Task> getTasks() {
        return tasks;
    }

    // Task one
    public String findOneTask(Long id) {
        Task task = findTask(id);
        if (task == null) {
            return NO_SEARCH_ID;
        }
        return task.toString();
    }

    // Task Insert
    public Task addTask(Task task) {
        task.setId(generatedId());
        tasks.add(task);
        return task;
    }

    // Task Update
    public String taskModify(Long id, Task task) {
        Task source = findTask(id);
        if (source == null) {
            return NO_SEARCH_ID;
        }
        source.setTitle(task.getTitle());
        return source.toString();
    }

    // Task Delete
    public String taskDelete(Long id) {
        Task task = findTask(id);
        if (task == null) {
            return NO_SEARCH_ID;
        }
        tasks.remove(task);
        return "";
    }

    private Long generatedId() {
        newId += 1;
        return newId;
    }

    private Task findTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
