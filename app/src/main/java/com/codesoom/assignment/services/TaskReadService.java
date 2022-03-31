package com.codesoom.assignment.services;

import com.codesoom.assignment.domains.Task;

import java.util.List;

/**
 * 할 일을 조회하는 역할만 담당합니다.
 */
public interface TaskReadService {

    /** 모든 할 일을 반환합니다. */
    List<Task> getTasks();

    /**
     * id와 매핑되는 할 일이 있으면 해당 할 일을, 없으면 null을 반환합니다.
     */
    Task findTaskById(Long id);

}
