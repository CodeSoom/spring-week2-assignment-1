//TODO
// 1. Read Collection - GET /tasks => 완료
// 2. Read Item - GET /tasks/
// 3. Create - POST /tasks => 완료
// 4. Update - PUT/PATCH /tasks
//버튼 추가 - (조회)수정 / (수정시)확인, 취소
// 5. DELETE - DELETE /tasks
//버튼 추가 - (조회)완료

package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    //@RequestMapping(path = "", method = RequestMethod.GET)
    @GetMapping //훨씬 더 수월함.
    public List<Task> list() {
        return tasks;
    }

    //@RequestMapping(path = "", method = RequestMethod.POST)
    @PostMapping
    public Task create(@RequestBody Task task) {
        //String body = getBody(exchange);

        //String title = "test";
        //Task task = toTask(body);
        //Task task = new Task();
        task.setId(generate());
        //task.setTitle(title);
        tasks.add(task);

        return task;
    }

    @PatchMapping

    private Long generate(){
        newId += 1;
        return newId;
    }
}
