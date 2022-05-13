package com.codesoom.assignment.interfaces;

import com.codesoom.assignment.Task;

import java.util.List;

/**
 * Summary:
 * Task 엔티티 객체에 대한 목록 및 상세 조회
 * <p>
 * All Known Implementing Classes:
 * TaskLoadingRepository
 * <p>
 * Method Parameters:
 * Long id - Task 객체의 Id에 해당하는 객체
 * <p>
 * Method Returns:
 * Task - Task 엔티티 객체
 * ManipulatingRepository - Task 객체를 갱신할 수 있는 객체
 */
public interface LoadingRepository {
    List<Task> tasksAll();

    Task taskBy(Long id);

    boolean notPresent(Long id);

    ManipulatingRepository manipulator();
}
