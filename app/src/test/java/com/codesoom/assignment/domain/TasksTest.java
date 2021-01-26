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
        task = new Task();
    }

    @DisplayName("객체를 추가했을때 컬렉션에 값이 저장된다")
    @Test
    void addTask() {
        tasks.addTask(task);

        Task task = tasks.findTask(1L)
                .orElse(null);

        assertEquals(1, tasks.size());
        assertEquals(1L, task.getId());
    }

    @DisplayName("객체를 제거했을때 컬렉션에 값이 없어진다.")
    @Test
    void remove() {
        tasks.addTask(task);

        tasks.remove(task);

        Task task = tasks.findTask(1L)
                .orElse(null);
        assertNull(task);
        assertEquals(0,tasks.size());
    }
}
