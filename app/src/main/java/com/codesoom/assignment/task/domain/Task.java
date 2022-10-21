package com.codesoom.assignment.task.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Task {
    private final Long id;
    private final String title;
}
