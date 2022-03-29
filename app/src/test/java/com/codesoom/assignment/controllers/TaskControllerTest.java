package com.codesoom.assignment.controllers;

import com.codesoom.assignment.App;
import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.dtos.TaskDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

// TODO: GET /tasks - 모든 Task 조회
// TODO: GET /tasks/{id} - 1개의 Task 조회
// TODO: POST /tasks - Task 1개 추가
// TODO: PUT/PATCH /tasks/{id} - Task 수정
// TODO: DELETE /tasks/{id} - 할 일 삭제

class TaskControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String LOCAL_HOST_TASKS = "http://localhost:8080/tasks";

    @BeforeAll
    public static void turnOnServer() {
         App.main(new String[]{});
    }

    @Test
    @DisplayName("Task 를 추가하면, 상태코드 CREATED(201)과 추가한 Task 의 JSON 문자열을 반환한다.")
    public void testAddTask() throws IOException {
        TaskDto taskDto = new TaskDto("할 일");
        String taskDtoJson = objectMapper.writeValueAsString(taskDto);
        String taskJson = objectMapper.writeValueAsString(new Task(1L, "할 일"));

        testResponse(buildHttpUriRequest(HttpMethod.POST, LOCAL_HOST_TASKS, taskDtoJson), HttpStatus.CREATED, taskJson);
    }

    private HttpUriRequest buildHttpUriRequest(HttpMethod method, String url, String body) {
        HttpUriRequest httpUriRequest;

        switch (method) {
            case POST:
                HttpPost postRequest = new HttpPost(url);
                StringEntity requestEntity = new StringEntity(body, ContentType.APPLICATION_JSON);
                postRequest.setEntity(requestEntity);

                httpUriRequest = postRequest;
                break;
            default:
                httpUriRequest = new HttpGet(url);
        }

        return httpUriRequest;
    }

    private void testResponse(HttpUriRequest request, HttpStatus expectedStatusCode, String expectedBody) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        InputStream content = response.getEntity().getContent();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content));

        String body = bufferedReader.lines().collect(Collectors.joining("\n"));
        int statusCode = response.getStatusLine().getStatusCode();

        System.out.println("----------HTTP RESPONSE-----------");
        System.out.println("statusCode = " + statusCode);
        System.out.println("body = " + body);
        System.out.println("----------------------------------");

        Assertions.assertEquals(expectedStatusCode.value(), statusCode);
        Assertions.assertEquals(expectedBody, body);
    }
}