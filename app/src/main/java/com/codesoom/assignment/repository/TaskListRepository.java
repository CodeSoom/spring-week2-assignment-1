package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskRepository의 List 구현입니다.
 */
public class TaskListRepository implements TaskRepository {
    private Long id = 0L;
    private final List<Task> taskList = new ArrayList<>();

    /**
     * 입력 받은 taskId와 같은 id를 가진 Task를 순차적으로 찾아 있다면 해당 Task만 리턴하고, 아니면 null을 리턴한다.
     *
     * @param taskId 요청된 숫자 형식의 taskId
     * @return taskId와 같은 id의 Task가 있다면 해당 Task 리턴, 없으면 null 리턴
     */
    @Override
    public Task get(Long taskId) {
        return taskList.stream()
                .filter(t -> t.isSame(taskId))
                .findFirst()
                .orElse(null);
    }

    public List<Task> getAll() {
        return taskList;
    }

    @Override
    public Task add(String title) {
        Task task = new Task(id++, title);
        taskList.add(task);
        return task;
    }

    @Override
    public void remove(Long taskId) {
        taskList.remove(get(taskId));
    }
}
