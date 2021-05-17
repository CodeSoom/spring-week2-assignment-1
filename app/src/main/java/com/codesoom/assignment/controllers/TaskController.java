package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.exceptions.TaskTitleEmptyException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskList taskList = new TaskList();

    @GetMapping
    public List<Task> list(
            @RequestParam("desc") Optional<Boolean> desc
    ) {
        if (desc.orElse(false)) {
            return taskList.descendingAll();
        }
        return taskList.all();
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable("id") final Long id) {
        return taskList.one(id)
                .orElseThrow(TaskNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody final Task task) {
        if (task.getTitle().isBlank()) {
            throw new TaskTitleEmptyException();
        }
        return taskList.save(task);
    }

    @PutMapping("/{id}")
    public Task update(
            @PathVariable("id") final Long id,
            @RequestBody final Task newTaskForUpdate
    ) {
        if (newTaskForUpdate.getTitle().isBlank()) {
            logger.debug("task={}", newTaskForUpdate.getTitle());
            throw new TaskTitleEmptyException();
        }

        taskList.one(id)
                .orElseThrow(TaskNotFoundException::new);

        taskList.update(id, newTaskForUpdate);
        return newTaskForUpdate;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") final Long id) {
        taskList.remove(id)
                .orElseThrow(TaskNotFoundException::new);
    }
}
