package com.codesoom.assignment.services;

import com.codesoom.assignment.common.exceptions.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 할 일(Task) 데이터를 가공하여 반환합니다.
 */
@Service
public class TaskService {

    private Long taskIdSeqeuence = 0L;
    private LinkedHashMap<Long, Task> tasksMap = new LinkedHashMap<>();

    private Logger log = LoggerFactory.getLogger(TaskService.class);

    /**
     * 할 일 리스트를 반환합니다.
     * @return 할 일 리스트
     */
    public List<Task> getTaskList() {
        log.info(">>> Access Service 할 일 목록 조회");
        return tasksMap.values().stream()
                .collect(Collectors.toList());
    }

    /**
     * 주어진 할 일 ID에 해당하는 할 일 정보를 조회하여 반환합니다.
     * @param taskId 조회할 할 일 ID
     * @return 조회한 할 일 정보
     */
    public Task getTaskOne(Long taskId) {
        log.info(">>> Access Service 할 일 상세조회");

        Task findTask = tasksMap.get(taskId);

        if( findTask == null ) {
            throw new TaskNotFoundException(taskId);
        }

        return findTask;
    }

    /**
     * 새로운 할 일을 등록합니다.
     * @param newTask 새로 등록할 할 일
     * @return 등록한 할 일
     */
    public Task saveTask(Task newTask) {
        log.info(">>> Access Service 할 일 등록");

        Long newTaskId = getNewTaskId();
        newTask.setId(newTaskId);

        tasksMap.put(newTask.getId(), newTask);

        return newTask;
    }

    /**
     * 주어진 할 일 Id에 해당하는 할 일의 제목을 수정합니다.
     * @param taskId 수정 대상인 할 일의 Id
     * @param newTitle 수정할 할 일 제목
     * @return 수정한 할 일
     */
    public Task updateTask(Long taskId, String newTitle) {
        log.info(">>> Access Service 할 일 수정");

        Task foundTask = tasksMap.get(taskId);

        // 해당하는 Task가 없을 경우 예외 발생
        if(foundTask == null){
            throw new TaskNotFoundException(taskId);
        }
        foundTask.setTitle(newTitle);

        return foundTask;
    }

    /**
     * 주어진 할 일 Id에 해당하는 할 일을 목록에서 삭제합니다.
     * @param taskId 삭제할 할 일의 Id
     * @return 삭제한 할 일
     */
    public Task removeTask(Long taskId) {
        log.info(">>> Access Service 할 일 삭제");

        Task foundTask = tasksMap.get(taskId);// taskId에 해당하는 Task를 구함

        // 해당하는 Task가 없을 경우 예외 발생
        if(foundTask == null){
            throw new TaskNotFoundException(taskId);
        }
        tasksMap.remove(taskId);

        return foundTask;
    }

    /**
     * 새로 생성할 할 일에 부여할 ID를 반환합니다.
     * @return 새로 생성할 할 일의 ID
     */
    private Long getNewTaskId() {
        taskIdSeqeuence += 1;
        return taskIdSeqeuence;
    }

}
