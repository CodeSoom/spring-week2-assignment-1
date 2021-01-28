package com.codesoom.assignment.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @DisplayName("task 객체를 생성할 수 있다")
    @Test
    void canCreate() {
        Task task = new Task(1L, "homework");

        assertEquals(task.getId(), 1L);
        assertEquals(task.getTitle(), "homework");
    }
}
