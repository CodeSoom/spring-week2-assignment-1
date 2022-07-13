package com.codesoom.assignment.controllers;

import com.codesoom.assignment.TaskRepository;
import com.codesoom.assignment.models.Task;
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
import java.util.Optional;

/**
 * /tasks path로 들어온 요청에 대한 응답을 보냅니다
 */
@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final TaskRepository repository = new TaskRepository();
    private Long newId = 0L;

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
    @GetMapping(path="/{taskId}")
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

        task.setId(generateId());
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

        Optional<Task> task = repository.getTaskById(taskId);

        if (task.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Task result = task.get();
        result.update(newTask);

        return result;
    }

    /**
     * @param taskId 삭제할 task의 id
     * @return 조회된 task가 없을 때 notFound 반환,
     * task 삭제에 성공했을 때 noContent 반환
     */
    @DeleteMapping(path="/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long taskId) {
        Optional<Task> task = repository.getTaskById(taskId);

        if (task.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        repository.deleteTask(task.get());
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }
}
