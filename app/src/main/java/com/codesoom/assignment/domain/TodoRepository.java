package com.codesoom.assignment.domain;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Task를 컬렉션으로 관리하며 조회,저장,수정,삭제하는 클래스입니다.
 */
@Component
public class TodoRepository {
    private static final Map<Long, Task> store = new ConcurrentHashMap<>();
    private static Long sequence = 0L;

    private final IdGenerator idGenerator;

    public TodoRepository(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public synchronized Task save(Task task) {
        if (isNewTask(task)) {
            final Task newTask = new Task(generateId(), task.getTitle());
            store.put(newTask.getId(), newTask);

            return newTask;
        }
        store.put(task.getId(), task);
        return task;
    }

    private synchronized Long generateId() {
        sequence = idGenerator.generate(sequence);
        return sequence;
    }

    private boolean isNewTask(Task task) {
        return task.getId() == null || !store.containsKey(task.getId());
    }

    public void delete(Task task) {
        if (store.containsValue(task)) {
            store.remove(task.getId());
        }
    }

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Task> findAll() {
        return new ArrayList<>(store.values());
    }

    public void deleteById(Long id) {
        if (!store.containsKey(id)) {
            throw new TaskNotFoundException(id);
        }

        store.remove(id);
    }
}
