package com.codesoom.assignment.services;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    public List<Task> handleList() {
        return tasks;
    }

    public Object handleItem(Long id, HttpServletResponse response) {
        Task task = findTask(id);

        if (task == null) {
            response.setStatus(404);
            return "";
        }

        return task;
    }

    public Object handleCreate(Task task, HttpServletResponse response) {
        if(task.getTitle().isBlank()) {
            response.setStatus(400);
            return "";
        }
        else {
            task.setId(generateId());
            tasks.add(task);
            response.setStatus(201);
            return task;
        }
    }

    public Object handleUpdate(Long id, Task source, HttpServletResponse response) {
        if(source.getTitle().isBlank()) {
            response.setStatus(400);
            return "";
        }

        Task task = findTask(id);

        if (task == null) {
            response.setStatus(404);
            return "";
        }

        task.setTitle(source.getTitle());
        return task;
    }

    public String handleDelete(Long id, HttpServletResponse response) {
        if(id == null) {
            response.setStatus(404);
            return "";
        }

        Task task = findTask(id);

        if(task == null) {
            response.setStatus(404);
            return "";
        }

        response.setStatus(204);
        tasks.remove(task);
        return "";
    }

    private Task findTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private  Long generateId() {
        newId += 1;
        return newId;
    }
}
