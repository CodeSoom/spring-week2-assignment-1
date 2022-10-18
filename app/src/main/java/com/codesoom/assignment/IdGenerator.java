package com.codesoom.assignment;

public class IdGenerator {
    private static Long id = 0L;

    private IdGenerator() {
    }

    public static Long createId() {
        return ++id;
    }
}
