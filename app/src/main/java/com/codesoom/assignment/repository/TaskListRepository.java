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
     * 주어진 식별자와 같은 작업을 리턴한다.
     *
     * @param taskId 식별자
     * @return 식별자와 같은 작업 리턴
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
