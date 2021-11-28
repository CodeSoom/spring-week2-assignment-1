package com.codesoom.assignment;


import com.codesoom.assignment.Exception.TaskNotFoundException;
import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Task에 대한 CRUD 처리를 담당합니다.
 */
public class TaskManager {
    private Long nextId = 1L;
    private HashMap<Long, Task> tasks = new HashMap<>();

    private final Object nextIdLock = new Object();

    /**
     * 새로운 Task를 생성해 등록하고, 새로운 Task를 반환합니다.
     * @param title 새롭게 생성할 Task의 title
     * @return 새롭게 생성된 Task
     */
    public Task create(String title) {
        synchronized (nextIdLock) {
            nextId++;
        }

        Long newId = nextId;
        Task newTask = new Task(newId, title);
        this.tasks.put(newId, newTask);

        return newTask;
    }

    /**
     * 등록된 모든 Task 목록을 리턴합니다.
     * @return 등록된 모든 Task 목록
     */
    public List<Task> getAll() {
        return new ArrayList<Task>(this.tasks.values());
    }

    /**
     * 등록된 Task에서 요청한 id를 가진 Task를 리턴합니다.
     * @param id 가져올 Task의 id
     * @return 찾은 Task
     * @throws TaskNotFoundException
     *          등록된 Task에 요청한 id가 존재하지 않는 경우
     */
    public Task getOne(Long id) throws TaskNotFoundException {
        if(!exist(id)) {
            String message = "id(" + id + ")에 대한 Task에 접근할 수 없습니다.";
            throw new TaskNotFoundException(message);
        }

        return this.tasks.get(id);
    }

    /**
     * 등록된 Task에서 요청한 id를 가진 Task를 제거합니다.
     * @param id 제거할 Task의 id
     * @throws TaskNotFoundException
     *          등록된 Task에 요청한 id가 존재하지 않는 경우
     */
    public void remove(Long id) throws TaskNotFoundException {
        if(!exist(id)) {
            String message = "id(" + id + ")에 대한 Task에 접근할 수 없습니다.";
            throw new TaskNotFoundException(message);
        }

        this.tasks.remove(id);
    }

    /**
     * 요청한 id의 Task의 title를 변경합니다.
     * @param id 변경할 Task의 id
     * @param title 변경할 Task의 title
     * @throws TaskNotFoundException
     *          등록된 Task에 요청한 id가 존재하지 않는 경우
     */
    public void update(Long id, String title) throws TaskNotFoundException {
        Task updateTask = getOne(id);
        updateTask.setTitle(title);
    }

    /**
     * 요청한 id가 등록된 Task에 존재하면 true를 리턴하고, 아닐 경우 false를 리턴합니다.
     * @param id 등록된 Task에서 존재 여부를 확인할 Task의 id
     * @return 요청한 id가 등록된 Task에 존재하면 true, 아닐 경우 false
     */
    private boolean exist(Long id) {
        return this.tasks.containsKey(id);
    }
}
