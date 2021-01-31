package com.codesoom.assignment.application;

import com.codesoom.assignment.domain.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskJsonTransfer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Optional<Task> jsonStringToTask(String jsonString) {
        try {
            JsonTask task = objectMapper.readValue(jsonString, JsonTask.class);
            return Optional.of(new Task(-1L, task.title));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }

    public Optional<JsonTask> taskToJson(Task task) {
        JsonTask jsonTask = new JsonTask(task.getId(), task.getTitle());
        return Optional.of(jsonTask);
    }

    public Optional<List<JsonTask>> taskListToJson(List<Task> tasks) {
        List<JsonTask> jsonTaskList = tasks
            .stream()
            .map(it -> new JsonTask(it.getId(), it.getTitle()))
            .collect(Collectors.toList());
        return Optional.of(jsonTaskList);
    }
}
