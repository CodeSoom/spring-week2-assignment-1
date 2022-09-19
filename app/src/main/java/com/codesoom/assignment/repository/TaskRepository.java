package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TaskRepository {

    private Long sequence = 0L;
    private final Map<Long, Task> taskStorage = new ConcurrentHashMap<>();

    /**
     * 새로운 Task 아이디를 생성해 반환합니다.
     * @return Task 아이디
     */
    private synchronized Long generateId() {
        sequence += 1;
        return sequence;
    }

    /**
     * 저장된 모든 Task를 반환합니다.
     * @return Task 목록
     */
    public List<Task> findAll() {
        return new ArrayList<>(taskStorage.values());
    }

    /**
     * 아이디에 해당하는 Task를 찾아 반환합니다.
     * @param id 조회하려는 Task 아이디
     * @return 조회된 Task
     */
    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(taskStorage.get(id));
    }

    /**
     * 새로운 Task를 생성하고 반환합니다.
     * @param task 생성할 Task
     * @return 새로 생성된 Task
     */
    public Task create(Task task) {
        final Long id = generateId();
        task.setId(id);
        taskStorage.put(id, task);
        return task;
    }

    /**
     * 기존의 Task를 아이디로 찾아 수정합니다.
     * @param id 수정할 Task 아이디
     * @param task 수정할 Task
     * @return 수정 완료된 Task
     */
    public Task update(Long id, Task task) {
        Task foundTask = findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "에 해당하는 Task가 없어 업데이트를 수행할 수 없습니다"));

        foundTask.setTitle(task.getTitle());
        return foundTask;
    }

    /**
     * 아이디에 해당하는 Task를 찾아 삭제합니다.
     * @param id 삭제할 Task 아이디
     * @return 삭제한 Task
     */
    public Optional<Task> deleteById(Long id) {
        return Optional.ofNullable(taskStorage.remove(id));
    }
}
