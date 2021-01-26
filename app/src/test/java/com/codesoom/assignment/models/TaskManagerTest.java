package com.codesoom.assignment.models;

import org.junit.jupiter.api.*;

@DisplayName("TaskManager 클래스")
public class TaskManagerTest {
    @BeforeEach
    void BeforeEach() {
        // clear 메소드를 실행하도록 하기
    }

    @Nested
    @DisplayName("clear 메소드는")
    class Describe_clear {
        @Test
        @DisplayName("아무것도 리턴하지 않는다. clear 후에 findAll 을 하면 빈 Task 리스트를 리턴한다.")
        void It_returns_nothing_and_find_all_returns_empty_task_list() {

        }
    }

    @Nested
    @DisplayName("findAll 메소드는")
    class Describe_findAll {

        @Nested
        @DisplayName("Task 가 하나도 없을 때")
        class Context_when_task_is_empty {

            @Test
            @DisplayName("빈 Task 리스트를 리턴한다.")
            void It_returns_empty_task_list() {

            }
        }

        @Nested
        @DisplayName("Task 가 있을 때")
        class Context_when_task_is_exists {

            @Test
            @DisplayName("Task 리스트를 리턴한다.")
            void It_returns_task_list() {

            }
        }
    }

    @Nested
    @DisplayName("findOne 메소드는")
    class Describe_findOne {
        @Nested
        @DisplayName("찾으려는 id가 없을 때")
        class Context_when_find_id_is_not_exists {

            @Test
            @DisplayName("NotFoundTaskIDException 을 던진다.")
            void It_throws_not_found_task_id_exception() {

            }
        }

        @Nested
        @DisplayName("찾으려는 id가 있을 때")
        class Context_when_find_id_is_exists {

            @Test
            @DisplayName("Task 를 리턴한다.")
            void It_returns_task() {

            }
        }
    }

    @Nested
    @DisplayName("insertOne 메소드는")
    class Describe_insertOne {

        @Test
        @DisplayName("생성된 Task 를 리턴한다.")
        void It_returns_inserted_task() {

        }
    }

    @Nested
    @DisplayName("modifyOne 메소드는")
    class Describe_modifyOne {
        @Nested
        @DisplayName("변경하려는 id가 없을 때")
        class Context_when_modify_id_is_not_exists {

            @Test
            @DisplayName("NotFoundTaskIDException 을 던진다.")
            void It_throws_not_found_task_id_exception() {

            }
        }

        @Nested
        @DisplayName("변경하려는 id가 있을 때")
        class Context_when_modify_id_is_exists {

            @Test
            @DisplayName("수정된 Task 를 리턴한다.")
            void It_returns_modified_task() {

            }
        }
    }

    @Nested
    @DisplayName("deleteOne 메소드는")
    class Describe_deleteOne {
        @Nested
        @DisplayName("삭제하려는 id가 없을 때")
        class Context_when_delete_id_is_not_exists {

            @Test
            @DisplayName("NotFoundTaskIDException 을 던진다.")
            void It_throws_not_found_task_id_exception() {

            }
        }

        @Nested
        @DisplayName("삭제하려는 id가 있을 때")
        class Context_when_delete_id_is_exists {

            @Test
            @DisplayName("아무것도 리턴하지 않는다. 식제한 Task 를 찾으려 하면 NotFoundTaskIDException 을 던진다.")
            void It_returns_nothing_and_find_id_throws_not_found_task_id_exception() {

            }
        }
    }
}
