package com.codesoom.assignment;

import com.codesoom.assignment.interfaces.LoadingRepository;
import com.codesoom.assignment.interfaces.ManipulatingRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.NoSuchElementException;

@Repository
public class TaskManipulatingRepository implements ManipulatingRepository {
    private final LoadingRepository loadingRepository;
    private final Map<Long, DefaultTask> tasks;
    private Long savedTaskId;
    private Long updatedTaskId;

    public TaskManipulatingRepository(LoadingRepository loadingRepository, Map<Long, DefaultTask> tasks) {
        this.loadingRepository = loadingRepository;
        this.tasks = tasks;
    }

    @Override
    public void save(final DefaultTask task) {
        final DefaultTask savingTask = new DefaultTask(task.title());

        tasks.put(savingTask.id(), savingTask);
        savedTaskId = savingTask.id();
    }

    @Override
    public void update(final DefaultTask task) {
        final Long id = task.id();
        if (loadingRepository.notPresent(id)) {
            throw new NoSuchElementException("taskId(" + id + ")에 해당하는 Task를 Repository에서 찾을 수 없습니다");
        }

        tasks.put(id, task);
        updatedTaskId = id;
    }

    @Override
    public void deleteBy(final Long id) {
        if (loadingRepository.notPresent(id)) {
            throw new NoSuchElementException("taskId(" + id + ")에 해당하는 Task를 Repository에서 찾을 수 없습니다");
        }

        tasks.remove(id);
    }

    @Override
    public DefaultTask taskSaved() {
        return tasks.get(savedTaskId);
    }

    @Override
    public DefaultTask taskUpdated() {
        return tasks.get(updatedTaskId);
    }
}
