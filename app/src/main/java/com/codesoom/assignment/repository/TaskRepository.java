package com.codesoom.assignment.repository;

import com.codesoom.assignment.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Optional;


@Repository
public class TaskRepository {

    private Long lastId = 0L;
    private final Map<Long, Task> tasks = new LinkedHashMap<>();

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    /**
     * task를 저장합니다.
     * @param task 저장할 task
     * @return 저장된 task 반환
     */
    public Task save(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    /**
     * task가 존재하는지 확인합니다.
     * @param id
     * @return 존재여부를 true, false로 반
     */
    public boolean existsById(Long id) {
        return tasks.containsKey(id);
    }

    /**
     * task를 삭제합니다.
     * @param id 삭제할 task의 id
     */
    public void deleteById(Long id) {
        tasks.remove(id);
    }

    /**
     * 할 일을 등록합니다.
     *
     * @param task 추가할 task
     * @return 새로 추가된 task
     */
    public Task addTask(Task task) {
        task.setId(increaseId());
        save(task);
        return task;
    }

    /**
     * task 생성에 쓰일 ID를 반환합니다.
     */
    private Long increaseId() {
        lastId += 1;
        return lastId;
    }
}
