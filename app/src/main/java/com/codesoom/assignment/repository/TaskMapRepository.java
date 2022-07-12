package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Task get(Long taskId) {
        return taskMap.get(taskId);
    }

    public List<Task> getAll() {
        return null;
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
