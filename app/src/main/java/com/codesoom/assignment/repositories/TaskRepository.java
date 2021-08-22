package com.codesoom.assignment.repositories;

import com.codesoom.assignment.errors.TaskIdNotFoundException;
import com.codesoom.assignment.models.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
     * 식별자와 할 일을 받아 저장합니다.
     *
     * @param id 저장할 식별자
     * @param task 저장할 할 일
     */
    public void save(Long id, Task task) {
        taskMap.put(id, task);
    }

    /**
     * 모든 할 일을 리턴합니다.
     *
     * @return 할 일 목록
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(taskMap.values());
    }

    /**
     * 식별자에 해당하는 할 일을 찾아 리턴합니다.
     *
     * @param id 찾을 할 일 식별자
     * @return 찾은 할 일
     */
    public Task get(Long id) {
        return findWith(id);
    }

    /**
     * 식별자로 해당 할 일을 찾고, 제목을 변경합니다.
     *
     * @param id 찾을 할 일 식별자
     * @param title 변경할 할 일 제목
     * @return 변경된 할 일
     */
    public Task update(Long id, String title) {
        Task task = findWith(id);

        task.setTitle(title);

        return task;
    }

    /**
     * 식별자로 할 일을 찾고, 삭제합니다.
     *
     * @param id 삭제할 식별자
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
