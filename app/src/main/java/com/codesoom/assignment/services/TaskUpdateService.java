package com.codesoom.assignment.services;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.domains.TaskDto;


/**
 * 할 일의 변경 기능만 담당합니다.
 */
public interface TaskUpdateService {

    /**
     * id와 매핑되는 할 일의 정보를 수정합니다.
     *
     * @param id 사용자가 입력한 resource id
     * @param taskDto 사용자가 입력한 데이터
     */
    Task updateTaskById(Long id, TaskDto taskDto);

}
