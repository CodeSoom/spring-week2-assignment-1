package com.codesoom.assignment.services;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.domains.TaskDto;
import com.codesoom.assignment.exceptions.TaskInvalidFormatException;
import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.repositories.TaskRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;


/**
 * 할 일의 변경 기능만 구현한 서비스 입니다.
 */
@Service
public class TaskUpdateServiceImpl implements TaskUpdateService{

    private final TaskRepository repository;

    public TaskUpdateServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task updateTaskById(Long id, TaskDto taskDto) {
        validateTaskDto(taskDto);

        final Task task = findTaskById(id);
        task.updateTitle(taskDto.getTitle());

        repository.update(task.getId(), task);

        return task;
    }

    /**
     * id로 할 일을 찾아 반환합니다.
     * @throws TaskNotFoundException id와 매핑되는 할 일을 찾지 못한 경우
     */
    private Task findTaskById(Long id) {
        final Task task = repository.findById(id);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        return task;
    }

    /**
     * 사용자가 입력한 데이터의 유효성을 검증합니다.
     *
     * @param taskDto 사용자가 입력한 데이터
     * @throws TaskInvalidFormatException 데이터의 유효성 검증을 통과하지 못한 경우
     */
    private void validateTaskDto(TaskDto taskDto) {
        if (Strings.isBlank(taskDto.getTitle())) {
            throw new TaskInvalidFormatException("title은 필수로 입력해야 합니다.");
        }
    }

}
