package com.codesoom.assignment.models;

import com.codesoom.assignment.errors.NotFoundTaskIDException;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@DisplayName("TaskManager 클래스")
public class TaskRepositoryTest {
    TaskRepository taskRepository = TaskRepository.instance();

    @BeforeEach
    void BeforeEach() {
        taskRepository.clear();
    }

    @Nested
    @DisplayName("clear 메소드는")
    class Describe_clear {
        List<Task> expectTasks = new ArrayList<>();

        @Test
        @DisplayName("아무것도 리턴하지 않는다. clear 후에 findAll 을 하면 빈 Task 리스트를 리턴한다.")
        void It_returns_nothing_and_find_all_returns_empty_task_list() {
            taskRepository.clear();

            List<Task> tasks = taskRepository.findAll();
            Assertions.assertArrayEquals(expectTasks.toArray(), tasks.toArray());
        }
    }

    @Nested
    @DisplayName("findAll 메소드는")
    class Describe_findAll {

        @Nested
        @DisplayName("Task 가 하나도 없을 때")
        class Context_when_task_is_empty {
            List<Task> expectTasks = new ArrayList<>();

            @Test
            @DisplayName("빈 Task 리스트를 리턴한다.")
            void It_returns_empty_task_list() {
                List<Task> tasks = taskRepository.findAll();

                Assertions.assertArrayEquals(expectTasks.toArray(), tasks.toArray());
            }
        }

        @Nested
        @DisplayName("Task 가 있을 때")
        class Context_when_task_is_exists {
            int givenID = 0;
            String givenTitle = "sample";
            Task givenTask = new Task(givenID, givenTitle);
            List<Task> expectTasks = new ArrayList<>(Collections.singletonList(givenTask));

            @Test
            @DisplayName("Task 리스트를 리턴한다.")
            void It_returns_task_list() {
                taskRepository.insertOne(givenTitle);
                List<Task> tasks = taskRepository.findAll();

                Assertions.assertEquals(expectTasks.toArray().length, tasks.toArray().length);
                Assertions.assertEquals(expectTasks.get(0).id(), tasks.get(0).id());
                Assertions.assertEquals(expectTasks.get(0).title(), tasks.get(0).title());
            }
        }
    }

    @Nested
    @DisplayName("findOne 메소드는")
    class Describe_findOne {
        @Nested
        @DisplayName("찾으려는 id가 없을 때")
        class Context_when_find_id_is_not_exists {
            int givenID = 0;

            @Test
            @DisplayName("NotFoundTaskIDException 을 던진다.")
            void It_throws_not_found_task_id_exception() {
                Assertions.assertThrows(NotFoundTaskIDException.class, () -> taskRepository.findOne(givenID));
            }
        }

        @Nested
        @DisplayName("찾으려는 id가 있을 때")
        class Context_when_find_id_is_exists {
            int givenID = 0;
            String givenTitle = "sample";

            @Test
            @DisplayName("Task 를 리턴한다.")
            void It_returns_task() {
                taskRepository.insertOne(givenTitle);

                Task task = taskRepository.findOne(givenID);

                Assertions.assertEquals(givenTitle, task.title());
            }
        }
    }

    @Nested
    @DisplayName("insertOne 메소드는")
    class Describe_insertOne {
        int givenID = 0;
        String givenTitle = "sample";

        @Test
        @DisplayName("생성된 Task 를 리턴한다.")
        void It_returns_inserted_task() {
            Task task = taskRepository.insertOne(givenTitle);

            Assertions.assertEquals(givenID, task.id());
            Assertions.assertEquals(givenTitle, task.title());
        }
    }

    @Nested
    @DisplayName("modifyOne 메소드는")
    class Describe_modifyOne {
        int givenID = 0;
        String givenTitle = "sample";
        String givenModifyTitle = "modified sample";

        @Nested
        @DisplayName("변경하려는 id가 없을 때")
        class Context_when_modify_id_is_not_exists {

            @Test
            @DisplayName("NotFoundTaskIDException 을 던진다.")
            void It_throws_not_found_task_id_exception() {
                Assertions.assertThrows(NotFoundTaskIDException.class, () -> taskRepository.modifyOne(givenID, givenModifyTitle));
            }
        }

        @Nested
        @DisplayName("변경하려는 id가 있을 때")
        class Context_when_modify_id_is_exists {

            @Test
            @DisplayName("수정된 Task 를 리턴한다.")
            void It_returns_modified_task() {
                taskRepository.insertOne(givenTitle);
                Task task = taskRepository.modifyOne(givenID, givenModifyTitle);

                Assertions.assertEquals(givenID, task.id());
                Assertions.assertEquals(givenModifyTitle, task.title());
            }
        }
    }

    @Nested
    @DisplayName("deleteOne 메소드는")
    class Describe_deleteOne {
        int givenID = 0;
        String givenTitle = "sample";

        @Nested
        @DisplayName("삭제하려는 id가 없을 때")
        class Context_when_delete_id_is_not_exists {

            @Test
            @DisplayName("NotFoundTaskIDException 을 던진다.")
            void It_throws_not_found_task_id_exception() {
                Assertions.assertThrows(NotFoundTaskIDException.class, () -> taskRepository.deleteOne(givenID));
            }
        }

        @Nested
        @DisplayName("삭제하려는 id가 있을 때")
        class Context_when_delete_id_is_exists {

            @Test
            @DisplayName("삭제된 Task 를 리턴한다. 식제한 Task 를 찾으려 하면 NotFoundTaskIDException 을 던진다.")
            void It_returns_deleted_task_and_find_id_throws_not_found_task_id_exception() {
                taskRepository.insertOne(givenTitle);
                Task task = taskRepository.deleteOne(givenID);

                Assertions.assertEquals(givenID, task.id());
                Assertions.assertEquals(givenTitle, task.title());
                Assertions.assertThrows(NotFoundTaskIDException.class, () -> taskRepository.findOne(givenID));
            }
        }
    }
}
