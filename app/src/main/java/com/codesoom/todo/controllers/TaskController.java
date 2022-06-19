package com.codesoom.todo.controllers;

import com.codesoom.todo.domain.Task;
import com.codesoom.todo.repository.TaskRepository;
import com.codesoom.todo.services.TaskService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class TaskController {

    TaskRepository taskRepository = new TaskRepository();
    TaskService taskService = new TaskService(taskRepository);

    /**
     * 저장되어 있는 task 들을 리스트의 형태로 리턴한다.
     * @return List of Task, 만약 없다면, 빈 리스트를 리턴한다.
     */
    // TODO: fill skeleton mapper
    @GetMapping("/tasks")
    public List<Task> showTasks() {
        return taskService.showTasks();
    }

    /**
     * @param taskId 리퀘스트 경로에서 id 를 추출해서 인자로 받는다.
     * @return 저장되어 있는 태스크 중에서 인자로 받은 taskId 를 가지고 있는 태스크를 리턴한다. 만약 taskId 가 존재하지 않는다면, exception 을 발생시킨다.
     * @exception TaskNotFoundException 인자로 받은 taskId 를 가지고 있는 태스크가 없다면, 발생시킨다.
     */
    @GetMapping("/tasks/{id}")
    public Task showTask(@PathVariable("id") Long taskId) {
        return taskService.showTask(taskId);
    }

    /**
     * @param task HTTP 리퀘스트의 인자로 받은 title 을 이용해 새로운 태스크를 인자로 받는다.
     * @return 새로운 태스크를 생성해서 리포지토리에 추가하고, 해당 태스크를 리턴한다.
     */
    @PostMapping("/tasks")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    /**
     * PUT, PATCH 메소드로 받은 경로에서 id 를 추출해서 해당 태스크가 존재하다면, 리퀘스트 바디의 title로 해당 태스크의 타이틀을 수정한다.
     * @param taskId 리퀘스트 경로에서 id 를 추출해서 인자로 받는다.
     * @param task HTTP 리퀘스트의 인자로 받은 title 을 이용해 새로운 태스크를 인자로 받는다.
     * @return 삭제된 태스크
     * @exception TaskNotFoundException 인자로 받은 taskId 를 가지고 있는 태스크가 없다면, 발생시킨다.
     */
    @RequestMapping(value = "tasks/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    @ResponseStatus(value = HttpStatus.OK)
    public Task editTitle(@PathVariable("id") Long taskId, @RequestBody @NotNull Task task) {
        task.setId(taskId);
        return taskService.editTaskTitle(task);
    }

    /**
     * DELETE 메소드로 받은 경로에서 id 를 추출해서 해당 태스크가 존재하다면, 리퀘스트 바디의 title로 해당 태스크의 타이틀을 수정한다.
     * @param taskId 리퀘스트 경로에서 id 를 추출해서 인자로 받는다.
     * @exception TaskNotFoundException 인자로 받은 taskId 를 가지고 있는 태스크가 없다면, 발생시킨다.
     */
    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("id") Long taskId) {
        taskService.removeTask(taskId);
    }
}
