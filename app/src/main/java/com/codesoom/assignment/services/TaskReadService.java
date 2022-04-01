package com.codesoom.assignment.services;

import com.codesoom.assignment.domains.Task;

import java.util.List;

/**
 * 할 일을 조회하는 역할만 담당합니다.
 */
public interface TaskReadService {

    /**
     * 모든 할 일을 반환합니다.
     *
     * @return 모든 할 일
     */
    List<Task> getTasks();

    /**
     * 할 일을 찾아 반환합니다.
     *
     * @param id 할 일의 id
     * @return id와 매핑되는 할 일이 있으면 해당 할 일, 없으면 null
     */
    Task findTaskById(Long id);

}
