package com.codesoom.assignment.task;

import com.codesoom.assignment.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @PostMapping
    public Task createTask(@RequestBody Task task){
        Task newTask = new Task(plusId(), task.getTitle());
        tasks.add(newTask);
        return newTask;
    }

    @PutMapping("/{id}")
    public Task updateTask(@RequestBody Task task, @PathVariable Long id){
        Task crtTask = findTaskById(id);
        crtTask.updateTitle(task.getTitle());
        return crtTask;
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
