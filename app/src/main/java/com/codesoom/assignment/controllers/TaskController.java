package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exception.TaskIdNotFoundException;
import com.codesoom.assignment.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
        Task task = findTask(id);
        return task;
    }

    @PostMapping
    public Task taskCreate(@RequestBody Task task) throws Throwable {
        task.setId(generatedId());
        tasks.add(task);
        return task;
    }

    @PutMapping("/{id}")
    public Task taskModify(@PathVariable Long id, @RequestBody Task task) {
        final String newTitle = task.getTitle();
        if(id ==null || newTitle.isBlank()){
            throw new IllegalArgumentException(String.format("ID[%s] or Title[%s] is null or blank", id, newTitle));
        }
        Task findTask = findTask(id);
        int findTaskIndex = tasks.indexOf(findTask);
        task.setId(findTask.getId());
        tasks.set(findTaskIndex, task);
        return task;
    }

    @PatchMapping("/{id}")
    public Task taskPatchModify(@PathVariable Long id, @RequestBody Task task) {
        return taskModify(id, task);
    }

    @DeleteMapping("/{id}")
    public void taskDelete(@PathVariable Long id){
        Task findTask = findTask(id);
        tasks.remove(findTask);
    }

    private Task findTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
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
                    .getId()+1;
        }
        return maxId;
    }

}
