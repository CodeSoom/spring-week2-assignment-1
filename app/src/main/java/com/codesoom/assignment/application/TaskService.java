package com.codesoom.assignment.application;

import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private Long id = 0L;
    private Object lock = new Object(); //synchronized를 위한 객체 선언

    /**
     * Task List를 반환합니다.
     * @return tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * 찾고자 하는 Task를 반환합니다.
     * @param id Task의 id값
     * @return id에 해당하는 Task가 있다면 Task를 리턴하고, 없다면 TaskNotFoundExcetion을 던집니다.
     */
    public Task getTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(TaskNotFoundException::new);
    }

    /**
     * Task 객체를 생성합니다.
     * @param task Task 객체
     * @return 생성된 Task 객체를 리턴합니다.
     */
    public Task createTask(Task task) {
        task.setTitle(task.getTitle());
        task.setId(generateId());
        tasks.add(task);

        return task;
    }

    /**
     * Task 객체를 수정합니다.
     * @param id Task 객체의 id값
     * @param task Task 객체
     * @return id에 해당하는 Task가 있다면 수정된 Task를 리턴하고, 없다면 TaskNotFoundExcetion을 던집니다.
     */
    public Task updateTask(Long id, Task task) {
        Task filteredTask = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(TaskNotFoundException::new);

        filteredTask.setTitle(task.getTitle());

        return filteredTask;
    }

    /**
     * Task 객체를 제거합니다.
     * @param id Task 객체의 id값
     */
    public void deleteTask(Long id) {
        Task filteredTask = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(TaskNotFoundException::new); // = () -> new TaskNotFoundException()

        tasks.remove(filteredTask);
    }

    /**
     * Task를 생성할 때 Task의 id를 증가시킵니다.
     * @return 증가된 id
     */
    private Long generateId() {
        synchronized(lock) {
            id += 1;
        }
        return id;
    }
}
