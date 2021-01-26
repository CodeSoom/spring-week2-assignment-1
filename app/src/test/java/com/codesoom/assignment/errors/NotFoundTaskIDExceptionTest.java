package com.codesoom.assignment.errors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("NotFoundTaskIDException 클래스")
public class NotFoundTaskIDExceptionTest {

    @Nested
    @DisplayName("getMessage 메서드는")
    class Describe_getMessage {
        final String defaultMessage = "Not found task id";

        @Nested
        @DisplayName("생성시 id를 입력했을 때")
        class Context_withID {
            final NotFoundTaskIDException givenError = new NotFoundTaskIDException();

            @Test
            @DisplayName("id가 포함된 메시지를 리턴한다.")
            void It_returns_include_id_message() {
                Assertions.assertEquals(defaultMessage, givenError.getMessage());
            }
        }

        @Nested
        @DisplayName("생성시 id를 입력하지 않았을 때")
        class Context_withoutID {
            final long givenID = 1L;
            final NotFoundTaskIDException givenError = new NotFoundTaskIDException(givenID);

            @Test
            @DisplayName("기본 메시지를 리턴한다.")
            void It_returns_include_id_message() {
                final String expectMessage = String.format("Not found task id: %d", givenID);

                Assertions.assertEquals(expectMessage, givenError.getMessage());
            }
        }
    }
}
