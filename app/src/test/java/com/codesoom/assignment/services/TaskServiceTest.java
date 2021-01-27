package com.codesoom.assignment.services;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repositories.TaskRepository;
import com.codesoom.assignment.utils.IdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskServiceTest {

    TaskService taskService;

    @BeforeEach
    void setUp() {
        TaskRepository taskRepository = new TaskRepository();
        IdGenerator idGenerator = new IdGenerator();
        taskService = new TaskService(taskRepository, idGenerator);
    }

    @Test
    void getEmptyTasks() {
        List<Task> tasks = taskService.getTasks();

        assertEquals(0, tasks.size());
    }

    @Test
    void getTasks() {
        Task task1 = new Task(1L, "task1");
        Task task2 = new Task(2L, "task2");

        taskService.addTask(task1);
        taskService.addTask(task2);
        List<Task> tasks = taskService.getTasks();

        assertEquals(2, tasks.size());
    }

    @Test
    void addTask() {
        Task task1 = new Task(1L, "task1");

        taskService.addTask(task1);
        List<Task> tasks = taskService.getTasks();

        assertEquals(1, tasks.size());
    }


    @Test
    void getTask() {
        Task task1 = new Task(1L, "task1");

        taskService.addTask(task1);
        Task task = taskService.getTask(task1.getId());

        assertEquals(task, task1);
    }

    @Test
    void getNotExistedTask() {
        Long notExistedId = 1L;

        assertThrows(TaskNotFoundException.class,
                () -> taskService.getTask(notExistedId));
    }

    @Test
    void updateTask() {
        Task task1 = new Task(1L, "task1");
        Task newTask = new Task(1L, "newTask");

        taskService.addTask(task1);
        taskService.updateTask(task1.getId(), newTask);

        assertEquals(task1.getTitle(), newTask.getTitle());
    }

    @Test
    void updateNotExistedTask() {
        Task task1 = new Task(1L, "task1");
        Long notExistedId = 1L;

        assertThrows(TaskNotFoundException.class,
                () -> taskService.updateTask(notExistedId, task1));
    }

    @Test
    void deleteTask() {
        Task task1 = new Task(1L, "task1");

        taskService.addTask(task1);
        taskService.deleteTask(task1.getId());

        assertThrows(TaskNotFoundException.class,
                () -> taskService.getTask(task1.getId()));
    }

}
