package com.codesoom.assignment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings({"InnerClassMayBeStatic", "NonAsciiCharacters"})
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@DisplayName("TaskController 클래스")
class TaskControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Nested
    @DisplayName("GET 메소드는")
    class Describe_GET {
        @Nested
        @DisplayName("만약 tasks가 비어있다면")
        class Context_empty_task {
            @Test
            @DisplayName("빈 배열을 리턴한다")
            void it_returns_empty_array() throws Exception {
                mockMvc.perform(get("/tasks"))
                        .andExpect(status().isOk())
                        .andExpect(content().string("[]"));
            }
        }

        @Nested
        @DisplayName("만약 없는 task id를 요청한다면")
        class Context_invalid_task_id {
            @Test
            @DisplayName("404 에러를 리턴한다")
            void it_returns_404_error() throws Exception {
                mockMvc.perform(get("/tasks/0"))
                        .andExpect(status().isNotFound());
            }
        }
    }
}
