package com.codesoom.assignment.task;

import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TaskController {

    private static List<Task> tasks = Arrays.asList(
            new Task(1L, "title1"),
            new Task(2L, "title2"),
            new Task(3L, "title3")
    );
}
