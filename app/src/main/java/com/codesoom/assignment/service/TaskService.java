package com.codesoom.assignment.service;

import com.codesoom.assignment.dto.TaskDTO;
import com.codesoom.assignment.dto.TasksDTO;
import com.codesoom.assignment.model.Task;
import com.codesoom.assignment.model.Tasks;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private Tasks tasks = new Tasks();

    public TasksDTO createTask(TaskDTO taskDTO) {
        tasks.add(taskDTO);
        return new TasksDTO(tasks);
    }

    public TasksDTO getTasks() {
        return new TasksDTO(tasks);
    }

    public TaskDTO updateTasks(long id, TaskDTO taskDTO) {
        return tasks.updateTask(id, taskDTO);
    }
}
