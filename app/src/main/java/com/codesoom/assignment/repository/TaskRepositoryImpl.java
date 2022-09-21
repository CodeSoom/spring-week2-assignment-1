package com.codesoom.assignment.repository;

import com.codesoom.assignment.error.TaskNullException;
import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskRepositoryImpl implements TaskRepository{
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    public List<Task> findAll(){
        return new ArrayList<>(tasks);
    }
    public synchronized Long generateId() {
        newId += 1;
        return newId;
    }

    public Task taskFindId(Long id) {
        Task task =tasks.stream().filter(i-> Objects.equals(i.getId(), id))
                .findAny()
                .orElseThrow(TaskNullException::new);
        return task;
    }

    public Task createTask(Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    public Task updateTask(Long id, Task task){
        Task findTask = taskFindId(id);
        findTask.setTitle(task.getTitle());
        return findTask;
    }

    public void deleteTask(Long id){
        tasks.remove(taskFindId(id));
    }


}
