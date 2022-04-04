package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskRepositoryTest {
    private List<Task> tasks = new ArrayList<>();

    @AfterEach
    void tearDown() throws Exception {
        tasks = new ArrayList<>();
    }

    @Test
    void 테스크생성() throws Exception {
        // given
        Task newTask1 = new Task();
        newTask1.setId(1L);
        newTask1.setTitle("Title1");
        tasks.add(newTask1);

        Task newTask2 = new Task();
        newTask2.setId(2L);
        newTask2.setTitle("Title2");
        tasks.add(newTask2);
        // when
        Task foundTask = tasks.stream()
                .filter(task -> task.getId().equals(1L))
                .findFirst()
                .orElse(new Task());

        // then
        assertEquals(newTask1.getId(), 1L);
        assertEquals(newTask1.getTitle(), "Title1");
        assertEquals(newTask1, foundTask);
        assertNotEquals(newTask1, newTask2);
        assertNotEquals(newTask1.getId(), 2L);
    }

    @Test
    void 테스트삭제() throws Exception {
        // given
        Task newTask1 = new Task();
        newTask1.setId(1L);
        newTask1.setTitle("Title1");
        tasks.add(newTask1);

        Task newTask2 = new Task();
        newTask2.setId(2L);
        newTask2.setTitle("Title2");
        tasks.add(newTask2);

        Task newTask3 = new Task();
        newTask3.setId(3L);
        tasks.add(newTask3);

        // when
        tasks.remove(newTask1);

        // then
        assertEquals(tasks.size(), 2);
        assertEquals(tasks.get(0), newTask2);
        assertTrue(newTask2.hasTitle());
        assertFalse(newTask3.hasTitle());
    }
}