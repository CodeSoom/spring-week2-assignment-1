// TODO
// 1. Read Collection - GET /tasks => 완료
// 2. Read Item - GET /tasks/{id} => 완료
// 3. Create - POST /tasks => 완료
// 4. Update - PUT/PATCH /tasks/{id} => 완료
// 5. Delete - DELETE /tasks/{id} => 완료

package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        return new ArrayList<>(tasks.values());
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);

        return task;
    }

    @PutMapping("/{id}")
    public Task modify(@RequestBody Task task, @PathVariable("id") Long id) {
        task.setId(id);
        tasks.put(task.getId(), task);

        return task;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        if(tasks.get(id) != null) {
            tasks.remove(id);
            return "삭제됨";
        } else {
            return "해당 id를 가진 task 없음";
        }

    }

    private Long generateId() {
        newId += 1;
        return newId;
    }
}
