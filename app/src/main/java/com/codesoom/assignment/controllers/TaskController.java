package com.codesoom.assignment.controllers;

import com.codesoom.assignment.dtos.TaskDTO;
import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@CrossOrigin
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private static final String ID_PATH = "/{id}";
    private static final String ID = "id";
    private static final int INDEX_START = 0;

    private final List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    private Long generateId() {
        return ++newId;
    }

    @GetMapping
    public List<Task> getTasks() {
        return tasks;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task postTask(@RequestBody final TaskDTO taskDTO) {
        Task task = new Task(generateId(), taskDTO.getTitle());
        tasks.add(task);
        return task;
    }

    private Task getTaskItem(final Long id) {
        Optional<Task> taskOptional = tasks.stream()
                .filter(task-> Objects.equals(task.getId(), id))
                .findFirst();
        if (taskOptional.isEmpty()) {
            throw new TaskNotFoundException();
        }
        return taskOptional.get();
    }

    @GetMapping(ID_PATH)
    public Task getTask(@PathVariable(ID) final Long id) {
        return getTaskItem(id);
    }

    @RequestMapping(value = ID_PATH, method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Task updateTask(@PathVariable(ID) final Long id, @RequestBody final TaskDTO taskDTO) {
        Task task = getTaskItem(id);
        task.setTitle(taskDTO.getTitle());
        return task;
    }

    @DeleteMapping(ID_PATH)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable(ID) final Long id) {
        OptionalInt integerOptional =  IntStream.range(INDEX_START, tasks.size())
                .filter(index -> Objects.equals(tasks.get(index).getId(), id))
                .findFirst();
        if (integerOptional.isEmpty()) {
            throw new TaskNotFoundException();
        }
        tasks.remove(integerOptional.getAsInt());
    }
}
