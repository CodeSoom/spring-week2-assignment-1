package com.codesoom.assignment.task;

import com.codesoom.assignment.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/tasks")
@RestController
public class TaskController {

    private static List<Task> tasks = new ArrayList<>();

    @GetMapping
    public List<Task> getTasks(){
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) throws ResourceNotFoundException {
        Task task = findTaskById(id);
        return task;
    }

    public Task findTaskById(Long id) throws ResourceNotFoundException {
        for (Task task : tasks){
            if (task.getId() == id)
                return task;
        }
        throw new ResourceNotFoundException("no task id : " + id);
    }

}
