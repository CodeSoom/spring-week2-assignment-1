package com.codesoom.assignment.task.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Task {
    private final Long id;
    private final String title;
}
