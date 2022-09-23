package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 할 일의 저장 및 관리를 담당합니다.
 */
@Repository
public class TaskRepository {

    private Long sequence = 0L;
    private final Map<Long, Task> taskStorage = new ConcurrentHashMap<>();

    /**
     * 새로운 할 일 아이디를 생성해 반환합니다.
     * @return 할 일 아이디
     */
    private synchronized Long generateId() {
        sequence += 1;
        return sequence;
    }

    /**
     * 저장된 모든 할 일을 반환합니다.
     * @return 할 일 전체 목록
     */
    public List<Task> findAll() {
        return new ArrayList<>(taskStorage.values());
    }

    /**
     * 아이디에 해당하는 할 일을 찾아 반환합니다.
     * @param id 조회하려는 할 일 아이디
     * @return 조회된 할 일
     */
    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(taskStorage.get(id));
    }

    /**
     * 새로운 할 일을 생성하고 반환합니다.
     * @param task 생성할 할 일 데이터
     * @return 새로 생성된 할 일
     */
    public Task create(Task task) {
        final Long id = generateId();

        final Task createdTask = Task.builder()
                .id(id)
                .title(task.getTitle())
                .build();

        taskStorage.put(id, createdTask);
        return createdTask;
    }

    /**
     * 기존의 할 일을 아이디로 찾아 수정합니다.
     * @param id 수정할 할 일 아이디
     * @param task 수정할 할 일 데이터
     * @throws IllegalArgumentException 할 일을 찾지 못한 경우
     * @return 수정 완료된 할 일
     */
    public Task update(Long id, Task task) {
        final Task foundTask = findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "에 해당하는 할 일을 찾지 못해 업데이트를 수행할 수 없습니다"));

        final Task updatedTask = foundTask.withNewTitle(task.getTitle());
        taskStorage.put(id, updatedTask);
        return updatedTask;
    }

    /**
     * 아이디에 해당하는 할 일을 찾아 삭제합니다.
     * @param id 삭제할 할 일 아이디
     * @return 삭제된 할 일
     */
    public Optional<Task> deleteById(Long id) {
        return Optional.ofNullable(taskStorage.remove(id));
    }
}
