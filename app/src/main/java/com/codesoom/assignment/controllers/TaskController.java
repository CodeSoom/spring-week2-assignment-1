package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Task Controller 입니다.
 */
@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    /**
     * Task Repository 입니다.
     */
    TaskRepository taskRepository = new TaskRepository();

    /**
     * 할 일 리스트를 전체 조회하는 컨트롤러입니다.
     * @return List<Task> 전체 할 일 리스트
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTaskList() {
        return taskRepository.getTasks();
    }

    /**
     * id로 찾은 할 일을 조회하는 컨트롤러입니다.
     *
     * @param id 조회할 식별자 Id
     * @return Task 조회한 할 일
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task getTask(@PathVariable Long id) {
        Optional<Task> task = taskRepository.findTaskById(id);
        if (!task.isPresent()) {
            throw new TaskNotFoundException(Long.toString(id));
        }
        return task.get();
    }

    /**
     * 새로운 할 일을 생성하는 컨트롤러입니다.
     *
     * @param task 생성 요청된 할 일
     * @return Task 생성된 할 일
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        taskRepository.createNewTaskId();
        Task newTask = new Task(taskRepository.getNewId(), task.getTitle());
        taskRepository.addTask(newTask);

        return newTask;
    }

    /**
     * 할 일을 찾아서 title을 변경합니다.
     *
     * @param id 수정할 식별자 Id
     * @param requestTask 변경 요청된 할 일
     * @return Task 변경된 할 일
     */
    @RequestMapping(value = "{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    @ResponseStatus(HttpStatus.OK)
    public Task updateTask(@PathVariable Long id, @RequestBody Task requestTask) {
        Optional<Task> task = taskRepository.findTaskById(id);
        if (!task.isPresent()) {
            throw new TaskNotFoundException(Long.toString(id));
        }

        Task updateTask = new Task(task.get().getId(), requestTask.getTitle());
        taskRepository.removeTask(task.get());
        taskRepository.addTask(updateTask);
        return updateTask;
    }

    /**
     * 완료된 할 일을 삭제합니다.
     *
     * @param id 완료된 Id 식별자
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void completeTask(@PathVariable Long id) {
        Optional<Task> task = taskRepository.findTaskById(id);
        if (!task.isPresent()) {
            throw new TaskNotFoundException(Long.toString(id));
        }
        taskRepository.removeTask(task.get());
    }

}