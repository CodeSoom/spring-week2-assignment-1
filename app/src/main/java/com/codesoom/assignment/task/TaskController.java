package com.codesoom.assignment.task;

import com.codesoom.assignment.exception.NoContentException;
import com.codesoom.assignment.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RequestMapping("/tasks")
@RestController
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private Long id = 1L;

    @GetMapping
    public List<Task> getTasks(){
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) throws ResourceNotFoundException {
        Task task = findTaskById(id);
        return task;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Task createTask(@RequestBody Task task){
        Task newTask = new Task(nextId(), task.getTitle());
        tasks.add(newTask);
        return newTask;
    }

    @PutMapping("/{id}")
    public Task updateTask(@RequestBody Task task, @PathVariable Long id){
        Task currentTask = findTaskById(id);
        currentTask.updateTitle(task.getTitle());
        return currentTask;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        Task deleteTask = findTaskById(id);
        tasks.remove(deleteTask);
    }

    public Long nextId(){
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
