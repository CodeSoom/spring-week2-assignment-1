package com.codesoom.assignment;


import com.codesoom.assignment.Exception.TaskNotFoundException;
import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Task 목록 정보를 저장하고 있으며, Task 목록에 대한 CRUD 처리를 담당합니다.
 */
public class TaskManager {
    private Long nextId = 1L;
    private HashMap<Long, Task> tasks = new HashMap<>();

    public void create(String title) {
        Long newId = nextId++;
        Task newTask = new Task(newId, title);
        this.tasks.put(newId, newTask);
    }

    public List<Task> getAll() {
        return new ArrayList<Task>(this.tasks.values());
    }

    public Task getOne(Long id) throws TaskNotFoundException {
        if(!exist(id)) {
            String message = "getOne() 메소드에서 요청하신 id(" + id + ")에 대한 Task에 접근할 수 없습니다.";
            throw new TaskNotFoundException(message);
        }

        return this.tasks.get(id);
    }

    public Task getLast() throws TaskNotFoundException {
        return getOne(nextId - 1);
    }

    public void remove(Long id) throws TaskNotFoundException {
        if(!exist(id)) {
            String message = "remove() 메소드에서 요청하신 id(" + id + ")에 대한 Task에 접근할 수 없습니다.";
            throw new TaskNotFoundException(message);
        }

        this.tasks.remove(id);
    }

    public void update(Long id, String title) throws TaskNotFoundException {
        Task updateTask = getOne(id);
        updateTask.setTitle(title);
    }

    private boolean exist(Long id) {
        return this.tasks.containsKey(id);
    }
}
