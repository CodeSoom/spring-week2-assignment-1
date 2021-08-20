package com.codesoom.assignment.repositories;

import com.codesoom.assignment.errors.TaskIdNotFoundException;
import com.codesoom.assignment.models.Task;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * 등록된 할 일들을 저장 및 관리합니다.
 */
@Repository
public class TaskRepository {

    private final Map<Long, Task> taskMap = new HashMap<>();

    /**
     * 번호와 할 일을 받아 저장합니다.
     *
     * @param id 저장할 번호
     * @param task 저장할 할 일
     */
    public void save(Long id, Task task) {
        taskMap.put(id, task);
    }

    /**
     * 모든 할 일을 찾습니다.
     *
     * @return 할 일 목록
     */
    public Collection<Task> getAllTasks() {
        return taskMap.values();
    }

    /**
     * 번호에 해당하는 할 일을 찾습니다.
     *
     * @param id 찾을 할 일 번호
     * @return Task
     */
    public Task get(Long id) {
        return findWith(id);
    }

    /**
     * 번호로 해당 할 일을 찾고, 제목을 변경합니다.
     *
     * @param id 찾을 할 일 번호
     * @param title 변경할 할 일 제목
     * @return Task
     */
    public Task update(Long id, String title) {
        Task task = findWith(id);

        task.setTitle(title);

        return task;
    }

    /**
     * 번호로 할 일을 찾고, 삭제합니다.
     *
     * @param id 삭제할 번호
     */
    public void delete(Long id) {
        findWith(id);

        taskMap.remove(id);
    }

    private Task findWith(Long id) {
        return Optional.ofNullable(taskMap.get(id))
            .orElseThrow(() -> new TaskIdNotFoundException(id));
    }
}
