package com.codesoom.assignment.repository;

import com.codesoom.assignment.exception.TaskIdNotFoundException;
import com.codesoom.assignment.model.Task;

import java.util.List;

public interface TaskRepository {
    /**
     * 데이터베이스에 입력받은 할 일을 저장한 후
     * 저장된 할 일을 리턴한다.
     * @param task 사용자가 입력한 할 일
     * @return 저장된 할 일
     */
    Task save(Task task);

    /**
     * 데이터베이스에 저장된 모든 할 일들을 리턴한다.
     * @return 데이터베이스에 저장된 모든 할일
     */
    List<Task> findAll();

    /**
     * 데이터베이스에서 검색요청받은 할 일 ID로 할 일을 조회 후
     * 검색된 할 일을 리턴한다.
     * @param id 검색요청 할 일 ID
     * @return 검색된 할 일
     * @throws TaskIdNotFoundException 할 일을 못 찾을 경우 발생하는 예외
     */
    Task findById(Long id);

    /**
     * 데이터베이스에서 수정요청받은 할 일 ID의 할 일을
     * 수정된 할 일 내역으로 변경하고 변경된 할 일을 리턴한다.
     * @param newTask 수정된 할 일
     * @return 변경된 할 일
     * @throws TaskIdNotFoundException 할 일을 못 찾을 경우 발생하는 예외
     */
    Task update(Task newTask);

    /**
     * 데이터베이스에서 삭제요청 받은 할 일 ID의 할 일 내역을 삭제 후
     * 삭제된 할 일을 리턴한다.
     * @param id 삭제요청 할 일 ID
     * @return 삭제된 할 일
     * @throws TaskIdNotFoundException 할 일을 못 찾을 경우 발생하는 예외
     */
    Task delete(Long id);
}
