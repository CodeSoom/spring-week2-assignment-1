package com.codesoom.assignment.service;

public class AutoIdService {

    private Long newId = 0L;

    Long generateId() {
        newId += 1;
        return newId;
    }

}

