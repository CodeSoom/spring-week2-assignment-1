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

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(TaskNotFoundException::new);
    }

    public Task createTask(Task task) {
        task.setTitle(task.getTitle());
        task.setId(generateId());
        tasks.add(task);

        return task;
    }

    public Task updateTask(Long id, Task task) {
        //NPE 발생 가능
        if (task.getTitle().isEmpty()) {
            throw new NullPointerException("Title을 입력해주세요.");
        }

        Task filteredTask = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(TaskNotFoundException::new);

        filteredTask.setTitle(task.getTitle());

        return filteredTask;
    }

    public void deleteTask(Long id) {
        Task filteredTask = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(TaskNotFoundException::new); // = () -> new TaskNotFoundException()

        tasks.remove(filteredTask);
    }


    /**
     * id 1씩 증가
     * @return 1 증가된 id
     */
    private Long generateId() {
        synchronized(lock) {
            id += 1;
        }
        return id;
    }
}
