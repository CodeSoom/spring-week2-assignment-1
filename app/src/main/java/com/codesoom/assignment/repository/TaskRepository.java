package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;

import java.util.List;
import java.util.Optional;

/**
 * 작업과 관련된 컬렉션 기능을 제공하는 역할을 가지고 있습니다.
 *
 * 사용자의 요청에 따라 작업을 생성, 조회, 삭제 기능을 수행해야 하는 책임을 가지고 있습니다.
 */
public interface TaskRepository {
    /**
     * 요청된 숫자 형식의 taskId와 같은 id를 가진 요소를 찾아 존재하면 요소를 리턴,
     *
     * @param taskId 요청된 숫자 형식의 taskId
     * @return taskId와 같은 id를 가진 요소가 존재하면 요소를 리턴, 존재하지 않으면 null 리턴
     */
    Optional<Task> get(Long taskId);

    /**
     * 인터페이스를 구현한 레포지토리의 저장된 Task를 List 타입으로 리턴합니다.
     *
     * @return 레포지토리의 Tasks를 List로 리턴
     */
    List<Task> getAll();

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
