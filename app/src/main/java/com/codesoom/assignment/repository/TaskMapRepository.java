package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 작업과 관련된 컬렉션 기능을 제공하는 역할을 가지고 있습니다.
 *
 * 사용자의 요청에 따라 작업을 생성, 조회, 삭제 기능을 수행해야 하는 책임을 가지고 있습니다.
 */
@Repository
public class TaskMapRepository implements TaskRepository {
    private Long id = 0L;
    private final Map<Long, Task> taskMap = new ConcurrentHashMap<>();

    /**
     * 요청된 받은 숫자 타입 taskId와 같은 id를 가진 작업을 리턴합니다. 이 작업은 null일 수 있습니다.
     *
     * @param taskId 요청된 숫자 타입의 taskId
     * @return null이 아닌 값을 포함하거나 포함하지 않을 수 있는 작업 리턴
     */
    public Optional<Task> get(Long taskId) {
        return Optional.ofNullable(taskMap.get(taskId));
    }

    /**
     * 현재 저장된 작업 목록을 리턴합니다.
     *
     * @return 작업 목록 리턴
     */
    public List<Task> getAll() {
        return new ArrayList<>(taskMap.values());
    }

    /**
     * 입력 받은 title을 가진 작업을 생성해서 리턴합니다.
     *
     * @param title 입력 받은 title
     * @return title을 가진 작업 생성 후 리턴
     */
    public Task add(String title) {
        Task task = new Task(id, title);
        taskMap.put(id++, task);
        return task;
    }

    /**
     * 입력 받은 숫자 타입의 taskId와 같은 id를 가진 작업 찾아 있으면 제거합니다.
     *
     * @param taskId 입력 받은 숫자 타입의 taskId
     */
    public void remove(Long taskId) {
        taskMap.remove(taskId);
    }
}
