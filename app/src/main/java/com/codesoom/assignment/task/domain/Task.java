package com.codesoom.assignment.task.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Task {
    private Long id;
    private String title;
}
