package com.codesoom.assignment.repository;

/**
 * Generates a new id for Task
 */
public class IdGenerator {
    private static Long newId = 0L;

    /**
     * Returns a newly generated id.
     *
     * @return newId
     */
    public static synchronized Long generateId() {
        newId += 1;
        return newId;
    }
}
