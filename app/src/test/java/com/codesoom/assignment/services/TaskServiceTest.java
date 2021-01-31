package com.codesoom.assignment.services;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repositories.TaskRepository;
import com.codesoom.assignment.utils.IdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("TestService 클래스")
class TaskServiceTest {

    TaskService taskService;

    @BeforeEach
    void setUp() {
        TaskRepository taskRepository = new TaskRepository();
        IdGenerator idGenerator = new IdGenerator();
        taskService = new TaskService(taskRepository, idGenerator);
    }

    @Nested
    @DisplayName("getTasks 메소드는")
    class Describe_getTasks {
        @Nested
        @DisplayName("저장된 task가 있다면")
        class Context_with_tasks {
            @BeforeEach
            void prepareTasks() {
                Task task1 = new Task(1L, "task1");
                Task task2 = new Task(2L, "task2");
                taskService.addTask(task1);
                taskService.addTask(task2);
            }

            @Test
            @DisplayName("task list를 리턴한다")
            void it_returns_task_list() {
                List<Task> tasks = taskService.getTasks();

                assertEquals(2, tasks.size());
            }
        }

        @Nested
        @DisplayName("저장된 task가 없다면")
        class Context_with_empty_tasks {
            @Test
            @DisplayName("empty task list를 리턴한다")
            void it_returns_empty_task_list() {
                List<Task> tasks = taskService.getTasks();

                assertEquals(0, tasks.size());
            }
        }
    }

    @Nested
    @DisplayName("addTask 메소드는")
    class Describe_addTask {
        @Nested
        @DisplayName("생성하려는 task가 유효하다면")
        class Context_with_task {
            String title = "task";
            Task task = new Task(title);

            @Test
            @DisplayName("생성된 task를 리턴한다")
            void it_returns_created_task() {
                Task task = taskService.addTask(this.task);

                assertEquals(title, task.getTitle());
            }
        }
    }

    @Nested
    @DisplayName("getTask 메소드는")
    class Describe_findOne {
        @Nested
        @DisplayName("존재하는 task id로 요청한다면")
        class Context_with_valid_task_id {
            Task task;

            @BeforeEach
            void prepareTask() {
                task = new Task("title");
                taskService.addTask(task);
            }

            @Test
            @DisplayName("task를 리턴한다")
            void it_returns_optional_task_with_value() {
                Task foundTask = taskService.getTask(task.getId());

                assertEquals(foundTask, task);
            }
        }

        @Nested
        @DisplayName("존재하지 않는 task id로 요청한다면")
        class Context_with_invalid_task_id {
            Long notExistedId = -1L;

            @Test
            @DisplayName("TaskNotFoundException을 던진다")
            void it_throws_task_not_found_exception() {
                assertThrows(TaskNotFoundException.class,
                        () -> taskService.getTask(notExistedId));
            }
        }
    }

    @Nested
    @DisplayName("updateTask 메소드는")
    class Describe_updateTask {
        @Nested
        @DisplayName("존재하는 task id로 수정 한다면")
        class Context_with_valid_task_id {
            Task task;
            Task taskForUpdate;

            @BeforeEach
            void prepareTask() {
                task = new Task("title");
                taskService.addTask(task);
                taskForUpdate = new Task("new title");
            }

            @Test
            @DisplayName("수정된 task를 리턴한다")
            void it_returns_optional_task_with_value() {
                Task updatedTask = taskService.updateTask(task.getId(), taskForUpdate);

                assertEquals(taskForUpdate.getTitle(), updatedTask.getTitle());
            }
        }

        @Nested
        @DisplayName("존재하는 않은 task id로 수정 한다면")
        class Context_with_invalid_task_id {
            Long notExistedId = -1L;
            Task taskForUpdate;

            @BeforeEach
            void prepareTask() {
                taskForUpdate = new Task("new title");
            }

            @Test
            @DisplayName("TaskNotFoundException을 던진다")
            void it_throws_task_not_found_exception() {
                assertThrows(TaskNotFoundException.class,
                        () -> taskService.updateTask(notExistedId, taskForUpdate));
            }
        }
    }

    @Nested
    @DisplayName("deleteTask 메소드는")
    class Describe_deleteTask {
        @Nested
        @DisplayName("존재하는 task id로 삭제한다면")
        class Context_with_valid_task_id {
            Task task;

            @BeforeEach
            void prepareTask() {
                task = new Task("title");
                taskService.addTask(task);
            }

            @Test
            @DisplayName("task를 삭제한다")
            void it_delete_a_task() {
                taskService.deleteTask(task.getId());

                assertThrows(TaskNotFoundException.class,
                        () -> taskService.getTask(task.getId()));
            }
        }

        @Nested
        @DisplayName("존재하지 않는 task id로 삭제한다면")
        class Context_with_invalid_task_id {
            Long notExistedId = -1L;

            @Test
            @DisplayName("TaskNotFoundException을 던진다")
            void it_throws_task_not_found_exception() {
                assertThrows(TaskNotFoundException.class,
                        () -> taskService.deleteTask(notExistedId));
            }
        }
    }

}
