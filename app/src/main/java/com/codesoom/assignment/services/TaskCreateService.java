package com.codesoom.assignment.services;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.domains.TaskDto;

/**
 * 할 일을 생성하는 역할만 담당합니다.
 */
public interface TaskCreateService {

    /**
     * 사용자가 입력한 데이터로 할 일을 저장합니다.
     *
     * @param taskDto 사용자가 입력한 할 일
     * @return 저장된 할 일
     */
    Task addTask(TaskDto taskDto);

}
