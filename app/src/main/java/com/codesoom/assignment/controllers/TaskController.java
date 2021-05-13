package com.codesoom.assignment.controllers;
// 1. READ Collection - GET / tasks => 완료
// 2. READ Item - GET / tasks/{id} => 완료
// 3. CREATE POST/tasks => 완료
// 4. UPDATE PUT/PATCH/tasks/{id} => 완료
// 5. DELETE DELETE/tasks/{id} => 완료

import com.codesoom.assignment.ErrorCreate;
import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;


    // GET
    @GetMapping
    public List<Task> list() {
        return tasks;
    }

    // GET tasks/id
    @GetMapping("/{id}")
    public Task getTask(@PathVariable(value = "id") Long id) {
        return selectTask(tasks, id);
    }

    // POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    // UPDATE
    @PutMapping("/{id}")
    public Task update(@PathVariable(value = "id") Long id, @RequestBody Task task) {
        Task originTask = selectTask(tasks, id);
        if (originTask != null) {
            originTask.setTitle(task.getTitle());
            return originTask;
        }
        return null;
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id){
        tasks.remove(selectTask(tasks, id));
    }

    private Task selectTask(List<Task> tasks, Long id) {
//        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst().orElseThrow(ErrorCreate::new);
    }

    private Long generateId(){
        newId += 1;
        return newId;
    }

//    public static class StatusCode {
//        public static final int OK = 200;
//        public static final int CREATED = 201;
//        public static final int NO_CONTENT = 204;
//        public static final int BAD_REQUEST =  400;
//        public static final int UNAUTHORIZED = 401;
//        public static final int FORBIDDEN = 403;
//        public static final int NOT_FOUND = 404;
//        public static final int INTERNAL_SERVER_ERROR = 500;
//        public static final int SERVICE_UNAVAILABLE = 503;
//        public static final int DB_ERROR = 600;
//    }

}

// status code를 어디로 리턴해야 하는 것인지.