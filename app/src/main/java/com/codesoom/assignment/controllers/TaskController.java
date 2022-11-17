package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.model.Task;
import org.springframework.http.HttpStatus;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private Long id = 0L;

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
                .orElseThrow(TaskNotFoundException::new);
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

    /**
     * PUT/PATCH /tasks/{id}
     * @param id
     * @param task
     * @return task
     */
    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Task updateTask(@PathVariable(name = "id") Long id, @RequestBody Task task) {

        Task filteredTask = tasks.stream()
                            .filter(t -> t.getId().equals(id))
                            .findFirst()
                            .orElseThrow(() -> new NullPointerException("ID를 다시 한 번 확인해주세요."));
        //NPE 발생 가능
        if (task.getTitle().isEmpty()) {
            throw new NullPointerException("Title을 입력해주세요.");
        }

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
                .orElseThrow(TaskNotFoundException::new); // = () -> new TaskNotFoundException()

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
