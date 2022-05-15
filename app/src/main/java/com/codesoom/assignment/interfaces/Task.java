package com.codesoom.assignment.interfaces;

/**
 * '할 일'에 대한 엔티티 객체
 * <p>
 * All Known Implementing Classes:
 * Task
 * </p>
 */
public interface Task {
    /**
     * Task 엔티티 객체의 멤버변수 id를 반환한다
     * <p>
     * @return Task객체의 유일성을 표현하는 객체
     * </p>
     */
    Long id();

    /**
     * Task 엔티티 객체의 멤버변수 title을 반환한다
     * <p>
     * @return Task객체의 제목에 해당하는 객체 반환
     * </p>
     */
    String title();
}
