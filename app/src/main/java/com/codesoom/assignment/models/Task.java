package com.codesoom.assignment.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.CrossOrigin;

@Getter
@Setter
@ToString
public class Task {
    private Long id;
    private String title;
}
