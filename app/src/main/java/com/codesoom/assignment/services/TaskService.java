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
            return new ResponseNotFound(response).send("");
        }

        return task;
    }

    public Object handleCreate(Task task, HttpServletResponse response) {
        if(task.getTitle().isBlank()) {
            return new ResponseBadRequest(response).send("");
        }
        else {
            task.setId(generateId());
            tasks.add(task);
            return new ResponseCreated(response).send(task);
        }
    }

    public Object handleUpdate(Long id, Task source, HttpServletResponse response) {
        if(source.getTitle().isBlank()) {
            return new ResponseBadRequest(response).send("");
        }

        Task task = findTask(id);

        if (task == null) {
            return new ResponseNotFound(response).send("");
        }

        task.setTitle(source.getTitle());
        return task;
    }

    public Object handleDelete(Long id, HttpServletResponse response) {
        if(id == null) {
            return new ResponseNotFound(response).send("");
        }

        Task task = findTask(id);

        if(task == null) {
            return new ResponseNotFound(response).send("");
        }

        tasks.remove(task);
        return new ResponseNoContent(response).send("");
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
