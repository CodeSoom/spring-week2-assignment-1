package com.codesoom.assignment.repository;

/**
 * This class stores id for Task and manipuldates id
 */
public class IdGenerator {
    private static Long newId = 0L;

    /**
     * Returns a newly generated id
     *
     * @return newId
     */
    public static synchronized Long generateId() {
        newId += 1;
        return newId;
    }
}
