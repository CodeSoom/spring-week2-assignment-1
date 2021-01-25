package com.codesoom.assignment;

import com.codesoom.assignment.controllers.TaskController;
import com.codesoom.assignment.domain.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.Charset;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    public static final MediaType APPLICATION_JSON_UTF8 =
            new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetEmptyTaskList() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @Test
    public void testCreateTask() throws Exception {
        Task task = new Task(0, "Get Sleep");
        mockMvc.perform(post("/tasks").contentType(APPLICATION_JSON_UTF8).content("{\"title\":\"Get Sleep\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(task)));
    }

    @Test
    public void testGetCreatedTask() throws Exception {
        Task expectedTask = new Task(0, "Get Sleep");
        MvcResult mvcResult = mockMvc.perform(post("/tasks").contentType(APPLICATION_JSON_UTF8).content("{\"title\":\"Get Sleep\"}"))
                .andExpect(status().isOk())
                .andReturn();
        Task createdTask = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Task.class);
        assertEquals("id", expectedTask.getId(), createdTask.getId());
        assertEquals("title", expectedTask.getTitle(), createdTask.getTitle());

        mockMvc.perform(get("/tasks/" + createdTask.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(createdTask)));
    }
}
