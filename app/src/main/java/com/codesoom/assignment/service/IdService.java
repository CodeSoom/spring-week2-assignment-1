package com.codesoom.assignment.service;

import com.codesoom.assignment.domain.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class IdService {
    private Long newId = 0L;

    Long generateId() {
        newId += 1;
        return newId;
    }

}

