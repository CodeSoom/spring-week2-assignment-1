package com.codesoom.assignment.controllers;

import com.codesoom.assignment.entity.Task;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    TaskController taskController;

    @BeforeAll
    public void init() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("hello");
        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("world");
        taskController.postTask(task);
        taskController.postTask(task2);
    }

    @Test
    @DisplayName("GET - /tasks 파라미터 없이")
    public void getTaskList() throws Exception {
        mockMvc.perform(get("/tasks")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"title\":\"hello\"},{\"id\":2,\"title\":\"world\"}]"));
    }

    @Test
    @DisplayName("GET - /tasks/{id} id 존재")
    public void getTaskOk() throws Exception {
        mockMvc.perform(get("/tasks/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"title\":\"hello\"}"));
    }

    @Test
    @DisplayName("GET - /tasks/{id} id 없음")
    public void getTaskNotFound() throws Exception {
        mockMvc.perform(get("/tasks/10")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
