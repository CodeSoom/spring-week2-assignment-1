package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.TaskNotFoundException;
import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.domain.Tasks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/tasks")
public class TaskController {
    private Tasks tasks = new Tasks();

    @GetMapping
    public List<Task> getTasks() {
        return tasks.getTasks();
    }

    @GetMapping("{id}")
    public Task getTask(@PathVariable("id") Long id) {
        return tasks.findTask(id).orElseThrow(
                () -> new TaskNotFoundException("존재 하지 않는 task id로 task를 찾을 수 없습니다. 존재하지 않는 id:" + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(@RequestBody Task task) {
        tasks.addTask(task);
        return task;
    }

    @PutMapping("{id}")
    public Task updateTask(@PathVariable("id") Long id, @RequestBody Task source) {
        Task task = tasks.findTask(id).orElseThrow(
                () -> new TaskNotFoundException("존재 하지 않는 task id로 task를 갱신할 수 없습니다. 존재하지 않는 id: " + id));
        task.setTitle(source.getTitle());
        return task;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void Task(@PathVariable("id") Long id) {
        tasks.findTask(id).orElseThrow(
                () -> new TaskNotFoundException("존재 하지 않는 task id로 task를 삭제할 수 없습니다. 존재하지 않는 id: " + id));
        tasks.remove(id);
    }

}
