package com.codesoom.assignment.repository;

public class IdGenerator {
    private static Long newId = 0L;

    public static synchronized Long generateId() {
        newId += 1;
        return newId;
    }
}
