package com.codesoom.assignment.interfaces;

/**
 * Summary:
 * '할 일'에 대한 Entity 객체
 * <p>
 * All Known Implementing Classes:
 * Task
 * <p>
 * Method Returns:
 * Long id - Task 객체의 유일성을 표현하는 객체
 * String title - Task 객체의 제목에 해당하는 객체
 */
public interface DefaultTask {
    Long id();

    String title();
}
