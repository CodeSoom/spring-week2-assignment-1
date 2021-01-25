package com.codesoom.assignment.task;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/tasks")
@RestController
public class TaskController {

    private static List<Task> tasks = Arrays.asList(
            new Task(1L, "title1"),
            new Task(2L, "title2"),
            new Task(3L, "title3")
    );

    @GetMapping
    public List<Task> getTasks(){
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return tasks.get((int) (id-1));
    }

}
