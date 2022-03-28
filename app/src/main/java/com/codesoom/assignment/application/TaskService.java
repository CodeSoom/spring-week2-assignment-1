package com.codesoom.assignment.application;

import com.codesoom.assignment.dto.TaskDTO;
import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TaskService {

    private List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTask(Long id) {
        Task task = getOneTaskById(id)
                .orElseThrow(() -> { throw new NoSuchElementException("ID [" + id + "]에 해당하는 Task를 찾을 수 없어, Task를 조회할 수 없습니다."); });
        return task;
    }

    public Task createNewTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(generateId());
        task.setTitle(taskDTO.getTitle());
        tasks.add(task);
        return task;
    }

    public Task modifyTaskById(TaskDTO taskDTO) {
        Task findTask = getOneTaskById(taskDTO.getId())
                .orElseThrow(() -> { throw new NoSuchElementException("ID [" + taskDTO.getId() + "]에 해당하는 Task를 찾을 수 없어, Task를 수정할 수 없습니다."); });
        int findTaskIndex = tasks.indexOf(findTask);
        findTask.setTitle(taskDTO.getTitle());
        tasks.set(findTaskIndex, findTask);
        return findTask;
    }

    public Task deleteTaskById(Long id) {
        Task findTask = getOneTaskById(id)
                .orElseThrow(() -> { throw new NoSuchElementException("ID [" + id + "]에 해당하는 Task를 찾을 수 없어, Task를 삭제할 수 없습니다."); });
        tasks.remove(findTask);
        return findTask;
    }

    private Optional<Task> getOneTaskById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    private Long generateId() {
        if (tasks.isEmpty()) {
            return 1L;
        }
        return tasks.get(tasks.size() - 1).getId() + 1L;
    }

}
