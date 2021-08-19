package com.codesoom.assignment;

import com.codesoom.assignment.models.Task;
import java.util.Collection;

/**
 * 할 일 목록 관리를 담당합니다.
 *
 * <p>
 * {@link com.codesoom.assignment.controllers.TasksController TasksController}에서 요청을 받고,
 * {@link TaskMap TaskMap}에 실제 할 작업을 요청하고 결과를 가져옵니다.
 * </p>
 *
 * @author pangnem
 * @see com.codesoom.assignment.controllers.TasksController TasksController
 * @see TaskMap
 */
public class TaskManager {

    private static final TaskManager uniqueInstance = new TaskManager();

    private final TaskMap taskMap = new TaskMap();

    private Long lastId = 0L;

    private TaskManager() {
    }

    public static TaskManager getInstance() {
        return uniqueInstance;
    }

    public Task createTask(Task task) {
        Long lastId = getLastId();
        task.setId(lastId);

        taskMap.insert(lastId, task);

        return task;
    }

    public Collection<Task> getAllTasks() {
        return taskMap.getValues();
    }

    public Task getTaskWith(Long id) {
        return taskMap.get(id);
    }

    public Task updateTask(Long id, Task content) {
        return taskMap.update(id, content.getTitle());
    }

    public void deleteTask(Long id) {
        taskMap.delete(id);
    }

    private Long getLastId() {
        increaseLastId();
        return lastId;
    }

    private synchronized void increaseLastId() {
        lastId++;
    }

}
