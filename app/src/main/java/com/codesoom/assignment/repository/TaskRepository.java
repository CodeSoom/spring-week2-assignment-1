package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 *  {@link Task} 저장소
 */
@Repository
public class TaskRepository {

    private long sequenceId = 0;

    private final Map<Long, Task> tasks = new HashMap<>();

    /**
     * {@link Task}를 저장합니다.
     * @param task 저장할 {@link Task}
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
     * 모든 {@link Task} 를 리턴합니다.
     */
    public Map<Long, Task> findAll() {
        return tasks;
    }

    /**
     * 주어진 {@link Task#id} 로 {@link Task} 를 리턴합니다.
     * <p>
     * 일치하는 {@link Task} 객체가 없을 경우 {@code null} 을 리턴합니다.
     * @param id {@link Task#id}
     * @return 일치하는 {@link Task} 있다면 {@link Task}, 없다면 {@code null}
     */
    public Task findById(final Long id) {
        return tasks.get(id);
    }

    /**
     * 주어진 {@link Task#id} 와 일치하는 {@link Task} 를 제거합니다.
     * @param id {@link Task#id}
     */
    public void deleteById(final Long id) {
        tasks.remove(id);
    }
}
