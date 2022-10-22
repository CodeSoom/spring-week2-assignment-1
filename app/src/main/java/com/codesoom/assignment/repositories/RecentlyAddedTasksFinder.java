package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;

import java.util.Collection;
import java.util.Collections;
import java.util.NavigableMap;
import java.util.TreeMap;

public class RecentlyAddedTasksFinder {

    private final NavigableMap<Long, Task> originalMap;
    private final NavigableMap<Long, Task> recentlyAddedTasksMap = Collections.synchronizedNavigableMap(new TreeMap<>(Collections.reverseOrder()));

    public RecentlyAddedTasksFinder(NavigableMap<Long, Task> originalMap) {
        this.originalMap = originalMap;
    }

    public Collection<Task> findTasks(int quantity) {
        final int size = size();

        if (size == quantity) {
            return Collections.unmodifiableCollection(getAllTasks());
        }

        if (size < quantity) {
            final Long firstId = getFirstKey();
            fillRecentlyAddedTasksMap(firstId - 1, quantity - size);
            return Collections.unmodifiableCollection(getAllTasks());
        }

        Long id = getLastKey();
        NavigableMap<Long, Task> headMap = getHeadMap(id);
        while (headMap.size() > quantity) {
            id = recentlyAddedTasksMap.higherKey(id);
            headMap = getHeadMap(id);
        }

        return Collections.unmodifiableCollection(headMap.values());
    }

    private void fillRecentlyAddedTasksMap(long ceilingId, int quantity) {
        Long id = ceilingId - 1L;

        while (id > 0 && quantity > 0) {
            final Task taskToBeAdded = originalMap.get(id);

            if (taskToBeAdded == null) {
                id = originalMap.higherKey(id);
                continue;
            }

            recentlyAddedTasksMap.put(id, taskToBeAdded);
            id--;
            quantity--;
        }
    }

    private NavigableMap<Long, Task> getHeadMap(Long id) {
        return recentlyAddedTasksMap.headMap(id, true);
    }

    private Collection<Task> getAllTasks() {
        return recentlyAddedTasksMap.values();
    }

    private int size() {
        return recentlyAddedTasksMap.size();
    }

    private Long getFirstKey() {
        return recentlyAddedTasksMap.firstKey();
    }

    private Long getLastKey() {
        return recentlyAddedTasksMap.lastKey();
    }

}
