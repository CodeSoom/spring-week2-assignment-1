package com.codesoom.assignment;

import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Task를 추가, 삭제, 수정, 조회 합니다
 * 도메인 Layer와 데이터 접근 Layer 사이의 Mediator 역할을 합니다
 * 현재는 in-memory 방식으로 데이터를 관리합니다
 */
public class TaskRepository {
    private final List<Task> tasks = Collections.synchronizedList(new ArrayList<>());
    private Long newId = 0L;

    /**
     * Task Id로 Task를 조회합니다
     * @param taskId Task Id
     * @return 조회된 Task
     */
    public Optional<Task> getTaskById(Long taskId) {
        return tasks
                .stream()
                .filter(task -> task.getId().equals(taskId))
                .findFirst();
    }

    /**
     * Task를 저장소에 추가합니다
     * @param task 저장소에 추가할 task
     */
    public void addTask(Task task) {
        task.setId(generateId());
        tasks.add(task);
    }

    /**
     * Task를 저장소에서 제거합니다
     * @param targetTask 저장소에서 제거할 task
     */
    public void deleteTask(Task targetTask) {
        tasks.removeIf(task -> task.getId().equals(targetTask.getId()));
    }

    /**
     * Task 목록을 가져옵니다
     * @return Task 목록
     */
    public List<Task> getTasks() {
        return tasks;
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }
}
