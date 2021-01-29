package com.codesoom.assignment.models;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;

@DisplayName("Task 클래스")
public class TaskTest {
    Integer givenID = 1;
    String givenTitle = "sample";
    Task givenTask = new Task(givenID, givenTitle);

    @Nested
    @DisplayName("id 메소드는")
    class Describe_id {

        @Test
        @DisplayName("입력된 값을 리턴한다.")
        void It_returns_given_value() {
            Assertions.assertEquals(givenID, givenTask.id());
        }
    }

    @Nested
    @DisplayName("title 메소드는")
    class Describe_title {

        @Test
        @DisplayName("입력된 값을 리턴한다.")
        void It_returns_given_value() {
            Assertions.assertEquals(givenTitle, givenTask.title());
        }
    }
}
