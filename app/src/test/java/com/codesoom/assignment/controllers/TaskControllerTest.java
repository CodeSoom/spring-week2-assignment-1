package com.codesoom.assignment.controllers;

import com.codesoom.assignment.controller.TaskController;
import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.entity.Task;
import com.codesoom.assignment.repository.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    TaskController taskController;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeAll
    public void init() {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(1L);
        taskDto.setTitle("hello");
        TaskDto taskDto2 = new TaskDto();
        taskDto2.setId(2L);
        taskDto2.setTitle("world");
        taskController.addTask(taskDto);
        taskController.addTask(taskDto2);
    }

    @Test
    @Order(1)
    @DisplayName("GET - /tasks 파라미터 없이")
    public void getTaskList() throws Exception {
        mockMvc.perform(get("/tasks")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].title").value("hello"))
                .andExpect(jsonPath("$.[1].title").value("world"));
    }

    @Test
    @Order(2)
    @DisplayName("GET - /tasks/{id} id 존재")
    public void getTaskOk() throws Exception {
        mockMvc.perform(get("/tasks/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("title").value("hello"));
    }

    @Test
    @DisplayName("GET - /tasks/{id} id 없음")
    public void getTaskNotFound() throws Exception {
        mockMvc.perform(get("/tasks/10")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST - /tasks title 정상입력")
    public void postTask() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(3L);
        String newTitle = "friends";
        taskDto.setTitle(newTitle);

        mockMvc.perform(post("/tasks")
                .content(objectMapper.writeValueAsString(taskDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("title").value(newTitle));

        Task task = taskRepository.findById(3L).orElse(null);
        assertEquals(task.getId(), 3L);
        assertEquals(task.getTitle(), newTitle);
    }

    @Test
    @DisplayName("PUT - /tasks id title 정상입력")
    public void putTask() throws Exception {
        TaskDto taskDto = new TaskDto();
        String newTitle = "new world";
        taskDto.setTitle(newTitle);

        mockMvc.perform(put("/tasks/1")
                .content(objectMapper.writeValueAsString(taskDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("title").value(newTitle));

        assertEquals(taskRepository.findById(1L).get().getTitle(), newTitle);

    }

    @Test
    @DisplayName("PUT - /tasks 없는 아이디 수정요청")
    public void putTaskNoID() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle("new world");

        mockMvc.perform(put("/tasks/666")
                .content(objectMapper.writeValueAsString(taskDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE - /tasks 정상 삭제")
    public void deleteTask() throws Exception {
        mockMvc.perform(delete("/tasks/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNoContent());

        Task removedTask = taskRepository.findById(1L).orElse(null);
        assertNull(removedTask);
        assertEquals(taskRepository.existsById(1L), false);
    }

}
