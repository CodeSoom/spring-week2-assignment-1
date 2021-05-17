// TODO
// 1. Read Collection - GET /tasks
// 2. Read Item - GET /tasks/{id}
// 3. Read Create - POST /tasks
// 4. Read Update - PUT/PATCH /tasks/{id}
// 5. Read Delete - DELETE /tasks/{id}

package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 할 일(Task) 관련 Request를 수신하고 Response를 반환합니다.
 */
@RestController
@RequestMapping("/tasks")
@CrossOrigin("*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    private Logger log = LoggerFactory.getLogger(TaskController.class);

    /**
     * 모든 할 일 리스트를 반환합니다.
     * @return 할 일 리스트
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> list() {
        log.info(">>>>>>>>>>> Access Controller 할 일 목록 조회");
        List<Task> taskList = this.taskService.getTaskList();

        log.info(">>>>>>>>>>> Response : " + taskList.toString());

        return taskList;
    }

    /**
     * 주어진 할 일 ID에 해당하는 할 일 정보를 조회하여 반환합니다.
     * @param taskId 조회할 할 일 ID
     * @return 조회한 할 일
     */
    @GetMapping("{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public Task detail(@PathVariable("taskId") Long taskId) {
        log.info(">>> Access Controller 할 일 상세조회");
        log.info("==== Req Param ====");
        log.info("taskId : " + taskId);
        log.info("===================");

        Task findTask = this.taskService.findTaskOne(taskId);

        log.info(">>>>>>>>>>> Response : " + findTask.toString());
        
        return findTask;
    }

    /**
     * 새로운 할 일을 등록합니다.
     * @param newTask 새로 등록할 할 일
     * @return 새로 등록한 할 일
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task newTask) {
        log.info(">>>>>>>>>>> Access Controller 할 일 등록");
        log.info("==== Req Param ====");
        log.info("newTask : " + newTask.toString());
        log.info("===================");
        Task createdTask = this.taskService.saveTask(newTask);

        log.info(">>>>>>>>>>>> Response : " + createdTask.toString());

        return createdTask;
    }

    /**
     * 주어진 할 일 Id에 해당하는 할 일의 제목을 수정합니다.
     * @param taskId 수정 대상인 할 일의 Id
     * @param task 수정할 할 일 정보
     * @return 수정한 할 일
     */
    @PutMapping("{taskId}")
    @PatchMapping("{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public Task update(@PathVariable("taskId") Long taskId, @RequestBody Task task) {
        log.info(">>>>>>>>>>> Access Controller 할 일 수정");
        log.info("==== Req Param ====");
        log.info("taskId : " + taskId);
        log.info("task : " + task.toString());
        log.info("===================");

        Task updatedTask = this.taskService.updateTask(taskId, task.getTitle());

        log.info(">>>>>>>>>>> Response : " + updatedTask.toString());

        return updatedTask;
    }

    /**
     * 주어진 할 일 Id에 해당하는 할 일을 목록에서 삭제합니다.
     * @param taskId 삭제할 할 일의 Id
     * @return 삭제한 할 일
     */
    @DeleteMapping("{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Task delete(@PathVariable("taskId") Long taskId) {
        log.info(">>>>>>>>>>> Access Controller 할 일 삭제");
        log.info("==== Req Param ====");
        log.info("taskId : " + taskId);
        log.info("===================");
        Task deletedTask = this.taskService.removeTask(taskId);

        log.info(">>>>>>>>>>> Response : " + deletedTask.toString());

        return deletedTask;
    }

}
