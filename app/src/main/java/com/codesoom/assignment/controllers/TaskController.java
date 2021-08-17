// TODO
// 1. Read Collection - GET /tasks => 완료
// 2. Read Item - GET /tasks/{id}
// 3. Create - POST /tasks => 완료
// 4. Update - PUT/PATCh /tasks/{id} => 완료
// 5. Delete - DELETE /tasks/{id} => WIP

package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private HashMap<Long, Task> tasks = new HashMap<>();
    private long newId = 0L;

    @GetMapping
    public ArrayList<Task> list() {
        return new ArrayList<>(tasks.values());
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);

        return task;
    }

    @PutMapping
    @PatchMapping
    @RequestMapping("/{id}")
    public Task modify(@RequestBody Task task, @PathVariable("id") long id) {
        task.setId(id);
        tasks.put(task.getId(), task);

        return task;
    }

//    @DeleteMapping
//    @RequestMapping("/{id}")
//    public String delete(@PathVariable("id") long id) {
//        tasks.remove(id);
//        return "삭제됨";
//    }

    private Long generateId() {
        newId += 1;
        return newId;
    }
}
