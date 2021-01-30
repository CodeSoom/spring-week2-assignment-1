package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskDto;
import com.codesoom.assignment.repositories.TaskRepository;
import com.codesoom.assignment.services.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("TaskController 클래스")
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TaskService taskService;

    @Autowired
    TaskRepository taskRepository;

    @AfterEach
    void clearTasks() {
        taskRepository.removeAll();
    }

    @Nested
    @DisplayName("GET 메서드 요청은")
    class Describe_GET {
        @Nested
        @DisplayName("저장된 task가 여러 개 있다면")
        class Context_with_tasks {
            List<Task> tasks;

            @BeforeEach
            void prepareTasks() {
                Task task1 = new Task("title1");
                Task task2 = new Task("title2");
                taskService.addTask(task1);
                taskService.addTask(task2);
                tasks = taskService.getTasks();
            }

            @Test
            @DisplayName("200코드와 저장된 task들을 리턴한다")
            void it_returns_200_and_tasks() throws Exception {
                mockMvc.perform(get("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().string(objectMapper.writeValueAsString(tasks)));
            }
        }

        @Nested
        @DisplayName("저장된 task가 없다면")
        class Context_with_empty_task {
            @Test
            @DisplayName("200코드와 빈 배열을 리턴한다")
            void it_returns_200_and_empty_tasks() throws Exception {
                mockMvc.perform(get("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().string("[]"));
            }
        }

        @Nested
        @DisplayName("존재하는 task id를 요청한다면")
        class Context_with_valid_task_id {
            Task task;

            @BeforeEach
            void prepareTask() {
                task = new Task("title");
                taskService.addTask(task);
            }

            @Test
            @DisplayName("200코드와 task를 리턴한다")
            void it_returns_200_and_task() throws Exception {
                mockMvc.perform(get("/tasks/{id}", task.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("id").exists())
                        .andExpect(jsonPath("title").exists())
                        .andExpect(status().isOk());
            }
        }

        @Nested
        @DisplayName("존재하지 않는 task id를 요청한다면")
        class Context_with_invalid_task_id {
            Long notExistId = -1L;

            @Test
            @DisplayName("404코드와 에러정보를 리턴한다")
            void it_returns_404_and_error_info() throws Exception {
                mockMvc.perform(get("/tasks/{id}", notExistId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("id").doesNotExist())
                        .andExpect(jsonPath("title").doesNotExist())
                        .andExpect(jsonPath("statusCode").exists())
                        .andExpect(jsonPath("timestamp").exists())
                        .andExpect(jsonPath("message").exists())
                        .andExpect(jsonPath("description").exists())
                        .andExpect(status().isNotFound());
            }
        }
    }

    @Nested
    @DisplayName("POST 메서드 요청은")
    class Describe_POST {
        @Nested
        @DisplayName("생성하려는 task가 유효하다면")
        class Context_with_valid_task {
            TaskDto taskDto = new TaskDto("title");

            @Test
            @DisplayName("201코드와 생성된 task를 리턴한다")
            void it_returns_201_and_created_task() throws Exception {
                mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskDto)))
                        .andExpect(status().isCreated());
            }
        }

        @Nested
        @DisplayName("생성하려는 task가 유효하지 않다면")
        class Context_with_in_valid_task {
            TaskDto taskDto = new TaskDto("");

            @Test
            @DisplayName("400코드를 에러 정보를 리턴한다")
            void it_returns_400() throws Exception {
                mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskDto)))
                        .andExpect(jsonPath("statusCode").exists())
                        .andExpect(jsonPath("timestamp").exists())
                        .andExpect(jsonPath("message").exists())
                        .andExpect(jsonPath("description").exists())
                        .andExpect(status().isBadRequest());
            }
        }
    }

    @Nested
    @DisplayName("PUT 매서드 요청은")
    class Describe_PUT {
        @Nested
        @DisplayName("수정하려는 task, task id가 유효하다면")
        class Context_with_valid_task_and_task_id {
            Task task;
            TaskDto taskDto;

            @BeforeEach
            void prepareTask() {
                task = new Task("title");
                taskService.addTask(task);
                taskDto = new TaskDto("newTask");
            }

            @Test
            @DisplayName("200코드와 수정된 task를 리턴한다")
            void it_returns_200_and_updated_task() throws Exception {
                mockMvc.perform(put("/tasks/{id}", task.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskDto)))
                        .andExpect(jsonPath("id").exists())
                        .andExpect(jsonPath("title").exists())
                        .andExpect(status().isOk());
            }
        }

        @Nested
        @DisplayName("수정하려는 task가 유효하지 않다면")
        class Context_with_in_valid_task {
            Task task;
            TaskDto taskDto;

            @BeforeEach
            void prepareTask() {
                task = new Task("title");
                taskService.addTask(task);
                taskDto = new TaskDto("");
            }

            @Test
            @DisplayName("400코드와 에러정보를 리턴한다")
            void it_returns_400() throws Exception {
                mockMvc.perform(put("/tasks/{id}", task.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskDto)))
                        .andExpect(jsonPath("statusCode").exists())
                        .andExpect(jsonPath("timestamp").exists())
                        .andExpect(jsonPath("message").exists())
                        .andExpect(jsonPath("description").exists())
                        .andExpect(status().isBadRequest());
            }
        }

        @Nested
        @DisplayName("존재하지 않는 task id로 수정한다면")
        class Context_with_invalid_task {
            Long notExistedId = -1L;
            TaskDto taskDto = new TaskDto("newTitle");

            @Test
            @DisplayName("404코드와 에러정보를 리턴한다")
            void it_returns_404() throws Exception {
                mockMvc.perform(put("/tasks/{id}", notExistedId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskDto)))
                        .andExpect(jsonPath("id").doesNotExist())
                        .andExpect(jsonPath("title").doesNotExist())
                        .andExpect(jsonPath("statusCode").exists())
                        .andExpect(jsonPath("timestamp").exists())
                        .andExpect(jsonPath("message").exists())
                        .andExpect(jsonPath("description").exists())
                        .andExpect(status().isNotFound());
            }
        }
    }

    @Nested
    @DisplayName("DELETE 메서드 요청은")
    class Describe_DELETE {
        @Nested
        @DisplayName("존재하는 task id로 삭제한다면")
        class Context_with_valid_task_id {
            Task task;

            @BeforeEach
            void prepareTask() {
                task = new Task("title");
                taskService.addTask(task);
            }

            @Test
            @DisplayName("204코드를 리턴한다")
            void it_returns_204() throws Exception {
                mockMvc.perform(delete("/tasks/{id}", task.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("id").doesNotExist())
                        .andExpect(jsonPath("title").doesNotExist())
                        .andExpect(status().isNoContent());
            }
        }

        @Nested
        @DisplayName("존재하지 않는 task id로 삭제한다면")
        class Context_with_invalid_task_id {
            Long notExistId = -1L;

            @Test
            @DisplayName("404코드와 에러정보를 리턴한다")
            void it_returns_404() throws Exception {
                mockMvc.perform(delete("/tasks/{id}", notExistId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("id").doesNotExist())
                        .andExpect(jsonPath("title").doesNotExist())
                        .andExpect(jsonPath("statusCode").exists())
                        .andExpect(jsonPath("timestamp").exists())
                        .andExpect(jsonPath("message").exists())
                        .andExpect(jsonPath("description").exists())
                        .andExpect(status().isNotFound());
            }
        }
    }

}
