package com.codesoom.assignment.task.repository;

import com.codesoom.assignment.task.model.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TaskRepository {

    Task findById(final long id);

    Set<Task> findAll();

    Task save(final Task task);

    Task updateById(final long id, final Task task);

    void deleteById(final long id);

}
