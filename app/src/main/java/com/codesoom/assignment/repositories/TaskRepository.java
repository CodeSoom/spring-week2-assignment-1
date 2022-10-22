package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface TaskRepository {

    Collection<Task> findAllTasks();

    Collection<Task> findRecentlyAddedTasks(int quantity);

    Optional<Task> findById(Long id);

    Task addTask(Task task);

    Optional<Task> deleteById(Long id);

    Set<Task> deleteTasks(Set<Long> idList);
}
