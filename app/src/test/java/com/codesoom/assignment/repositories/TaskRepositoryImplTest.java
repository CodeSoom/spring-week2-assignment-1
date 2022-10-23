package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.*;

class TaskRepositoryImplTest {

    @Test
    void findRecentlyAddedTasks() {
        TaskRepository taskRepository = new TaskRepositoryImpl();

        LongStream.rangeClosed(1L, 300L).boxed()
                .map(id -> new TaskDto().setId(id).setTitle("To Do" + id).createNewTask())
                .forEach(taskRepository::addTask);

        assertDoesNotThrow(() -> taskRepository.findRecentlyAddedTasks(250));
        assertDoesNotThrow(() -> taskRepository.findRecentlyAddedTasks(300));
        assertThrows(IllegalArgumentException.class, () -> taskRepository.findRecentlyAddedTasks(400));

        Set<Long> idSet = new Random().longs(10, 1L, 301L)
                .boxed()
                .collect(Collectors.toSet());

        idSet.forEach(taskRepository::deleteById);

        Collection<Task> recentlyAddedTasks = taskRepository.findRecentlyAddedTasks(taskRepository.getQuantityStored() - idSet.size());
        recentlyAddedTasks.stream()
                .map(Task::getId)
                .forEach(id -> assertFalse(idSet.contains(id)));
    }

    @Test
    void deleteTasks() {
        TaskRepository taskRepository = new TaskRepositoryImpl();
        Task task1 = new TaskDto().setId(1L).setTitle("study").createNewTask();
        Task task2 = new TaskDto().setId(2L).setTitle("play").createNewTask();

        taskRepository.addTask(task1);
        taskRepository.addTask(task2);

        List<Long> idList = new ArrayList<>();
        idList.add(1L);
        idList.add(2L);
        idList.add(1L);
        idList.add(3L);

        Set<Task> removedTasks = taskRepository.deleteTasks(new HashSet<>(idList));

        assertEquals(2, removedTasks.size());
        assertTrue(removedTasks.contains(task1));
        assertTrue(removedTasks.contains(task2));
        assertFalse(removedTasks.contains(null));
    }
}