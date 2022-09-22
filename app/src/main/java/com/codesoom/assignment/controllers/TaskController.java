package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin("http://localhost:3000")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * GET /tasks 모든 task를 조회한다.
     * 정상응답시 OK를 응답한다.
     *
     * @return 모든 Task
     */
    @GetMapping
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    /**
     * GET /tasks/id task를 조회한다.
     * 정상응답시 OK를 응답한다.
     *
     * @param id path 파라미터로 조회할 Task의 id
     * @return 조회한 Task
     * @throws TaskNotFoundException 조회할 Task가 존재하지 않는 경우이다.
     */
    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        if (!taskRepository.isExist(id)) {
            throw new TaskNotFoundException();
        }
        return taskRepository.findById(id);

    }

    /**
     * POST /tasts task를 생성한다.
     * 정상응답시 CREATED 응답한다.
     *
     * @param task 생성할 Task
     * @return 생성한 Task
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    /**
     * PUT, PATCH /tasks/id task를 수정한다.
     * 정상응답시 OK를 응답한다.
     *
     * @param id   path 파라미터로 수정할 Task의 id
     * @param task requestBody로 받은 수정할 task
     * @return 수정한 Task
     * @throws TaskNotFoundException 조회할 Task가 존재하지 않는 경우이다.
     */
    @RequestMapping(path = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        if (!taskRepository.isExist(id)) {
            throw new TaskNotFoundException();
        }
        task.setId(id);
        return taskRepository.update(task);
    }

    /**
     * DELETE /tasks/id task를 삭제한다.
     * 정상응답시 NO_CONTENT를 응답한다.
     *
     * @param id path 파라미터로 삭제할 Task의 id
     * @throws TaskNotFoundException 삭제할 Task가 존재하지 않는 경우이다.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        if (!taskRepository.isExist(id)) {
            throw new TaskNotFoundException();
        }
        taskRepository.delete(id);
    }

    /**
     * TaskNotFoundException의 ExceptionHandler이다.
     *
     * @param error TaskNotFoundException의 error
     * @return 404 NOT_FOUND 상태코드를 반환한다.
     */
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handleTaskException(TaskNotFoundException error) {
        return new ResponseEntity<>(error.getMessage(), HttpStatus.NOT_FOUND);
    }
}
