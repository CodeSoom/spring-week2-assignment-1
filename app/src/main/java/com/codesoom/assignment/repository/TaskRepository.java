package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;

/**
 * Task를 다루는 저장소입니다.
 * Task를 삽입, 삭제, 조회하는 기능을 가지고 있습니다.
 */
public interface TaskRepository {
    /**
     * 요청된 숫자 형식의 taskId와 같은 id를 가진 Task를 찾아 리턴합니다.
     *
     * @param taskId 요청된 숫자 형식의 taskId
     * @return taskId와 같은 id를 가진 Task 리턴
     */
    Task get(Long taskId);

    /**
     * 입력 받은 title을 가진 Task를 생성해서 리턴합니다.
     *
     * @param title 입력 받은 title
     * @return title을 가진 Task 생성 후 리턴
     */
    Task add(String title);

    /**
     * 입력 받은 숫자 형식의 taskId와 같은 id를 가진 Task를 찾아 있으면 제거합니다.
     *
     * @param taskId 입력 받은 숫자 형식의 taskId
     */
    void remove(Long taskId);
}
