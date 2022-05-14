package com.codesoom.assignment.interfaces;

import com.codesoom.assignment.DefaultTask;


/**
 * Task 엔티티 객체 갱신
 * <p>
 * All Known Implementing Classes:
 * TaskManipulatingRepository
 * </p>
 */
public interface ManipulatingRepository {
    /**
     * Task 엔티티 객체 저장
     * <p>
     * Method Parameters:
     * Task task - Task 엔티티 객체
     * </p>
     * <p>
     * Method Returns:
     * Task - Task 엔티티 객체
     * </p>
     */
    DefaultTask save(DefaultTask task);

    /**
     * Task 엔티티 객체 수정
     * <p>
     * Method Parameters:
     * Task task - Task 엔티티 객체
     * </p>
     * <p>
     * Method Returns:
     * Task - Task 엔티티 객체
     * </p>
     */
    DefaultTask update(DefaultTask task);

    /**
     * Task 엔티티 객체 삭제
     * <p>
     * Method Parameters:
     * Long id - Task 객체의 Id에 해당하는 객체
     * </p>
     */
    void deleteBy(Long id);
}
