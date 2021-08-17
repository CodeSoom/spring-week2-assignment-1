package com.codesoom.assignment.models;

public class TaskIdGenerator {
    private static Long sequence = 1L;

    public static Long getSequence() {
        return sequence++;
    }

    public static Long getLastSequence() {
        return sequence - 1;
    }
}
