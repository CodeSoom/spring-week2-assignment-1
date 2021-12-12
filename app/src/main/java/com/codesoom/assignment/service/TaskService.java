package com.codesoom.assignment.service;

import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.model.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskService {

    private Map<Long, Task> tasks = new LinkedHashMap<Long, Task>();
    private Long newId = 0L;

    public List<Task> list() {

        return new ArrayList<Task>(tasks.values());
    }

    public Task create(Task task) {
        Long id = generateId();
        task.setId(id);
        task.setState("yet");
        tasks.put(id, task);

        return task;
    }

    public Optional<Task> view(Long id) {
        Optional<Task> task = findTask(id);
        if (!task.isPresent()) {
            throw new TaskNotFoundException("요청하신 " + id + "의 Task가 없습니다.");
        }
        return task;
    }

    public Optional<Task> update(Task body, Long id) {
        Optional<Task> task = findTask(id);
        if (!task.isPresent()) {
            throw new TaskNotFoundException("요청하신 " + id + "의 Task가 없습니다.");
        }
        task.get().setTitle(body.getTitle());
        task.get().setState(body.getState());
        return task;
    }

    public void delete(Long id) {
        Optional<Task> task = findTask(id);
        if (!task.isPresent()) {
            throw new TaskNotFoundException("요청하신 " + id + "의 Task가 없습니다.");
        }
        tasks.remove(id);
    }

    private synchronized Long generateId() {
        newId += 1;
        return newId;
    }

    private Optional<Task> findTask(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id가 null이므로 Task를 찾을 수 없습니다.");
        }

        return Optional.ofNullable(tasks.get(id));
    }
}
