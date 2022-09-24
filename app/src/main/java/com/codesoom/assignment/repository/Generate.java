package com.codesoom.assignment.repository;

public class GenerateId {
    private Long newId;

    public synchronized Long generateId() {
        newId += 1;
        return newId;
    }

}
