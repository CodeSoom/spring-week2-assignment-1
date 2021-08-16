// TODO
// 1. Read Collection - GET /tasks => work in progress..
// 2. Read Item - GET /tasks/{id}
// 3. Create - POST /tasks
// 4. Update - PUT/PATCH /tasks/{id}
// 5. Delete - DELETE /tasks/{id}

package com.codesoom.assignment.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @RequestMapping(path = "", method = RequestMethod.GET)
    public String list() {
        return "LIST";
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String create() {
        return "Created";
    }

}
