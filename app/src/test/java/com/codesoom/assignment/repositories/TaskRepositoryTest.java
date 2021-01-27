package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TaskRepository 클래스")
class TaskRepositoryTest {

    TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository = new TaskRepository();
    }

    @AfterEach
    void afterEach() {
        taskRepository.removeAll();
    }

    @Nested
    @DisplayName("findAll 메소드는")
    class Describe_findAll {
        @Nested
        @DisplayName("tasks가 있다면")
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

        @Nested
        @DisplayName("tasks가 없다면")
        class Context_with_empty_tasks {
            @Test
            @DisplayName("empty task list를 리턴한다")
            void it_returns_task_list() {
                List<Task> tasks = taskRepository.findAll();

                assertEquals(0, tasks.size());
            }
        }
    }

    @Nested
    @DisplayName("save 메소드는")
    class Describe_save {
        @Nested
        @DisplayName("task가 있다면")
        class Context_with_task {
            Long id = 1L;
            String title = "task";
            Task task = new Task(id, title);

            @Test
            @DisplayName("저장한 task를 리턴한다")
            void it_returns_task() {
                Task savedTask = taskRepository.save(task);

                assertEquals(id, savedTask.getId());
                assertEquals(title, savedTask.getTitle());
            }
        }
    }

    @Nested
    @DisplayName("findOne 메소드는")
    class Describe_findOne {
        @Nested
        @DisplayName("task id가 유효하면")
        class Context_with_valid_task_id {
            Task task;

            @BeforeEach
            void prepareTask() {
                task = new Task(1L, "title");
                taskRepository.save(task);
            }

            @Test
            @DisplayName("값이 존재하는 optional task를 리턴한다")
            void it_returns_optional_task_with_value() {
                Optional<Task> foundTask = taskRepository.findOne(task.getId());

                assertEquals(task, foundTask.get());
            }
        }

        @Nested
        @DisplayName("task id가 유효하지 않다면")
        class Context_with_invalid_task_id {
            Long notExistedId = -1L;

            @Test
            @DisplayName("빈 optional task를 리턴한다")
            void it_returns_empty_optional_task() {
                Optional<Task> task = taskRepository.findOne(notExistedId);

                assertFalse(task.isPresent());
            }
        }
    }

    @Nested
    @DisplayName("delete 메소드는")
    class Describe_delete {
        @Nested
        @DisplayName("task id가 유효하면")
        class Context_with_valid_task_id {
            Task task;

            @BeforeEach
            void prepareTask() {
                task = new Task(1L, "title");
                taskRepository.save(task);
            }

            @Test
            @DisplayName("유효한 task id의 task를 삭제한다")
            void it_delete_task_of_valid_task_id() {
                taskRepository.delete(task.getId());
                Optional<Task> deletedTask = taskRepository.findOne(task.getId());

                assertTrue(deletedTask.isEmpty());
            }
        }
    }

}
