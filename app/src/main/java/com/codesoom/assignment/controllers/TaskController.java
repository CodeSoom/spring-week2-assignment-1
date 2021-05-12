// TODO
// 1. Read Collection - GET /tasks
// 2. Read Item - GET /tasks/{id}
// 3. Read Create - POST /tasks
// 4. Read Update - PUT/PATCH /tasks/{id}
// 5. Read Delete - DELETE /tasks/{id}

package com.codesoom.assignment.controllers;

import com.codesoom.assignment.common.exceptions.NotFoundTaskException;
import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private Long taskIdSeq = 0L;
    private LinkedHashMap<Long, Task> tasksMap = new LinkedHashMap<>();

    @GetMapping
    public List<Task> list() {

        List<Task> taskList = tasksMap.values().stream()
                .collect(Collectors.toList());

        return taskList;

    }

    @GetMapping("{taskId}")
    public Task detail(@PathVariable("taskId") Long taskId) {

        Task findTask = tasksMap.get(taskId);

        if( findTask == null ) {
            throw new NotFoundTaskException(taskId);
        }

        return findTask;

    }

    @PostMapping
    public Task create(@RequestBody Task newTask) {

        newTask.setId(taskIdSeq++);
        tasksMap.put(newTask.getId(), newTask);

        return newTask;

    }

    @PutMapping
    @PatchMapping("{taskId}")
    public Task update(@PathVariable("taskId") Long taskId, @RequestBody Task task) {

        Task findTask = tasksMap.get(taskId);
        findTask.setTitle(task.getTitle());

        return findTask;

    }

    @DeleteMapping("{taskId}")
    public Task delete(@PathVariable("taskId") Long taskId) {

        Task findTask = tasksMap.remove(taskId);

        return findTask;

    }

}
