package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

@Repository
public class TaskRepositoryImpl extends CoreTaskRepository {

    private final NavigableMap<Long, Task> recentlyAddedTasksMap = Collections.synchronizedNavigableMap(new TreeMap<>(Collections.reverseOrder()));
    private final int cacheSize = 300;

    @Override
    public Task addTask(Task task) {
        if (getSize() == cacheSize) {
            recentlyAddedTasksMap.remove(recentlyAddedTasksMap.lastKey());
        }

        recentlyAddedTasksMap.put(task.getId(), task);
        return super.addTask(task);
    }

    @Override
    public Optional<Task> deleteById(Long id) {
        if (id < 0) {
            throw new IllegalArgumentException(String.format("%d: 0 또는 음수는 허용되지 않습니다.", id));
        }

        recentlyAddedTasksMap.remove(id);
        final Optional<Task> deletedTask = super.deleteById(id);

        fillRecentlyAddedTasksMap(cacheSize - getSize());

        return deletedTask;
    }

    @Override
    public Set<Task> deleteTasks(Set<Long> idSet) {
        idSet.forEach(recentlyAddedTasksMap::remove);
        final Set<Task> deleteTasks = super.deleteTasks(idSet);

        fillRecentlyAddedTasksMap(cacheSize - getSize());
        return deleteTasks;
    }

    @Override
    public List<Task> findRecentlyAddedTasks(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(String.format("%d: 0 또는 음수는 허용되지 않습니다.", quantity));
        }

        if (quantity == cacheSize) {
            return List.copyOf(recentlyAddedTasksMap.values());
        }

        if (quantity > cacheSize) {
            final int quantityStored = super.getQuantityStored();
            if (quantityStored < quantity) {
                throw new IllegalArgumentException(String.format("%d: 현재 저장된 task는 %d개입니다.", quantity, quantityStored));
            }

            final List<Task> list = new ArrayList<>(recentlyAddedTasksMap.values());
            list.addAll(getOlderTasks(quantity - cacheSize));
            return List.copyOf(list);
        }

        return List.copyOf(getHeadMapSmallerThan(quantity).values());
    }

    private NavigableMap<Long, Task> getHeadMapSmallerThan(int quantity) {
        Long id = recentlyAddedTasksMap.lastKey();
        NavigableMap<Long, Task> headMap = recentlyAddedTasksMap.headMap(id, true);

        while (headMap.size() > quantity) {
            id = recentlyAddedTasksMap.lowerKey(id);
            headMap = recentlyAddedTasksMap.headMap(id, true);
        }
        return headMap;
    }

    private void fillRecentlyAddedTasksMap(int quantity) {
        if (super.taskMap.isEmpty()) {
            return;
        }

        Long id = recentlyAddedTasksMap.isEmpty() ? super.taskMap.firstKey() : recentlyAddedTasksMap.lastKey();
        Map.Entry<Long, Task> entry = super.taskMap.higherEntry(id);

        while (entry!= null && quantity > 0) {
            Task task = entry.getValue();
            recentlyAddedTasksMap.put(task.getId(), task);
            quantity--;

            id = task.getId();
            entry = super.taskMap.higherEntry(id);
        }
    }

    private List<Task> getOlderTasks(int quantity) {
        final List<Task> list = new ArrayList<>();

        Long id = recentlyAddedTasksMap.lastKey();
        Map.Entry<Long, Task> entry = super.taskMap.higherEntry(id);

        while (entry!= null && quantity > 0) {
            Task task = entry.getValue();
            list.add(task);
            quantity--;

            id = task.getId();
            entry = super.taskMap.higherEntry(id);
        }

        return list;
    }

    private int getSize() {
        return recentlyAddedTasksMap.size();
    }
}
