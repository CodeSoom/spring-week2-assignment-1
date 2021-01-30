package com.codesoom.assignment.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Task 클래스")
class TaskTest {

    @DisplayName("task 객체를 생성하면 생성자에 입력 인자를 getter로 값을 리턴받을 수 있다")
    @Test
    void canCreate() {
        Task task = new Task(1L, "homework");

        assertEquals(task.getId(), 1L);
        assertEquals(task.getTitle(), "homework");
    }

    @Nested
    @DisplayName("id 메소드는")
    class Describe_id {

        @Nested
        @DisplayName("만약 Long 타입 정수값이 id로 생성자에 주어지면")
        class Context_with_real {
            Long givenId = 1L;
            Task task = new Task(givenId, null);
            @Test
            @DisplayName("생성자에 주어진 id를 리턴한다")
            void it_return_a_long_id() {
                assertEquals(task.getId(), givenId);
            }
        }
    }

    @Nested
    @DisplayName("title 메소드는")
    class Describe_title {

        @Nested
        @DisplayName("만약 할 일이 담긴 title이 생성자에 주어지면")
        class Context_with_real {
            String givenTitle = "공부하기";
            Task task = new Task(null, givenTitle);
            @Test
            @DisplayName("생성자에 주어진 title를 리턴한다")
            void it_return_a_string_title() {
                assertEquals(task.getTitle(), givenTitle);
            }
        }
    }
}
