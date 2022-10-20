package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TaskRepositoryImplTest {

    @Test
    void findRecentlyAddedTasks() {
        TaskRepository taskRepository = new TaskRepositoryImpl();

        Long[] idList = new Long[]{1L, 3L, 5L, 7L};

        Arrays.stream(idList)
                .map(id -> new TaskDto().setId(id).setTitle("play").createNewTask())
                .forEach(taskRepository::addTask);

        List<Task> recentlyAddedTasks = taskRepository.findRecentlyAddedTasks();
        assertEquals(7L, recentlyAddedTasks.get(0).getId());
        assertEquals(5L, recentlyAddedTasks.get(1).getId());
        assertEquals(3L, recentlyAddedTasks.get(2).getId());
        assertEquals(1L, recentlyAddedTasks.get(3).getId());
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

        Map<Long, Task> removedTasks = taskRepository.deleteTasks(new HashSet<>(idList));

        assertEquals(2, removedTasks.size());
        assertTrue(removedTasks.containsKey(1L));
        assertTrue(removedTasks.containsKey(2L));
        assertFalse(removedTasks.containsKey(3L));
        assertEquals("study", removedTasks.get(1L).getTitle());
        assertEquals("play", removedTasks.get(2L).getTitle());
        assertNull(removedTasks.get(3L));
    }
}