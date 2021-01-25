package com.codesoom.assignment.task;

import com.codesoom.assignment.exception.NoContentException;
import com.codesoom.assignment.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/tasks")
@RestController
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private Long id = 1L;

    @GetMapping
    public ResponseEntity getTasks(){
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTaskById(@PathVariable Long id) throws ResourceNotFoundException {
        Task task = findTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createTask(@RequestBody Task task){
        Task newTask = new Task(plusId(), task.getTitle());
        tasks.add(newTask);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTask(@RequestBody Task task, @PathVariable Long id){
        Task crtTask = findTaskById(id);
        crtTask.updateTitle(task.getTitle());
        return new ResponseEntity<>(crtTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) throws NoContentException {
        Task deleteTask = findTaskById(id);
        tasks.remove(deleteTask);
        return new ResponseEntity<>(deleteTask, HttpStatus.NO_CONTENT);
    }

    
    public Long plusId(){
        return id++;
    }

    public Task findTaskById(Long id) throws ResourceNotFoundException {
        for (Task task : tasks){
            if (task.getId() == id)
                return task;
        }
        throw new ResourceNotFoundException("no task id : " + id);
    }

}
