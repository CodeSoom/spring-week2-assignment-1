package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 작업과 관련된 컬렉션 기능을 제공하는 역할을 가지고 있습니다.
 *
 * 사용자의 요청에 따라 작업을 생성, 조회, 삭제 기능을 수행해야 하는 책임을 가지고 있습니다.
 */
public class TaskListRepository implements TaskRepository {
    private Long id = 0L;
    private final List<Task> taskList = new CopyOnWriteArrayList<>();

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
     * 작업을 삭제합니다.
     *
     * @param taskId 입력 받은 숫자 형식의 taskId
     */
    @Override
    public void remove(Long taskId) {
        taskList.removeIf(t -> t.isSame(taskId));
    }
}
