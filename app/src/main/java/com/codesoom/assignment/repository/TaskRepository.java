package com.codesoom.assignment.repository;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.web.TaskRequestDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class TaskRepository {
    private final static Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private static final AtomicLong id = new AtomicLong();

    /**
     * 모든 task를 리턴한다.
     *
     * @return 모든 task
     */
    public List<Task> findAll() {
        return tasks.values()
                .stream()
                .map(this::safeTask)
                .collect(Collectors.toList());
    }

    public Task safeTask(Task task) {
        return new Task(task.getId(), task.getTitle());
    }

    /**
     * Task를 저장하고 저장한 task를 리턴한다.
     *
     * @param task 새로 저장할 task
     * @return 저장한 task
     */
    public Task save(TaskRequestDto task) {
        long newId = id.incrementAndGet();
        Task newTask = new Task(newId, task.getTitle());
        tasks.put(newId, newTask);
        return newTask;
    }

    /**
     * Task를 수정하고 수정한 task를 리턴한다.
     *
     * @param id 수정할 task의 id
     * @param task 수정할 task
     * @return 수정한 task
     */
    public Task update(Long id, TaskRequestDto task) {
        if (!isExist(id)) {
            throw new TaskNotFoundException();
        }
        Task updateTask = new Task(id, task.getTitle());
        tasks.put(id, updateTask);
        return updateTask;
    }

    /**
     * Task를 삭제한다.
     *
     * @param id 삭제할 task의 id
     */
    public void delete(Long id) {
        if (!isExist(id)) {
            throw new TaskNotFoundException();
        }
        tasks.remove(id);
    }

    /**
     * id로 Task를 찾아 리턴한다.
     *
     * @param id 찾을 task의 id
     * @return 찾은 task
     */
    public Task findById(Long id) {
        if (!isExist(id)) {
            throw new TaskNotFoundException();
        }
        return tasks.get(id);
    }

    /**
     * id로 Task가 존재하는지 여부를 리턴한다.
     *
     * @param id 존재하는지 확인할 task의 id
     * @return 존재하면 true, 그렇지 않으면 false
     */
    public boolean isExist(Long id) {
        return tasks.containsKey(id);
    }
}
