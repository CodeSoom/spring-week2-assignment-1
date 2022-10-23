package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface TaskRepository {

    Collection<Task> findAllTasks();

    Optional<Task> findById(Long id);

    Task addTask(Task task);

    Optional<Task> deleteById(Long id);

    Set<Task> deleteTasks(Set<Long> idList);

    Collection<Task> findRecentlyAddedTasks(int quantity);

    int getQuantityStored();
}
