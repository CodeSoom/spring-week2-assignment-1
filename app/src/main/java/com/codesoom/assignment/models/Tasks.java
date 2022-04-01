package com.codesoom.assignment.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Tasks {
    private final List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    public List<Task> readAll() {
        return tasks;
    }

    public Task read(Long id) {
        int index = findIndexById(id);
        return tasks.get(index);
    }

    public Task create(Task task) {
        if (task.getTitle().isBlank()) {
            // TODO error...
        }

        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    public Task update(Long id, String title) {
        int index = findIndexById(id);
        Task task = new Task(id, title);
        tasks.set(index, task);
        return task;
    }

    public void delete(Long id) {
        int index = findIndexById(id);
        tasks.remove(index);
    }

    /**
     * 새로운 Id를 생성하여 반환합니다.
     * @return 새로운 Id
     */
    private Long generateId() {
        newId += 1;
        return newId;
    }

    /**
     * 식별자로 할 일의 인덱스를 찾습니다.
     * @param id 식별자
     * @return 발견된 인덱스
     */
    private int findIndexById(Long id) {
        return IntStream.range(0, tasks.size())
                .filter(i -> tasks.get(i).getId().equals(id))
                .findFirst()
                .orElseThrow();
    }
}
