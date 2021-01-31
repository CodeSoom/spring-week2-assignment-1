package com.codesoom.assignment.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 할 일들을 관리합니다.
 *
 * @see com.codesoom.assignment.domain.Task
 */
public class Tasks {
    private Map<Long, Task> tasks;
    private Long taskId = 1L;

    public Tasks() {
        this.tasks = new LinkedHashMap<>();
    }

    /**
     * 할 일을 등록합니다.
     *
     * @param task 등록하고자하는 할 일
     */
    public void addTask(Task task) {
        Long generateId = generateId();
        task.setId(generateId);
        tasks.put(generateId, task);
    }

    /**
     * 주어진 아이디에 해당되는 할 일을 찾아 삭제합니다.
     *
     * @param id 삭제 하고자 하는 할 일의 아이디
     */
    public void remove(Long id) {
        this.tasks.remove(id);
    }

    /**
     * 주어진 아이디에 해당되는 할 일을 찾아 리턴합니다.
     *
     * @param id 찾고자 하는 할 일의 아이디
     * @return 빈 값을 포함한 검색 된 할 일
     */
    public Optional<Task> findTask(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    /**
     * 등록된 모든 할 일들을 리턴합니다.
     *
     * @return 등록된 모든 할 일들
     */
    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    /**
     * 등록된 할 일들의 숫자를 리턴합니다.
     *
     */
    public int size() {
        return tasks.size();
    }

    /**
     * 아이디를 생성해 리턴합니다.
     *
     */
    private Long generateId() {
        return taskId++;
    }
}
