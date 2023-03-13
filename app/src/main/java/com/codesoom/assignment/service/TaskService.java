package com.codesoom.assignment.service;

import com.codesoom.assignment.config.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    TaskList taskList;

    public TaskService() {
        this.taskList = new TaskList();
    }

    public List<Task> getItems() {
        return taskList.getItems();
    }

    public Task create(Task task) {
        taskList.add(task);
        return task;
    }

    public Task update(Integer id, String title) throws TaskNotFoundException {
        taskList.update(id, title);
        return taskList.get(id);
    }

    public void delete(Integer id) throws TaskNotFoundException {
        taskList.delete(id);
    }
}
