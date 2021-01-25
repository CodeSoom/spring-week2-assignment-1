package com.codesoom.assignment.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void canCreate() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("homework");

        assertEquals(task.getId(), 1L);
        assertEquals(task.getTitle(), "homework");
    }
}
