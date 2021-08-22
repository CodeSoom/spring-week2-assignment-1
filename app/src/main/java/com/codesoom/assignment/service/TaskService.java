package com.codesoom.assignment.service;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.enums.ExceptionMessageType;
import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.model.TaskRequest;
import com.codesoom.assignment.model.TaskResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * 할 일 목록을 조회, 추가, 수정, 삭제 한다.
 */
@Service
public class TaskService {
    private static final long INIT_ID = 0L;
    private final Map<Long, Task> tasks;
    private final AtomicLong taskKey;

    public TaskService() {
        this.tasks = new ConcurrentHashMap<>();
        this.taskKey = new AtomicLong(INIT_ID);
    }

    /**
     * 할 일 목록을 리턴한다.
     * @return 할 일 목록
     */
    public List<TaskResponse> taskList() {
        return tasks.values().stream()
                .map(task -> new TaskResponse(task.id(), task.title()))
                .collect(Collectors.toList());
    }

    /**
     * id에 해당하는 할 일을 찾아서 리턴한다.
     * @param id
     * @return 할 일
     * @throws TaskNotFoundException id에 해당하는 할 일을 찾지 못한 경우
     */
    public TaskResponse getTask(Long id) {
        Task task = task(id);
        return new TaskResponse(task.id(), task.title());
    }

    /**
     * 할 일을 추가 한다.
     * @param taskRequest
     * @return 추가된 할 일
     */
    public TaskResponse addTask(TaskRequest taskRequest) {
        long id = taskKey.incrementAndGet();
        Task task = new Task(id, taskRequest.getTitle());
        tasks.put(id, task);
        return new TaskResponse(task.id(), task.title());
    }

    /**
     * id에 해당하는 할 일을 수정 한다.
     * @param taskRequest
     * @return 수정된 할 일
     * @throws TaskNotFoundException id에 해당하는 할 일을 찾지 못한 경우
     */
    public TaskResponse modifyTask(TaskRequest taskRequest) {
        Task task = task(taskRequest.getId());
        task.changeTitle(taskRequest.getTitle());
        return new TaskResponse(task.id(), task.title());
    }

    /**
     * 할 일을 삭제 한다.
     * @param id
     * @throws TaskNotFoundException id에 해당하는 할 일을 찾지 못한 경우
     */
    public void deleteTask(Long id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
            return ;
        }
        throw new TaskNotFoundException(id, ExceptionMessageType.DELETE);
    }

    /**
     * id에 해당하는 할 일을 찾아 리턴 한다.
     * @param id
     * @return 할 일
     * @throws TaskNotFoundException id에 해당하는 할 일을 찾지 못한 경우
     */
    private Task task(Long id) {
        if (tasks.containsKey(id)) {
            return tasks.get(id);
        }
        throw new TaskNotFoundException(id, ExceptionMessageType.GET);
    }

}
