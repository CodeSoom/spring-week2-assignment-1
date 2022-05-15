package com.codesoom.assignment.interfaces;

import com.codesoom.assignment.DefaultTask;

import java.util.List;


/**
 * Task 엔티티 객체에 대한 목록 및 상세 조회
 * <p>
 * All Known Implementing Classes:
 * TaskLoadingRepository
 * </p>
 */
public interface LoadingRepository {
    /**
     * 모든 Task 객체를 List의 형태로 불러온다
     * <p>
     * @return Task 엔티티 객체를 내부 요소로 하는 List Collection 객체
     * </p>
     */
    List<DefaultTask> tasksAll();

    /**
     * 매개변수로 전달 받은 id에 해당하는 Task 객체를 불러온다
     * <p>
     * @param id Task 객체의 Id에 해당하는 객체
     * @return Task Task 엔티티 객체
     * </p>
     */
    DefaultTask taskBy(Long id);

    /**
     * 매개변수로 전달 받은 id에 해당하는 Task 객체의 존재 여부를 알려준다
     * <p>
     * @param id Task 객체의 Id에 해당하는 객체
     * @return id에 해당하는 Task 객체가 존재하면 True, 존재하지 않으면 False를 반환한다
     * </p>
     */
    boolean notPresent(Long id);

    /**
     * Task 엔티티 객체를 갱신할 수 있는 객체를 반환한다
     * <p>
     * @return Task 객체를 갱신할 수 있는 ManipulatingRepository 객체 반환
     * </p>
     */
    ManipulatingRepository manipulator();
}
