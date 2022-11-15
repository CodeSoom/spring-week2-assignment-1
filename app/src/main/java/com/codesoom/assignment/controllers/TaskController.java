package com.codesoom.assignment.controllers;

import com.codesoom.assignment.handler.ErrorHandler;
import com.codesoom.assignment.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private Long id = 0L;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NullPointerException.class)
    public ErrorHandler NPEHandler(NullPointerException ne) {
        return new ErrorHandler("Not Found", ne.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequest.class)
    public ErrorHandler BadRequestHandler(BadRequest badRequest) {
        return new ErrorHandler("Bad Request", "다시 한 번 확인해주세요.");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorHandler notReadableHandler(HttpMessageNotReadableException he) {
        return new ErrorHandler("Not Readable", "title이 존재하지 않습니다. 다시 한 번 확인해주세요.");
    }

    /**
     * GET /tasks
     * @return tasks
     */
    @GetMapping
    public List<Task> getLists() {
        return tasks;
    }

    /**
     * GET /tasks/{id}
     * TODO 찾고자 하는 task가 없을 경우 404에러
     * @param id
     * @return task
     */
    @GetMapping("/{id}")
    public Task getTask(@PathVariable(name = "id") Long id) {

        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Task가 존재하지 않습니다. ID를 다시 한 번 확인해주세요. id="+id));
    }

    /**
     * POST /tasks
     * @param task
     * @return task
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createList(@RequestBody Task task) {
        task.setTitle(task.getTitle());
        task.setId(generateId());
        tasks.add(task);

        return task;
    }

    //TODO PUT, PATCH 코드 동일함. 어떻게 처리?
    /**
     * PUT /tasks/{id}
     * @param id
     * @param task
     * @return task
     */
    @PutMapping("/{id}")
    public Task putTask(@PathVariable(name = "id") Long id, @RequestBody Task task) {

        Task filteredTask = tasks.stream()
                            .filter(t -> t.getId().equals(id))
                            .findFirst()
                            .orElseThrow(() -> new NullPointerException("ID를 다시 한 번 확인해주세요. ID="+id));
        //NPE 발생 가능
        if (task.getTitle().isEmpty()) throw new NullPointerException("Title을 입력해주세요.");

        filteredTask.setTitle(task.getTitle());

        return filteredTask;
    }

    /**
     * PATCH /tasks/{id}
     * @param id
     * @param task
     * @return task
     */
    @PatchMapping("/{id}")
    public Task patchTask(@PathVariable(name = "id") Long id, @RequestBody Task task) {

        Task filteredTask = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
        filteredTask.setTitle(task.getTitle());

        return filteredTask;
    }

    /**
     * TODO HttpStatus NO_CONTENT(204)에 대해 알아보기
     * DELETE /tasks/{id}
     * @param id
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable(name = "id") Long id) {
        Task filteredTask = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Task가 존재하지 않습니다. ID를 다시 한 번 확인해주세요. id="+id));

        tasks.remove(filteredTask);
    }

    /**
     * id 1씩 증가
     * @return 1 증가된 id
     */
    private Long generateId() {
        id += 1;
        return id;
    }
}
