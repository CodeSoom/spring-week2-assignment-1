package com.codesoom.assignment.repository;

import com.codesoom.assignment.exception.TaskBadRequestException;
import com.codesoom.assignment.models.Task;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    public List<Task> findAll() {
        return tasks;
    }

    /**
     *
     * @param id {@code Task} 객체를 찾기위해 {@link Long} 인 id를 받아서 찾습니다.
     * @return {@link Optional}인 {@code Task} 객체를 반환합니다.
     */
    public Optional<Task> findOneById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    /**
     * {@code Task} 객체를 생성하고 {@link ArrayList} 인 {@code tasks} 에
     * 추가 한다.
     * @param title Task 객체의 title field에 들어가는 parameter
     * @return 생성된 Task 객체를 반환
     * @exception TaskBadRequestException title이 null 이거나 isBlank() 또는 isEmpty() 일때 발생
     */
    public Task create(String title) {
        Task task = new Task();
        task.setId(generateId());
        task.setTitle(title);
        tasks.add(task);
        return task;
    }

    /**
     * Task 객체를 업데이트 후 {@link ArrayList}인 tasks에 해당 index에 존재하는 객체를 업데이트한 객체로 대체한다.
     * @param task {@code Task} 객체
     * @param title {@code Task} 의 title field 값
     * @return 수정된 {@code Task} 객체를 반환
     */
    public Task update(Task task, String title) {
        int taskIndex = tasks.indexOf(task);
        task.setTitle(title);
        tasks.set(taskIndex, task);
        return task;
    }

    /**
     * {@code tasks} 에 있는 Task 객체를 삭제 한다.
     * @param task 삭제할 {@code Task} 객체
     */
    public void remove(Task task) {
        tasks.remove(task);
        return;
    }

    /**
     * <p>동시성을 위한 synchronized를 사용하여 <br>
     * 해당 block은 한 번에 하나의 스레드만 실행할수 있도록 함.</p>
     * @return 하나의 스레드에서 처리된 newId에 + 1 을 한 newId를 반환
     */
    private synchronized Long generateId() {
        newId += 1;
        return newId;
    }
}
