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

    public TaskManipulatingRepository(LoadingRepository loadingRepository, Map<Long, DefaultTask> tasks) {
        this.loadingRepository = loadingRepository;
        this.tasks = tasks;
    }

    @Override
    public DefaultTask save(final DefaultTask task) {
        final DefaultTask savingTask = new DefaultTask(task.title());

        tasks.put(savingTask.id(), savingTask);
        return savingTask;
    }

    @Override
    public DefaultTask update(final DefaultTask task) {
        final Long id = task.id();
        if (loadingRepository.notPresent(id)) {
            throw new NoSuchElementException("taskId(" + id + ")에 해당하는 Task를 Repository에서 찾을 수 없습니다");
        }

        tasks.put(id, task);
        return task;
    }

    @Override
    public void deleteBy(final Long id) {
        if (loadingRepository.notPresent(id)) {
            throw new NoSuchElementException("taskId(" + id + ")에 해당하는 Task를 Repository에서 찾을 수 없습니다");
        }

        tasks.remove(id);
    }
}
