package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exception.ParameterEmptyException;
import com.codesoom.assignment.exception.TaskIdNotFoundException;
import com.codesoom.assignment.model.Task;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private List<Task> tasks = new ArrayList<>();

    @GetMapping
    public List<Task> taskList() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task taskDetail(@PathVariable Long id) {
        return findTask(id);
    }

    @PostMapping
    public ResponseEntity<Task> taskCreate(@RequestBody Task task) throws Throwable {
        task.setId(generatedId());
        tasks.add(task);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(task.getId())
                .toUri();
        return new ResponseEntity<Task>(task,  getHttpLocationHeaders(uri), HttpStatus.CREATED);
    }

    private HttpHeaders getHttpLocationHeaders(URI uri) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return httpHeaders;
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public Task taskModify(@PathVariable Long id, @RequestBody Task task) {
        validateParameter(id, task);
        Task foundTask = findTask(id);
        int foundTaskIndex = tasks.indexOf(foundTask);
        task.setId(foundTask.getId());
        tasks.set(foundTaskIndex, task);
        return task;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void taskDelete(@PathVariable Long id) {
        Task findTask = findTask(id);
        tasks.remove(findTask);
    }

    private Task findTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(TaskIdNotFoundException::new);
    }

    private Long generatedId() throws Throwable {
        Long maxId = 1L;
        if (tasks.size() > 0) {
            Comparator<Task> comparator = Comparator.comparingLong(Task::getId);
            maxId = tasks.stream()
                    .max(comparator)
                    .orElseThrow(TaskIdNotFoundException::new)
                    .getId() + 1;
        }
        return maxId;
    }

    private void validateParameter(Long id, Task task) {
        final String newTitle = task.getTitle();
        if (id == null || newTitle.isBlank()) {
            throw new ParameterEmptyException(id, newTitle);
        }
    }
}
