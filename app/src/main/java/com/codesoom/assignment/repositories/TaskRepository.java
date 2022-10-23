package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TaskRepository {

    List<Task> findAllTasks();

    Optional<Task> findById(Long id);

    Task addTask(Task task);

    Optional<Task> deleteById(Long id);

    Set<Task> deleteTasks(Set<Long> idList);

    List<Task> findRecentlyAddedTasks(int quantity);

    int getQuantityStored();
}
