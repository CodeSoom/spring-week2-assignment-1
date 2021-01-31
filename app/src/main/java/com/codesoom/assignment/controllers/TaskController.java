package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private Long id = 0L;
    private TaskRepository taskRepository = new TaskRepository();



    @GetMapping
    public List<Task> list() {
        return taskRepository.getTasks();
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> detail(@PathVariable Long id) {
        return ResponseEntity.of(findTask(id));
    }

    private Optional<Task> findTask(Long id) {
        return taskRepository.getTasks().stream()
                .filter(task -> task.getId() == id)
                .findFirst();
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        taskRepository.getTasks().add(task);
        return task;
    }


    @PatchMapping("{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task source) {
        Optional<Task> entity = findTask(id);

        if(entity.isPresent()) {
           Task task = entity.get();
           task.setTitle(source.getTitle());
        }
        return ResponseEntity.of(entity);
    }


//
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity <Task> delete(@PathVariable Long id) throws IOException {
        Optional<Task> task = findTask(id);
        if(task.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        taskRepository.deleteTask(task.get());
        return ResponseEntity.noContent().build();
    }


    private Long generateId() {
        id++;
        return id;
    }

}
