// TODO
// 1. Read Collection - GET /tasks      => 완료
// 2. Read Item - GET /tasks/{id}       => 완료
// 3. Create - POST /tasks              => 완료
// 4. Update - PUT/PATCH /tasks/{id}    => 완료
// 5. Delete - DELETE /tasks/{id}       => 완료

package com.codesoom.assignment.controllers;


import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    @GetMapping("/tasks")
    public List<Task> list() {
        return tasks;
    }

    @GetMapping("/tasks/{id}")
    public String findOne(@PathVariable int id) {
        if (!findTaskId(id)) {
            return "입력하신 ID는 존재하지 않습니다.";
        }
        return tasks.get(id -1).toString();
    }

    @PostMapping("/tasks")
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    @RequestMapping(path = "/tasks/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public String modify(@PathVariable int id, @RequestBody Task task) {
        if (!findTaskId(id)) {
            return "입력하신 ID는 존재하지 않습니다.";
        }
        Task source = findTask((long) id);
        source.setTitle(task.getTitle());
        return source.toString();
    }

    @DeleteMapping("/tasks/{id}")
    public String delete(@PathVariable int id) {
        if (!findTaskId(id)) {
            return "입력하신 ID는 존재하지 않습니다.";
        }
        tasks.remove(id -1);
        return "";
    }

    private Long generateId() {
        return ++newId;
    }

    private boolean findTaskId(int id) {
        if (id == 0 ||  tasks.size() <= id -1 ) {
            return false;
        }
        return true;
    }

    private Task findTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
