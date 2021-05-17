package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TODO
 * 1. Read Collection - GET /tasks
  */


@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    @GetMapping
    public List<Task> list() {
        return tasks;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> details(@PathVariable Long id){
        return ResponseEntity.of(findTask(id));
    }

    private Optional<Task> findTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    @PostMapping
    public Task created(@RequestBody Task task) {
        task.setId(generatedId());
        task.setTitle(task.getTitle());
        tasks.add(task);

        return task;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task old_task) {
        Optional<Task> entity = findTask(id);

        if(!entity.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Task task = entity.get();
        task.setTitle(old_task.getTitle());

        return ResponseEntity.of(entity);
    }

    @DeleteMapping
    public List<Task> delete(@PathVariable String id, @RequestBody Task old_task) {
        tasks.remove(getIndex(id));
        return tasks;
    }

    private synchronized Long generatedId() {
        newId += 1;
        return newId;
    }

    // DRY 전략 적용
    // method 방식으로 위임임
   private int getIndex(String id){
        return Integer.parseInt(id) - 1;
    }
}