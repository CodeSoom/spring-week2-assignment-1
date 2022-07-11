package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // https://stackoverflow.com/a/37246354
public class TaskControllerTest {
    @Autowired
    private TaskController controller;

    @Test
    @DisplayName("tasks가 비어있을 떄 > task 목록을 요청하면 empty list 반환")
    public void givenEmptyTasks_whenGetTasks_thenReturnEmptyList() {
        // when
        List<Task> tasks = controller.getTasks();

        // then
        assertTrue(tasks.isEmpty());
    }

    @Test
    @DisplayName("tasks가 등록되어 있을 떄 > task 목록을 요청하면 tasks list 반환")
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

    @Test
    @DisplayName("null Task로 create 요청 > badRequest 반환")
    public void whenCreateNullTask_thenReturnBadRequest() {
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            controller.createTask(null);
        });
    }

    @Test
    @DisplayName("task create 요청 > 생성된 task 반환")
    public void whenCreateTask_thenCreatedTask() {
        // when
        Task task = new Task();
        task.setTitle("title");
        Task actual = controller.createTask(task);

        // then
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals("title", actual.getTitle());
    }

    @Test
    @DisplayName("task가 비어있을 때 > 존재하지 않는 task의 id로 update 요청하면 > notFound 반환")
    public void givenEmptyTasks_whenUpdateTaskWithWrongId_thenReturnNotFound() {
        // when
        final Task task = new Task();
        ResponseEntity<Task> actual = controller.updateTask(0L, task);

        // then
        assertEquals(ResponseEntity.notFound().build(), actual);
    }

    @Test
    @DisplayName("task가 등록되어 있을 때 > 존재하지 않는 task의 id로 update 요청하면 > notFound 반환")
    public void givenSomeTasks_whenUpdateTaskWithWrongId_thenReturnNotFound() {
        // given
        Task task1 = new Task();
        task1.setTitle("title1");
        controller.createTask(task1);

        // when
        final Task task = new Task();
        ResponseEntity<Task> actual = controller.updateTask(0L, task);

        // then
        assertEquals(ResponseEntity.notFound().build(), actual);
    }

    @Test
    @DisplayName("task가 등록되어 있을 때 > 존재하는 task의 id, 새로운 task null인 상태로 update 요청하면 > badRequest 반환")
    public void givenSomeTasks_whenUpdateTaskWithNullNewTask_thenReturnNotFound() {
        // given
        Task task1 = new Task();
        task1.setTitle("title1");
        controller.createTask(task1);

        // when
        ResponseEntity<Task> actual = controller.updateTask(1L, null);

        // then
        assertEquals(ResponseEntity.badRequest().build(), actual);
    }

    @Test
    @DisplayName("task가 등록되어 있을 때 > 존재하는 task의 id, 새로운 task 입력된 상태로 update 요청하면 > update 성공")
    public void givenSomeTasks_whenUpdateTaskWithExistedIdAndNewTask_thenReturnNotFound() {
        // given
        Task task1 = new Task();
        task1.setTitle("title1");
        controller.createTask(task1);

        // when
        Task newTask = new Task();
        newTask.setTitle("newTitle");
        ResponseEntity<Task> actual = controller.updateTask(1L, newTask);

        // then
        assertEquals(HttpStatus.OK, actual.getStatusCode());

        Task actualBody = actual.getBody();
        assertNotNull(actualBody);
        assertEquals("newTitle", actualBody.getTitle());
    }

    @Test
    @DisplayName("task가 비어있을 때 > 존재하지 않는 task의 id로 delete 요청하면 > notFound 반환")
    public void givenEmptyTasks_whenDeleteTaskWithWrongId_thenReturnNotFound() {
        // when
        ResponseEntity<Void> actual = controller.deleteTask(0L);

        // then
        assertEquals(ResponseEntity.notFound().build(), actual);
    }

    @Test
    @DisplayName("task가 비어있을 때 > 존재하는 id로 delete 요청하면 > noContent 반환")
    public void givenEmptyTasks_whenDeleteTaskWithWrongId_thenReturnNoContent() {
        // given
        Task givenTask = new Task();
        givenTask.setTitle("title1");
        controller.createTask(givenTask);

        // when
        ResponseEntity<Void> actual = controller.deleteTask(1L);

        // then
        assertEquals(ResponseEntity.noContent().build(), actual);
    }
}
