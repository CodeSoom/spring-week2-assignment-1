package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.Repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.management.RuntimeErrorException;
import java.util.Collection;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private TaskRepository taskRepository;
    public TaskController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Task> GetAllTask(){
        return taskRepository.getAllTask();
    }

    @GetMapping("/{TaskID}")
    @ResponseStatus(HttpStatus.OK)
    public Task GetTask(@PathVariable("TaskID") Long id) {
        return taskRepository.getKeyTask(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task Create(@RequestBody Task task){
        return taskRepository.addTask(task);
    }

    @PutMapping("/{TaskID}")
    @PatchMapping("/{TaskID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Task Revise(@PathVariable("TaskID") Long id, @RequestBody Task task) {
        if(task.getTitle().isBlank()){
            throw new RuntimeErrorException(new Error(), "content is blank");
        }
        task.setId(id);
        return taskRepository.modifyTask(task);
    }

    @DeleteMapping("/{TaskID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void Delete(@PathVariable("TaskID") Long id){
        taskRepository.removeTask(id);
    }
}
