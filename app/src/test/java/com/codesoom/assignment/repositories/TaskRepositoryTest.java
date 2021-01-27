package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskRepositoryTest {

    TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository = new TaskRepository();
    }

    @AfterEach
    void afterEach() {
        taskRepository.removeAll();
    }

    @Test
    void getEmptyTasks() {
        List<Task> tasks = taskRepository.findAll();

        assertEquals(0, tasks.size());
    }

    @Test
    void getTasks() {
        Task task1 = new Task(1L, "task1");
        Task task2 = new Task(2L, "task2");

        taskRepository.save(task1);
        taskRepository.save(task2);
        List<Task> tasks = taskRepository.findAll();

        assertEquals(2, tasks.size());
    }

    @Test
    void addTask() {
        Task task1 = new Task(1L, "task1");

        taskRepository.save(task1);
        List<Task> tasks = taskRepository.findAll();

        assertEquals(1, tasks.size());
    }

    @Test
    void getTask() {
        Task task1 = new Task(1L, "task1");

        taskRepository.save(task1);
        Optional<Task> task = taskRepository.findOne(task1.getId());

        assertEquals(task1, task.get());
    }

    @Test
    void getNotExistedTask() {
        Long notExistedId = 1L;

        Optional<Task> task = taskRepository.findOne(notExistedId);

        assertFalse(task.isPresent());
    }

    @Test
    void removeTask() {
        Task task1 = new Task(1L, "task1");

        taskRepository.save(task1);
        taskRepository.delete(task1.getId());
        Optional<Task> task = taskRepository.findOne(task1.getId());

        assertTrue(task.isEmpty());
    }

}
