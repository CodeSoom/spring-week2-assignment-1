package com.codesoom.assignment.application;

import com.codesoom.assignment.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TaskServiceTest {
    // 1. list -> getTasks
    // 2. detail -> getTask (with ID)
    // 3. create -> createTask (with source)
    // 4. update -> updateTask (with ID, source)
    // 5. delete -> deleteTask (with ID)
    private static final String TASK_TITLE = "test";
    private static final String UPDATE_POSTFIX = "^^";


    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void getTasks() {
        assertThat(taskService.getTasks()).isEmpty();
    }

    @Test
    void getTaskWithValidId() {
        Task task = new Task();
        task.setTitle(TASK_TITLE);
        taskService.createTask(task);

        assertThat(taskService.getTask(1L).getTitle()).isEqualTo(TASK_TITLE);
    }

    @Test
    void getTaskWithInvalidId() {
        assertThatThrownBy(() -> taskService.getTask(100L))
                .isInstanceOf(TaskNotFoundException.class);
    }

    @Test
    void CreateTask() {
        int oldSize = taskService.getTasks().size();

        Task task = new Task();
        task.setTitle("test");

        taskService.createTask(task);

        int newSize = taskService.getTasks().size();

        assertThat(newSize - oldSize).isEqualTo(1);
        assertThat(taskService.getTasks()).hasSize(1);
    }

    @Test
    void updateTask() {
        Task task = new Task();
        task.setTitle(TASK_TITLE);

        taskService.createTask(task);

        assertThat(taskService.getTask(1L).getTitle()).isEqualTo(TASK_TITLE);

        task = new Task();
        task.setTitle(TASK_TITLE + UPDATE_POSTFIX);

        taskService.updateTask(1L, task);

        assertThat(taskService.getTask(1L).getTitle()).isEqualTo(TASK_TITLE + UPDATE_POSTFIX);

    }

    @Test
    void deleteTask() {
        int oldSize = taskService.getTasks().size();

        Task task = new Task();
        task.setTitle("test");

        taskService.createTask(task);
        taskService.deleteTask(Long.valueOf(taskService.getTasks().size()));

        int newSize = taskService.getTasks().size();

        assertThat(newSize - oldSize).isEqualTo(0);
        assertThat(taskService.getTasks()).hasSize(0);
    }
}