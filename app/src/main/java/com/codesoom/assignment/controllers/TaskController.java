package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.dto.TaskViewDto;
import com.codesoom.assignment.dto.TaskEditDto;
import com.codesoom.assignment.dto.TaskSaveDto;
import com.codesoom.assignment.service.TaskService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/tasks")
@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * 모든 Task 를 조회합니다.
     */
    @GetMapping
    public ResponseEntity<List<TaskViewDto>> list() {
        List<TaskViewDto> tasks = taskService.getTaskDtoList();
        return ResponseEntity.ok(tasks);
    }

    /**
     * 새로운 Task를 생성합니다.
     * @param taskSaveDto 생성에 필요한 데이터
     * @see TaskSaveDto
     */
    @PostMapping
    public ResponseEntity<TaskViewDto> save(@RequestBody TaskSaveDto taskSaveDto) {
        TaskViewDto taskViewDto = taskService.save(taskSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskViewDto);
    }

    /**
     * Task를 조회합니다.
     * @param taskId Task 아이디
     * @see TaskViewDto
     */
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskViewDto> view(@PathVariable Long taskId) {
        Optional<Task> findTask = taskService.getTask(taskId);
        if (findTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Task task = findTask.get();
        TaskViewDto taskViewDto = TaskViewDto.from(task);
        return ResponseEntity.ok().body(taskViewDto);
    }

    /**
     * 새로운 Task로 대체 합니다.
     * @param taskId Task 아이디
     * @param taskEditDto 대체에 필요한 데이터
     * @see TaskEditDto
     */
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskViewDto> replace(@PathVariable Long taskId, @RequestBody TaskEditDto taskEditDto) {

        Optional<Task> findTask = taskService.getTask(taskId);
        if (findTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Task task = findTask.get();
        taskService.replaceTask(task, taskEditDto);

        TaskViewDto taskViewDto = TaskViewDto.from(task);
        return ResponseEntity.ok().body(taskViewDto);
    }

    /**
     * Task 일부를 수정 합니다.
     * @param taskId Task 아이디
     * @param taskEditDto 수정할 데이터
     * @see TaskEditDto
     */
    @PatchMapping("/{taskId}")
    public ResponseEntity<TaskViewDto> update(@PathVariable Long taskId, @RequestBody TaskEditDto taskEditDto) {

        Optional<Task> findTask = taskService.getTask(taskId);
        if (findTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Task task = findTask.get();
        taskService.updateTask(task, taskEditDto);

        TaskViewDto taskViewDto = TaskViewDto.from(task);
        return ResponseEntity.ok().body(taskViewDto);
    }

    /**
     * Task 삭제합니다.
     * @param taskId Task 아이디
     */
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Object> delete(@PathVariable Long taskId) {

        Optional<Task> findTask = taskService.getTask(taskId);
        if (findTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        taskService.delete(taskId);

        return ResponseEntity.noContent().build();
    }
}