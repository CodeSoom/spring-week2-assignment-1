package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Task를 생성, 삭제, 조회하는 저장소입니다.
 */
@Repository
public class TaskRepository {
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

    /**
     * 입력 받은 title을 가진 Task를 생성해서 리턴한다.
     *
     * @param title 입력 받은 title
     * @return title을 가진 Task 생성 후 리턴
     */
    public Task add(String title) {
        return taskMap.put(id, new Task(id++, title));
    }

    /**
     * 입력 받은 숫자 형식의 taskId와 같은 id를 가진 Task를 찾아 있으면 제거한다.
     *
     * @param taskId 입력 받은 숫자 형식의 taskId
     */
    public void remove(Long taskId) {
        taskMap.remove(taskId);
    }
}
