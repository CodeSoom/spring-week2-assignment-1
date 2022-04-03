package com.codesoom.assignment.services;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.domains.TaskDto;
import com.codesoom.assignment.exceptions.TaskInvalidFormatException;
import com.codesoom.assignment.repositories.TaskRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;


/** 할 일의 생성 기능만 구현한 서비스 입니다. */
@Service
public class TaskCreateServiceImpl implements TaskCreateService{

    private final TaskRepository repository;

    public TaskCreateServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task addTask(TaskDto taskDto) {
        validateTaskDto(taskDto);

        Task newTask = taskDto.toTask(repository.generateId());
        repository.save(newTask);
        return newTask;
    }

    /**
     * 사용자가 입력한 데이터의 유효성을 검증합니다.
     *
     * @param taskDto 사용자가 입력한 데이터
     * @throws TaskInvalidFormatException
     *         데이터의 유효성 검증을 통과하지 못한 경우
     */
    private void validateTaskDto(TaskDto taskDto) {
        if (Strings.isBlank(taskDto.getTitle())) {
            throw new TaskInvalidFormatException("title은 필수로 입력해야 합니다.");
        }
    }
}
