package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.exception.DuplicateException;
import com.codesoom.assignment.exception.NotEnoughException;
import com.codesoom.assignment.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
TODO
1. READ Collection -> get/tasks
2. read item - get/tasks/{id}
3. create - post/tasks/{id}
4. update - put/patch
 */
@RestController
@CrossOrigin
@RequestMapping("/tasks")
public class TaskController {

    private final TodoService todoService = new TodoService();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> list() {
        return todoService.readAllTask();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {

        if (task.getTitle().isBlank()) {
            throw new NotEnoughException("Please Input task text");
        }

        for (Task taskList : todoService.tasks) {
            if (taskList.getTitle().equals(task.getTitle())) {
                throw new DuplicateException("같은 할 일이 있습니다.");
            }
        }

        System.out.println("task = " + task.getTitle());
        return todoService.createTask(task);
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task update(@RequestBody Task task, @PathVariable Long id) {
        System.out.println("task = " + task);
        return todoService.updateTask(task, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        todoService.deleteOneTask(id);
    }

}

