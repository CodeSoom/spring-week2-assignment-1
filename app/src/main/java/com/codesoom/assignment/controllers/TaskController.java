package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("tasks")
public class TaskController {
    private TaskRepository taskRepository;
    private Long newId = 0L;

    TaskController(){
        this.taskRepository = new TaskRepository();
    }

    private synchronized Long generatedId(){
        newId += 1;
        return newId;
    }

    @GetMapping()
    public List<Task> getAll() {
        return taskRepository.getTasks();
    }

    @PostMapping()
    public ResponseEntity<Task> create(@RequestBody Task task) {
        task.setId(generatedId());
        taskRepository.getTasks().add(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Task> read(@PathVariable("id") Long id) {
        Optional<Task> task = taskRepository.getTasks().stream().filter(t -> t.getId().equals(id))
                .findFirst();
        if(task.isPresent()){
            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<Task> getTaskResponseEntity(@RequestBody Task updateTask, @PathVariable("id") Long id) {
        Optional<Task> task = taskRepository.getTasks().stream().filter(t -> t.getId().equals(id))
                .findFirst();
        if(task.isPresent()){
            if(updateTask != null){
                task.get().setTitle(updateTask.getTitle());
            }
            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Task> update(@RequestBody Task updateTask, @PathVariable("id") Long id) {
        return getTaskResponseEntity(updateTask, id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Task> partialUpdate(@RequestBody Task updateTask, @PathVariable("id") Long id) {
        return getTaskResponseEntity(updateTask, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional<Task> task = taskRepository.getTasks().stream().filter(t -> t.getId().equals(id))
                .findFirst();
        if(task.isPresent()){
            taskRepository.getTasks().remove(task.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
