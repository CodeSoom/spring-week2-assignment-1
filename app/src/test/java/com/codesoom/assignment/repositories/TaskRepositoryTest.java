package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskRepositoryTest {

    TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository = new TaskRepository();
    }

    @Test
    void getEmptyTasks() {
        List<Task> tasks = taskRepository.findAll();

        assertEquals(0, tasks.size());
    }

}