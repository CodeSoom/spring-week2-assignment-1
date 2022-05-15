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
     * @param task Task 엔티티 객체
     * @return Task 엔티티 객체
     * </p>
     */
    DefaultTask save(DefaultTask task);

    /**
     * Task 엔티티 객체 수정
     * <p>
     * @param task Task 엔티티 객체
     * @return Task 엔티티 객체
     * </p>
     */
    DefaultTask update(DefaultTask task);

    /**
     * Task 엔티티 객체 삭제
     * <p>
     * @param id Task 객체의 Id에 해당하는 객체
     * </p>
     */
    void deleteBy(Long id);
}
