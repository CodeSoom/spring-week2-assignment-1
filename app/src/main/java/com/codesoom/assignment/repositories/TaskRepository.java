package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 할 일들의 저장소
 *
 * @see Task
 */
@Repository
public class TaskRepository {

    private Map<Long, Task> taskMap = Collections.synchronizedMap(new LinkedHashMap<>());

    /**
     * 모든 할 일을 리턴합니다.
     */
    public List<Task> findAll() {
        return new ArrayList<>(taskMap.values());
    }

    /**
     * 주어진 할 일을 저장한 뒤, 저장된 할 일을 리턴합니다.
     *
     * @param task 저장 하고자 하는 할 일
     * @return 저장 된 할 일
     */
    public Task save(Task task) {
        taskMap.put(task.getId(), task);

        return task;
    }

    /**
     * 주어진 id에 해당하는 할 일을 찾는다면 할 일을 담은 Optional을 리턴합니다.
     * 찾지 못한다면 빈 Optional을 리턴합니다.
     *
     * @param id 찾고자 하는 할 일의 id
     * @return 주어진 id에 해당하는 할 일을 담은 Optional 또는 할 일이 존재하지 빈 Optional
     */
    public Optional<Task> findOne(Long id) {
        return Optional.ofNullable(taskMap.get(id));
    }

    /**
     * 주어진 id에 해당하는 할 일을 찾아 삭제합니다.
     *
     * @param id 삭제 하고자 하는 할 일의 id
     */
    public void delete(Long id) {
        taskMap.remove(id);
    }

    /**
     * 모든 할 일을 삭제합니다.
     */
    public void removeAll() {
        taskMap.clear();
    }

}
