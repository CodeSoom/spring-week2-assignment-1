package com.codesoom.assignment.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tasks 클래스")
class TasksTest {
    private Tasks tasks;
    private Task task;

    @BeforeEach
    void setUp() {
        tasks = new Tasks();
        task = new Task(1L, "homework");
    }

    @DisplayName("Tasks 객체에 Task를 추가하면 값이 저장되고 해당하는 값을 찾을 수 있다")
    @Test
    void addTask() {
        tasks.addTask(task);

        Task task = tasks.findTask(1L)
                .orElse(null);

        assertEquals(1, tasks.size());
        assertEquals(1L, task.getId());
    }

    @DisplayName("Tasks 객체에 Task를 제거하면 값이 사라지고 해당하는 값을 찾을 수 없다")
    @Test
    void remove() {
        tasks.addTask(task);

        tasks.remove(1L);

        Task task = tasks.findTask(1L)
                .orElse(null);
        assertNull(task);
        assertEquals(0, tasks.size());
    }

    @Nested
    @DisplayName("size 메서드는")
    class Describe_size {
        @Nested
        @DisplayName("등록된 할 일이 있으면")
        class Context_with_task {
            String givenTitle = "Shopping";
            Task givenTask = new Task(null, givenTitle);

            @Test
            @DisplayName("등록된 할 일 숫자를 리턴한다")
            void It_return_number_of_task() {
                tasks.addTask(givenTask);
                assertEquals(1, tasks.size());
            }
        }

        @Nested
        @DisplayName("등록된 할 일이 없으면")
        class Context_without_task {
            @Test
            @DisplayName("등록된 개수가 0인 숫자를 리턴한다")
            void It_return_number_of_task() {
                assertEquals(0, tasks.size());
            }
        }
    }

    @Nested
    @DisplayName("remove 메서드는")
    class Describe_remove {
        @Nested
        @DisplayName("등록된 제거할 할 일 id가 주어진다면")
        class Context_with_task {
            Long givenId = 1L;
            Task givenTask = new Task(givenId, "homework");

            @Test
            @DisplayName("할 일 목록에서 제거된다")
            void It_task_is_null() {
                tasks.addTask(givenTask);
                tasks.remove(givenId);

                assertEquals(Optional.empty(), tasks.findTask(givenId));
            }
        }

        @Nested
        @DisplayName("등록된 제거할 할 일 id가 주어지지않으면")
        class Context_without_task {
            Long givenId = 1L;
            Task givenTask = new Task(givenId, "homework");

            @Test
            @DisplayName("할 일 목록에서 남아있다")
            void It_task_is_not_null() {
                tasks.addTask(givenTask);
                tasks.remove(-1L);

                assertTrue(tasks.findTask(givenId).isPresent());
            }
        }
    }

    @Nested
    @DisplayName("getTasks 메서드는")
    class Describe_getTasks {
        @Nested
        @DisplayName("할 일들이 주어진다면")
        class Context_with_tasks {
            Task givenTask = new Task(1L, "homework");
            Task givenTask2 = new Task(1L, "homework2");
            Task givenTask3 = new Task(1L, "homework3");

            @Test
            @DisplayName("등록된 할 일 목록을 리턴한다.")
            void It_return_tasks() {
                tasks.addTask(givenTask);
                tasks.addTask(givenTask2);
                tasks.addTask(givenTask3);

                assertEquals(Arrays.asList(givenTask, givenTask2, givenTask3), tasks.getTasks());
            }
        }

        @Nested
        @DisplayName("할 일들이 주어지지 않으면")
        class Context_without_tasks {
            @Test
            @DisplayName("비어있는 할 일 목록을 리턴한다.")
            void It_return_empty_tasks() {
                assertEquals(new ArrayList<>(), tasks.getTasks());
            }
        }
    }

    @Nested
    @DisplayName("addTasks 메서드는")
    class Describe_addTasks {
        @Nested
        @DisplayName("할 일이 주어진다면")
        class Context_with_task {
            Task givenTask = new Task(1L, "homework");

            @Test
            @DisplayName("등록된 할 일은 0보다 크다")
            void It_tasks_size_is_not_zero() {
                tasks.addTask(givenTask);

                assertEquals(1, tasks.size());
            }
        }

        @Nested
        @DisplayName("할 일들이 주어지지 않으면")
        class Context_without_task {
            @Test
            @DisplayName("등록된 할 일은 0이다")
            void It_tasks_size_zero() {
                assertEquals(0, tasks.size());
            }
        }
    }
}
