package com.codesoom.assignment.controllers;

import com.codesoom.assignment.annotations.AutoConfigureUtf8MockMvc;
import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.dtos.TaskDto;
import com.codesoom.assignment.repositories.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// DONE: GET /tasks - 모든 Task 조회
// DONE: GET /tasks/{id} - 1개의 Task 조회
// DONE: POST /tasks - Task 1개 추가
// TODO: PUT/PATCH /tasks/{id} - Task 수정
// TODO: DELETE /tasks/{id} - 할 일 삭제

@SpringBootTest
@AutoConfigureUtf8MockMvc
class TaskControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private TaskRepository taskRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String TASKS = "/tasks";

    @BeforeEach
    public void clean() {
        taskRepository.removeAll();
    }

    @Test
    @DisplayName("POST /tasks, Task 생성 확인")
    public void postTasks() throws Exception {
        TaskDto input = new TaskDto("할 일");
        Task output = new Task(1L, "할 일");

        String inputJson = objectMapper.writeValueAsString(input);
        String outputJson = objectMapper.writeValueAsString(output);

        mockMvc.perform(
                post(TASKS)
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    @DisplayName("GET /tasks, 모든 Task 조회 확인")
    public void getTasks() throws Exception {
        // task 2개를 추가
        addTask("할 일1");
        addTask("할 일2");

        String outputJson = objectMapper.writeValueAsString(taskRepository.findAll());

        mockMvc.perform(
                        get(TASKS))
                .andExpect(status().isOk())
                .andExpect(content().string(outputJson));
    }

    @Test
    @DisplayName("GET /tasks/{id}, 단건 Task 조회 확인")
    public void getTask() throws Exception {
        // task 2개를 추가
        addTask("할 일1");
        Task task2 = addTask("할 일2");

        String outputJson = objectMapper.writeValueAsString(task2);

        mockMvc.perform(
                        get(TASKS +"/2"))
                .andExpect(status().isOk())
                .andExpect(content().string(outputJson));
    }

    @Test
    @DisplayName("PUT /tasks/{id}, Task 수정 확인")
    public void putTask() throws Exception {
        // task 2개를 추가
        addTask("할 일1");
        Task task2 = addTask("할 일2");

        TaskDto taskDto = new TaskDto("할 일3");
        String inputJson = objectMapper.writeValueAsString(taskDto);

        Task task3 = new Task(task2.getId(), taskDto.getTitle());
        String outputJson = objectMapper.writeValueAsString(task3);

        mockMvc.perform(
                        put(TASKS +"/2")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(outputJson));
    }

    @Test
    @DisplayName("PATCH /tasks/{id}, Task 수정 확인")
    public void patchTask() throws Exception {
        // task 2개를 추가
        addTask("할 일1");
        Task task2 = addTask("할 일2");

        TaskDto taskDto = new TaskDto("할 일3");
        String inputJson = objectMapper.writeValueAsString(taskDto);

        Task task3 = new Task(task2.getId(), taskDto.getTitle());
        String outputJson = objectMapper.writeValueAsString(task3);

        mockMvc.perform(
                        put(TASKS +"/2")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(outputJson));
    }

    @Test
    @DisplayName("DELETE /tasks/{id}, Task 삭제 확인")
    public void deleteTask() throws Exception {
        // task 2개를 추가
        addTask("할 일1");
        addTask("할 일2");

        mockMvc.perform(
                        delete(TASKS +"/2"))
                .andExpect(status().isOk());

        assertEquals(taskRepository.count(), 1);
    }

    private Task addTask(String title) {
        return taskRepository.save(new TaskDto(title));
    }


}