package com.codesoom.assignment.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TasksTest {
    private Tasks tasks;
    private Task task;

    @BeforeEach
    void setUp() {
        tasks = new Tasks();
        task = new Task(1L, "homework");
    }

    @DisplayName("Tasks 객체에 Task를 추가하면 값이 저장되고 해당하는 값을 찾을 수 있다")
    @Test
    void addTask() {
        tasks.addTask(task);

        Task task = tasks.findTask(1L)
                .orElse(null);

        assertEquals(1, tasks.size());
        assertEquals(1L, task.getId());
    }

    @DisplayName("Tasks 객체에 Task를 제거하면 값이 사라지고 해당하는 값을 찾을 수 없다")
    @Test
    void remove() {
        tasks.addTask(task);

        tasks.remove(1L);

        Task task = tasks.findTask(1L)
                .orElse(null);
        assertNull(task);
        assertEquals(0, tasks.size());
    }
}
