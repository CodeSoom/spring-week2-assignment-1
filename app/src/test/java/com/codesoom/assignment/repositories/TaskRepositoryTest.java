package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TaskRepository 클래스")
public class TaskRepositoryTest {

    TaskRepositoryImpl taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository = new TaskRepositoryImpl();
    }

    @AfterEach
    void afterEach() {
        taskRepository.removeAll();
    }

    @Nested
    @DisplayName("findAll 메소드가")
    class Describe_findAll {
        @Nested
        @DisplayName("저장된 task가 있다면")
        class Context_with_tasks {
            @BeforeEach
            void prepareTasks() {
                Task task1 = new Task(1L, "title1");
                Task task2 = new Task(2L, "title2");
                taskRepository.save(task1);
                taskRepository.save(task2);
            }

            @Test
            @DisplayName("task list를 리턴한다")
            void it_returns_task_list() {
                List<Task> tasks = taskRepository.findAll();
                assertEquals(2, tasks.size());
            }
        }
    }
}
