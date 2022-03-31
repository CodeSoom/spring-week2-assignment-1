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

/**
 *  할 일에 대한 클라이언트 요청 지점입니다.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/tasks")
@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * 리소스 전체 목록을 응답합니다.
     */
    @GetMapping
    public ResponseEntity<List<TaskViewDto>> list() {
        List<TaskViewDto> tasks = taskService.getTaskDtoList();
        return ResponseEntity.ok(tasks);
    }

    /**
     * 새로운 리소스를 생성하고 생성된 리소스 데이터를 응답합니다.
     * @param taskSaveDto 생성에 필요한 데이터
     * @see TaskViewDto
     */
    @PostMapping
    public ResponseEntity<TaskViewDto> save(@RequestBody TaskSaveDto taskSaveDto) {

        Task newTask = taskService.save(taskSaveDto);

        TaskViewDto taskViewDto = TaskViewDto.from(newTask);

        return ResponseEntity.status(HttpStatus.CREATED).body(taskViewDto);
    }

    /**
     * 단일 리소스 데이터를 응답하고 매칭되는 리소스가 없다면 404 를 응답합니다
     * @param taskId 리소스의 고유 아이디
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
     * 기존 리소스를 새로운 리소스로 대체 하고 대체된 리소스 데이터를 응답합니다.
     * <p>대체될 리소스가 없다면 404를 응답합니다.</p>
     * @param taskId 대체 대상인 리소스의 고유 아이디
     * @param taskEditDto 대체에 필요한 데이터
     * @see TaskViewDto
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
     * 리소스 일부를 수정하고 수정된 리소스 정보를 응답합니다.
     * <p>수정될 리소스가 없다면 404를 응답합니다.</p>
     * @param taskId 수정 대상인 리소스의 고유아이디
     * @param taskEditDto 수정에 필요한 데이터
     * @see TaskViewDto
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
     * 단일 리소스를 삭제합니다.
     * <p>삭제될 리소스가 없다면 404를 응답합니다.</p>
     * @param taskId 삭제할 리소스의 고유 아이디
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
