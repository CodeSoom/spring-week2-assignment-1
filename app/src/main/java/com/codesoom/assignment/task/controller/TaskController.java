package com.codesoom.assignment.task.controller;

import com.codesoom.assignment.task.domain.Task;
import com.codesoom.assignment.task.domain.request.TaskSearchDto;
import com.codesoom.assignment.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
    1. Read Collection - GET /tasks => 완료
    2. Read Item - GET /tasks/{id}  => 완료
    3. Create - POST /tasks
    4. Update - PUT/PATCH /tasks/{id}
    5. Delete - DELETE /tasks/{id}

    TODO List
     - responseDto 생성
     - copyProperties 만들기 (requestDto -> entity, entity -> responseDto)
     - ResponseEntity 생성
*/

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> gets() {
        return taskService.gets();
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable Long id) {
        TaskSearchDto taskSearchDto = new TaskSearchDto();
        taskSearchDto.setId(id);

        return taskService.getById(taskSearchDto);
    }
}
