package com.codesoom.assignment.controllers;

import com.codesoom.assignment.Task;
import com.codesoom.assignment.TaskList;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Handle requests about task.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {
    TaskList taskList = new TaskList();
    JsonParser jsonParser = new JsonParser();

    @GetMapping
    public String list() throws IOException {
        return jsonParser.toJson(taskList.getTaskList());
    }

    @GetMapping("/{id:[0-9]+}")
    public String detail(@PathVariable Long id) throws IOException {
        return jsonParser.toJson(taskList.getTask(id));
    }

    @PostMapping
    public String create(@RequestBody String title) throws IOException {
        Task task = taskList.add(title);
        return jsonParser.toJson(task);
    }

    @RequestMapping(path = "/{id:[0-9]+}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public String update(@PathVariable Long id, @RequestBody String title) throws IOException {
        Task task = taskList.modify(id, title);
        return jsonParser.toJson(task);
    }

    @DeleteMapping("/{id:[0-9]+}")
    public String delete(@PathVariable Long id) throws IOException {
        taskList.remove(id);
        return "Deleted";
    }
}
