package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TaskRepository {
    public final static Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private static final AtomicLong id = new AtomicLong();

    /**
     * 모든 task를 리턴한다.
     *
     * @return 모든 task
     */
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }


    /**
     * Task를 저장하고 저장한 task를 리턴한다.
     *
     * @param task 새로 저장할 task
     * @return 저장한 task
     */
    public Task save(Task task) {
        task.setId(id.incrementAndGet());
        tasks.put(task.getId(), task);
        return task;
    }

    /**
     * Task를 수정하고 수정한 task를 리턴한다.
     *
     * @param task 수정할 task
     * @return 수정한 task
     */
    public Task update(Task task) {
        Task findOne = findById(task.getId());
        findOne.setTitle(task.getTitle());
        return findOne;
    }

    /**
     * Task를 삭제한다.
     *
     * @param id 삭제할 task의 id
     */
    public void delete(Long id) {
        tasks.remove(id);
    }

    /**
     * id로 Task를 찾아 리턴한다.
     *
     * @param id 찾을 task의 id
     * @return 찾은 task
     */
    public Task findById(Long id) {
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
