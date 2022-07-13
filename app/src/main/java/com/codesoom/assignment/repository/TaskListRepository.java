package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TaskRepository의 List 구현입니다.
 */
public class TaskListRepository implements TaskRepository {
    private Long id = 0L;
    private final List<Task> taskList = new ArrayList<>();

    /**
     * 입력 받은 taskId와 같은 id를 가진 요소를 순차적으로 찾아 존재하면 해당 요소만 리턴하고, 존재하지 않으면 null을 리턴한다.
     *
     * @param taskId 요청된 숫자 형식의 taskId
     * @return taskId와 같은 id의 요소가 존재하면 해당 요소 리턴, 존재하지 않으면 null 리턴
     */
    @Override
    public Optional<Task> get(Long taskId) {
        return taskList.stream()
                .filter(t -> t.isSame(taskId))
                .findFirst();
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

    /**
     * 숫자 타입의 taskId가 들어오고 해당 taskId와 같은 task가 존재할 때, 해당 Task를 찾아 삭제한다.
     *
     * @param taskId 입력 받은 숫자 형식의 taskId
     */
    @Override
    public void remove(Long taskId) {
        taskList.removeIf(t -> t.isSame(taskId));
    }
}
