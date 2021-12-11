package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 할 일을 관리하는 클래스이다.
 */
public class TaskRepository {

    private static Map<Long, Task> tasks = new LinkedHashMap<>();
    private static Long taskId = 0L;

    /**
     * 할 일 목록을 반환한다.
     * @return 할 일 목록
     */
    public ArrayList<Task> findList() {
        return new ArrayList<>(tasks.values());
    }

    /**
     * 할 일을 추가한다.
     * @param task 추가할 할 일
     * @return 추가된 할 일
     */
    public Task add(Task task) {
        task.setId(generateId());
        tasks.put(taskId, task);
        return task;
    }

    /**
     * 할 일을 찾아서 반환한다.
     * @param id 할 일의 pk
     * @return 찾은 할 일
     */
    public Optional<Task> findDetail(Long id) {
        Task task = tasks.get(id);
        return Optional.ofNullable(task);
    }

    /**
     * 할 일을 수정한다.
     * @param id 수정할 할 일의 pk
     * @param title 수정할 할 일의 제목
     * @return 수정된 할 일
     */
    public Optional<Task> update(Long id, String title) {
        Optional<Task> find = findDetail(id);

        find.ifPresent(task -> task.setTitle(title));

        return find;
    }

    /**
     * 할 일을 삭제한다.
     * @param id 삭제할 할 일의 pk
     */
    public void delete(Long id) {
        tasks.remove(id);
    }

    /**
     * 할 일의 pk 값을 생성한다.
     * @return 생성된 할 일의 pk
     */
    private static synchronized Long generateId() {
        taskId += 1;
        return taskId;
    }
}
