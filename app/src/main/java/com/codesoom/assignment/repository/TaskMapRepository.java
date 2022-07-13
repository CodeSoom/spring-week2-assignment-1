package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * TaskRepository의 Map 구현입니다.
 */
@Repository
public class TaskMapRepository implements TaskRepository {
    private Long id = 0L;
    private final Map<Long, Task> taskMap = new HashMap<>();

    /**
     * 요청된 숫자 형식의 taskId와 같은 id를 가진 Task를 찾아 리턴합니다.
     *
     * @param taskId 요청된 숫자 형식의 taskId
     * @return taskId와 같은 id를 가진 Task 리턴
     */
    public Optional<Task> get(Long taskId) {
        return Optional.ofNullable(taskMap.get(taskId));
    }

    /**
     * 현재 저장된 Task 저장소를 List 타입으로 변환하여 리턴한다.
     *
     * @return 변환된 List 타입의 Task 저장소
     */
    public List<Task> getAll() {
        return new ArrayList<>(taskMap.values());
    }

    /**
     * 입력 받은 title을 가진 Task를 생성해서 리턴합니다.
     *
     * @param title 입력 받은 title
     * @return title을 가진 Task 생성 후 리턴
     */
    public Task add(String title) {
        Task task = new Task(id, title);
        taskMap.put(id++, task);
        return task;
    }

    /**
     * 입력 받은 숫자 형식의 taskId와 같은 id를 가진 Task를 찾아 있으면 제거합니다.
     *
     * @param taskId 입력 받은 숫자 형식의 taskId
     */
    public void remove(Long taskId) {
        taskMap.remove(taskId);
    }
}
