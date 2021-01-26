// TODO
// 1. Read collection
// 2. Read item/element
// 3. Create
// 4. Update
// 5. Delete

package com.codesoom.assignment;

import com.codesoom.assignment.models.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class DemoHttpHandler implements HttpHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    public DemoHttpHandler() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("First");

        tasks.add(task);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        // REST - CRUD
        // 1. Method - GET, POST, PUT/PATCH, DELETE...
        // 2. Path - "/", "/tasks", "/tasks/1"
        // 3. Headers, Body(Content)

        String method = exchange.getRequestMethod();
        URI uri = exchange.getRequestURI();
        String path = uri.getPath();

<<<<<<< HEAD
        InputStream inputStream = exchange.getRequestBody();
        String body = new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .collect(Collectors.joining("\n"));

        System.out.println(method + " " + path);
        if (!body.isEmpty()) {
            Task task = toTask(body);
            tasks.add(task);
=======
        System.out.println(method + " " + path);

        if (path.equals("/tasks")) {
            handleCollection(exchange, method);
            return;
        }

        if (path.startsWith("/tasks/")) {
            Long id = Long.parseLong(path.substring("/tasks/".length()));
            handleItem(exchange, method, id);
            return;
        }

        send(exchange, 200, "Hello, World!");
    }

    private void handleCollection(HttpExchange exchange, String method)
            throws IOException {
        if (method.equals("GET")) {
            handleList(exchange, 200, tasksToJSON());
        }

        if (method.equals("POST")) {
            handleCreate(exchange);
>>>>>>> e7cf222 (Spring Web)
        }
    }

    private void handleItem(HttpExchange exchange, String method, Long id)
            throws IOException {
        Task task = findTask(id);

        if (task == null) {
            send(exchange, 404, "");
            return;
        }

        if (method.equals("GET")) {
            handleDetail(exchange, task);
        }

        if (method.equals("PUT") || method.equals("PATCH")) {
            handleUpdate(exchange, task);
        }

        if (method.equals("DELETE")) {
            handleDelete(exchange, task);
        }
    }

    private void handleList(HttpExchange exchange, int i, String s) throws IOException {
        send(exchange, i, s);
    }

    private void handleDetail(HttpExchange exchange, Task task)
            throws IOException {
        send(exchange, 200, toJSON(task));
    }

    private void handleCreate(HttpExchange exchange) throws IOException {
        String body = getBody(exchange);

        Task task = toTask(body);
        task.setId(generateId());
        tasks.add(task);

        send(exchange, 201, toJSON(task));
    }

    private void handleUpdate(HttpExchange exchange, Task task)
            throws IOException {
        String body = getBody(exchange);
        Task source = toTask(body);

        task.setTitle(source.getTitle());

        send(exchange, 200, toJSON(task));
    }

    private void handleDelete(HttpExchange exchange, Task task) throws IOException {
        tasks.remove(task);

        send(exchange, 200, "");
    }

    private void send(HttpExchange exchange, int statusCode, String content)
            throws IOException {
        exchange.sendResponseHeaders(statusCode, content.getBytes().length);

        OutputStream outputStream = exchange.getResponseBody();

        outputStream.write(content.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    private String tasksToJSON() throws IOException {
        return toJSON(tasks);
    }

    private String getBody(HttpExchange exchange) {
        InputStream inputStream = exchange.getRequestBody();
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    private Task findTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst().orElse(null);
    }

    private Task toTask(String content) throws JsonProcessingException {
        return objectMapper.readValue(content, Task.class);
    }

    private String toJSON(Object object) throws IOException {
        OutputStream outputStream = new ByteArrayOutputStream();
        objectMapper.writeValue(outputStream, object);

        return outputStream.toString();
    }

<<<<<<< HEAD
        try (OutputStream outputStream = new ByteArrayOutputStream()) {
            objectMapper.writeValue(outputStream, tasks);

            return outputStream.toString();
        }
    }
=======
    private Long generateId() {
        newId += 1;
        return newId;
    }

>>>>>>> e7cf222 (Spring Web)
}
