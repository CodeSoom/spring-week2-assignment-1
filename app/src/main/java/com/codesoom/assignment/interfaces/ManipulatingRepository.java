package com.codesoom.assignment.interfaces;

import com.codesoom.assignment.Task;

/**
 * Summary:
 * Task 엔티티 객체에 대한 갱신
 * <p>
 * All Known Implementing Classes:
 * TaskManipulatingRepository
 * <p>
 * Method Parameters:
 * Task task - Task 엔티티 객체
 * Long id - Task 객체의 Id에 해당하는 객체
 * <p>
 * Method Returns:
 * Task - Task 엔티티 객체
 */
public interface ManipulatingRepository {

    void save(Task task);

    void update(Task task);

    void deleteBy(Long id);


    Task taskSaved();

    Task taskUpdated();
}
