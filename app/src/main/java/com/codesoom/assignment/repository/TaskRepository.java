package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 *  해야할 일을 메모리에서 관리합니다.
 */
@Repository
public class TaskRepository {

    private long sequenceId = 0;

    private final Map<Long, Task> tasks = new HashMap<>();

    /**
     * 해야할 일을 저장합니다.
     * @param task 저장될 할일
     */
    public void save(final Task task) {
        Long taskId = generateId();
        task.setId(taskId);
        tasks.put(taskId, task);
    }

    /**
     * 유일(Unique)한 아이디를 생성합니다.
     * @return 고유 아이디
     */
    private Long generateId() {
        synchronized (this) {
            sequenceId = sequenceId + 1;
            return sequenceId;
        }
    }

    /**
     * 해야 할 일 목록을 리턴 합니다.
     */
    public Map<Long, Task> findAll() {
        return tasks;
    }

    /**
     * 주어진 아이디와 일치하는 해야 할 일을 리턴합니다.
     * <p>일치하는 할일이 없을 경우 {@code null} 을 리턴합니다.</p>
     * @param id 아이디
     * @return 일치하는 아이디가 있다면 엔티티, 없다면 {@code null}
     */
    public Task findById(final Long id) {
        return tasks.get(id);
    }

    /**
     * 주어진 아이디와 일치하는 해야 할 일을 제거합니다.
     * <p>주어진 아이디와 일치하는 데이터가 없다면 아무 일도 일어나지 않습니다.</p>
     * @param id 아이디
     */
    public void deleteById(final Long id) {
        tasks.remove(id);
    }
}
