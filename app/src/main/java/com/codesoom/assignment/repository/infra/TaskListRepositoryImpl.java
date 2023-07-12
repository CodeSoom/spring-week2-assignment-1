package com.codesoom.assignment.repository.infra;

import com.codesoom.assignment.exception.custom.TaskNotFound;
import com.codesoom.assignment.models.domain.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TaskListRepositoryImpl implements TaskRepository {
    private ArrayList<Task> taskList = new ArrayList<>();
    private AtomicLong taskId = new AtomicLong(0L);

    @Override
    public Task save(Task task) {
        Task saveTask = new Task(generateId(), task.getTitle());
        taskList.add(saveTask);
        return saveTask;
    }

    @Override
    public List<Task> findAll() {
        return taskList;
    }

    @Override
    public Task findById(Long id) {
        return taskList.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(TaskNotFound::new);
    }

    @Override
    public void delete(Task task) {
        taskList.remove(task);
    }

    @Override
    public Task update(Task task) {
        Task findedTask = findById(task.getId());
        changeTask(findedTask, task.getTitle());
        return findedTask;
    }

    private void changeTask(Task task, String title) {
        task.changeTitle(title);
    }

    private Long generateId() {
        return taskId.getAndIncrement();
    }

}
