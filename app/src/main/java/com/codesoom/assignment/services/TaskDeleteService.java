package com.codesoom.assignment.services;

/**
 *  할 일을 삭제하는 역할만 담당합니다.
 */
public interface TaskDeleteService {

    /**
     * 할 일을 삭제합니다.
     *
     * @param id 사용자가 입력한 id
     */
    void deleteTaskById(Long id);

}
