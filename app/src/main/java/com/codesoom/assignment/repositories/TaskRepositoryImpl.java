package com.codesoom.assignment.repositories;

import com.codesoom.assignment.exceptions.NullTaskStoredException;
import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

@Repository
public class TaskRepositoryImpl extends CoreTaskRepository {

    private final NavigableMap<Long, Task> recentlyAddedTasksMap = Collections.synchronizedNavigableMap(new TreeMap<>(Collections.reverseOrder()));

    @Override
    public Task addTask(Task task) {
        recentlyAddedTasksMap.put(task.getId(), task);
        return super.addTask(task);
    }

    @Override
    public Optional<Task> deleteById(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException(String.format("%d: 0 또는 음수는 허용되지 않습니다.", id));
        }

        recentlyAddedTasksMap.remove(id);
        return super.deleteById(id);
    }

    @Override
    public Set<Task> deleteTasks(Set<Long> idSet) {
        idSet.forEach(recentlyAddedTasksMap::remove);
        return super.deleteTasks(idSet);
    }

    @Override
    public Collection<Task> findRecentlyAddedTasks(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(String.format("%d: 0 또는 음수는 허용되지 않습니다.", quantity));
        }

        final int size = recentlyAddedTasksMap.size();

        if (size == quantity) {
            return Collections.unmodifiableCollection(recentlyAddedTasksMap.values());
        }

        if (size < quantity) {
            final int quantityStored = super.getQuantityStored();
            if (quantityStored < quantity) {
                throw new IllegalArgumentException(String.format("%d: 현재 저장된 task는 %d개입니다.", quantity, quantityStored));
            }

            final Long firstId = recentlyAddedTasksMap.lastKey();
            fillRecentlyAddedTasksMap(firstId - 1, quantity - size);
            return Collections.unmodifiableCollection(recentlyAddedTasksMap.values());
        }

        Long id = recentlyAddedTasksMap.lastKey();
        NavigableMap<Long, Task> headMap = recentlyAddedTasksMap.headMap(id, true);
        while (headMap.size() > quantity) {
            id = recentlyAddedTasksMap.lowerKey(id);
            headMap = recentlyAddedTasksMap.headMap(id, true);
        }

        return Collections.unmodifiableCollection(headMap.values());
    }

    private void fillRecentlyAddedTasksMap(Long ceilingId, int quantity) {
        Long id = ceilingId;

        while (id != null && quantity > 0) {
            final Optional<Task> taskToBeAdded = findById(id);

            if (taskToBeAdded.isEmpty()) {
                throw new NullTaskStoredException(String.format("ID %d에 매핑된 Task 객체의 참조값이 null 입니다.", id));
            }

            recentlyAddedTasksMap.put(id, taskToBeAdded.get());
            id = super.getLowerKey(id);
            quantity--;
        }
    }
}
