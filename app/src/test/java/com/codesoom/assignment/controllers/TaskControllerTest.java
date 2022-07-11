package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // https://stackoverflow.com/a/37246354
public class TaskControllerTest {
    @Autowired
    private TaskController controller;

    @Test
    @DisplayName("tasks가 비어있을 떄, task 목록을 요청하면 empty list 반환")
    public void givenEmptyTasks_whenGetTasks_thenReturnEmptyList() {
        // when
        List<Task> tasks = controller.getTasks();

        // then
        assertTrue(tasks.isEmpty());
    }

    @Test
    @DisplayName("tasks가 등록되어 있을 떄, task 목록을 요청하면 tasks list 반환")
    public void givenSomeTasks_whenGetTasks_thenReturnTasksList() {
        // given
        Task task1 = new Task();
        task1.setTitle("title1");
        Task task2 = new Task();
        task2.setTitle("title2");
        controller.createTask(task1);
        controller.createTask(task2);

        // when
        List<Task> tasks = controller.getTasks();

        // then
        assertEquals(2, tasks.size());
        assertEquals("title1", tasks.get(0).getTitle());
        assertEquals("title2", tasks.get(1).getTitle());
    }
}
