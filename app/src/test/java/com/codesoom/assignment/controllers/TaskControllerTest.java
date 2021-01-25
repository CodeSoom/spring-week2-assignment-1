package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.services.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TaskService taskService;

    @Test
    public void readTaskList() throws Exception {
        this.createTask("task1");
        this.createTask("task2");
        List<Task> tasks = taskService.getTasks();

        mockMvc.perform(get("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(tasks)));
    }

    @Test
    public void createTask() throws Exception {
        Task noIdTask = new Task("task1");

        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(noIdTask)))
                .andExpect(status().isCreated());
    }

    @Test
    public void readTask() throws Exception {
        Task task = this.createTask("task1");

        mockMvc.perform(get("/tasks/{id}", task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("title").exists())
                .andExpect(status().isOk());
    }

    @Test
    public void readByNotExistId() throws Exception {
        Long notExistId = -1L;

        mockMvc.perform(get("/tasks/{id}", notExistId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").doesNotExist())
                .andExpect(jsonPath("title").doesNotExist())
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateTask() throws Exception {
        Task task = this.createTask("task");
        Task newTask = new Task(task.getId(), "newTask");

        mockMvc.perform(put("/tasks/{id}", newTask.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTask)))
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("title").exists())
                .andExpect(status().isOk());
    }

    @Test
    public void updateByNotExistTask() throws Exception {
        Task task = new Task(1L, "task");

        mockMvc.perform(put("/tasks/{id}", task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(jsonPath("id").doesNotExist())
                .andExpect(jsonPath("title").doesNotExist())
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteTask() throws Exception {
        Task task = this.createTask("task");

        mockMvc.perform(delete("/tasks/{id}", task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").doesNotExist())
                .andExpect(jsonPath("title").doesNotExist())
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteByNotExistTask() throws Exception {
        Long notExistId = -1L;

        mockMvc.perform(delete("/tasks/{id}", notExistId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").doesNotExist())
                .andExpect(jsonPath("title").doesNotExist())
                .andExpect(status().isNotFound());
    }


    private Task createTask(String title) {
        Task task = new Task(title);

        return this.taskService.addTask(task);
    }

}