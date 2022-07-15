package com.codesoom.assignment.controllers;

import com.codesoom.assignment.TaskRepository;
import com.codesoom.assignment.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * /tasks path로 들어온 요청에 대한 응답을 보냅니다
 */
@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final TaskRepository repository;

    @Autowired
    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    /**
     * @return 저장된 task 목록
     */
    @GetMapping
    public List<Task> getTasks() {
        return repository.getTasks();
    }

    /**
     * @param taskId 조회할 task의 id
     * @return task id로 조회된 task
     */
    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable Long taskId) {
        return repository.getTaskById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * @param task 추가할 task
     * @return 추가된 task
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        if (task == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "request body에 title을 입력해주세요");
        }

        repository.addTask(task);

        return task;
    }

    /**
     * @param taskId 수정할 task의 id
     * @param newTask 업데이트할 내용이 담긴 task
     * @return 조회되는 task가 없을 때는 notFound 반환,
     * task 수정에 성공했을 때는 수정된 task 반환
     */
    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, path = "/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task newTask) {
        if (newTask == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Task task = repository.getTaskById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        task.update(newTask);

        return task;
    }


    /**
     * 해당 id의 task를 삭제합니다
     * @param taskId 삭제할 task의 id
     */
    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long taskId) {
        Task task = repository.getTaskById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.deleteTask(task);
    }
}
