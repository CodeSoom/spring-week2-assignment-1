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
    @DisplayName("POST /tasks, Task 를 생성하는 HTTP 요청을 전송합니다. Http Status 201 과 생성된 Task 의 JSON 을 Body 로 반환합니다.")
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
    @DisplayName("GET /tasks, 모든 Task 를 조회하는 HTTP 요청을 전송합니다. Http Status 200 과 모든 Task 의 JSON 을 Body 로 반환합니다.")
    public void getTasks() throws Exception {
        addTask("할 일1");
        addTask("할 일2");

        String outputJson = objectMapper.writeValueAsString(taskRepository.findAll());

        mockMvc.perform(
                        get(TASKS))
                .andExpect(status().isOk())
                .andExpect(content().string(outputJson));
    }

    @Test
    @DisplayName("GET /tasks/{id}, {id} 에 해당하는 Task 를 조회하는 HTTP 요청을 전송합니다. Http Status 200 과 해당 Task 의 JSON 을 Body 로 반환합니다.")
    public void getTask() throws Exception {
        addTask("할 일1");
        Task task2 = addTask("할 일2");

        String outputJson = objectMapper.writeValueAsString(task2);

        mockMvc.perform(
                        get(TASKS +"/2"))
                .andExpect(status().isOk())
                .andExpect(content().string(outputJson));
    }

    @Test
    @DisplayName("PUT /tasks/{id}, {id} 에 해당하는 Task 를 수정하는 HTTP 요청을 전송합니다. Http Status 200 과 해당 Task 의 JSON 을 Body 로 반환합니다.")
    public void putTask() throws Exception {
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
    @DisplayName("PATCH /tasks/{id}, {id} 에 해당하는 Task 를 수정하는 HTTP 요청을 전송합니다. Http Status 200 과 해당 Task 의 JSON 을 Body 로 반환합니다.")
    public void patchTask() throws Exception {
        addTask("할 일1");
        Task task2 = addTask("할 일2");

        TaskDto taskDto = new TaskDto("할 일3");
        String inputJson = objectMapper.writeValueAsString(taskDto);

        Task task3 = new Task(task2.getId(), taskDto.getTitle());
        String outputJson = objectMapper.writeValueAsString(task3);

        mockMvc.perform(
                        patch(TASKS +"/2")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(outputJson));
    }

    @Test
    @DisplayName("DELETE /tasks/{id}, 모든 Task 를 삭제하는 HTTP 요청을 전송합니다. Http Status 204 (NoContent) 을 반환합니다.")
    public void deleteTask() throws Exception {
        addTask("할 일1");
        addTask("할 일2");

        mockMvc.perform(
                        delete(TASKS +"/2"))
                .andExpect(status().isNoContent());

        assertEquals(taskRepository.count(), 1);
    }

    private Task addTask(String title) {
        return taskRepository.save(new TaskDto(title));
    }


}