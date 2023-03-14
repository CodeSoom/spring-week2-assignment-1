package com.codesoom.assignment.service;

public class IdService {
    private Long newId = 0L;

    Long generateId() {
        newId += 1;
        return newId;
    }

}

