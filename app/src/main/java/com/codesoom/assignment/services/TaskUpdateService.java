package com.codesoom.assignment.services;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.domains.TaskDto;
import com.codesoom.assignment.exceptions.TaskNotFoundException;


/**
 * 할 일의 변경 기능만 담당합니다.
 */
public interface TaskUpdateService {

    /**
     * 할 일의 제목을 수정한 뒤 결과를 반환한다.
     *
     * @param id 요청받은 id
     * @param taskDto 수정할 데이터를 담은 객체
     * @return 수정이 완료된 할 일
     */
    Task updateTaskById(Long id, TaskDto taskDto);

}
