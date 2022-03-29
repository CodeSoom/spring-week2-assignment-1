package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 *  Task 저장소
 */
@Repository
public class TaskRepository {

    private long sequenceId = 0;

    private final Map<Long, Task> tasks = new HashMap<>();

    /**
     * 주어진 Task 객체를 Task 저장소에 저장합니다.
     * @param task 저장할 Task 객체
     */
    public void save(final Task task) {
        Long taskId = generateId();
        task.setId(taskId);
        tasks.put(taskId, task);
    }

    /**
     * 유일(Unique) 한 아이디를 생성 합니다.
     * @return 고유 아이디
     */
    private Long generateId() {
        synchronized (this) {
            sequenceId = sequenceId + 1;
            return sequenceId;
        }
    }

    /**
     * 모든 Task 를 리턴합니다.
     */
    public Map<Long, Task> findAll() {
        return tasks;
    }

    /**
     * 주어진 Task.id 로 Task 를 리턴합니다.
     * <p>
     * 일치하는 Task 객체가 없을 경우 null 을 리턴합니다.
     * @param id Task 아이디
     * @return Task
     */
    public Task findById(final Long id) {
        return tasks.get(id);
    }

    /**
     * 주어진 Task.id 로 저장소에 Task 를 제거합니다.
     * @param id Task 아이디
     */
    public void deleteById(final Long id) {
        tasks.remove(id);
    }
}
