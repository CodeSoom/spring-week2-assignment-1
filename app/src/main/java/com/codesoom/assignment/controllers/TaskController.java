// TODO
// 1. Read Collection - GET /tasks => 완료
// 2. Read Item - GET /tasks/{id} => 완료
// 3. Create - POST /tasks => 완료
// 4. Update - PUT/PATCH /tasks/{id} => 완료
// 5. Delete - DELETE /tasks/{id} => 완료

package com.codesoom.assignment.controllers;

import com.codesoom.assignment.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 할 일 Controller
 * 할 일에 대한 생성/수정/삭제 기능 제공
 */
@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private HashMap<Long, Task> tasks = new HashMap<>();
    private long newId = 0L;

    @GetMapping
    public ArrayList<Task> list() {
        ArrayList<Task> taskList = new ArrayList<>(tasks.values());
        taskList.sort(Comparator.comparing(Task::getId));
        return taskList;
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable("id") Long id) {
        Task task = findTask(id);
        return task;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);

        return task;
    }

    @PutMapping("/{id}")
    public Task update(
            @RequestBody Task source,
            @PathVariable("id") Long id
    ) {
        Task task = findTask(id);
        task.setTitle(source.getTitle());

        return task;
    }

    private Task findTask(Long id) {
        Task task = tasks.get(id);
        if(task != null) {
            return tasks.get(id);
        } else {
            throw new TaskNotFoundException(id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> delete(@PathVariable("id") Long id) {
        Task task = findTask(id);
        tasks.remove(task.getId());

        return ResponseEntity.noContent().build();
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }
}
