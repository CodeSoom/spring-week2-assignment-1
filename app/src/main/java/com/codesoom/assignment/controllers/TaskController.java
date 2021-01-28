// TODO
// 1. Read Collection - GET /tasks      => 완료
// 2. Read Item - GET /tasks/{id}       => 완료
// 3. Create - POST /tasks              => 완료
// 4. Update - PUT/PATCH /tasks/{id}    => 완료
// 5. Delete - DELETE /tasks/{id}       => 완료

package com.codesoom.assignment.controllers;


import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping("/tasks/{id}")
    public String findOneTask(@PathVariable Long id) {
        return taskService.findOneTask(id);
    }

    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @RequestMapping(path = "/tasks/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public String taskModify(@PathVariable Long id, @RequestBody Task task) {
        return taskService.taskModify(id, task);
    }

    @DeleteMapping("/tasks/{id}")
    public String delete(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }
}
