package com.codesoom.assignment.services;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.domains.TaskDto;
import com.codesoom.assignment.exceptions.TaskInvalidFormatException;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.stereotype.Service;


/** 할 일의 생성 기능만 구현한 서비스 입니다. */
@Service
public class TaskCreateServiceImpl implements TaskCreateService{

    private final TaskRepository repository;

    public TaskCreateServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    /**
     * 새로운 할 일을 추가한 뒤 결과를 반환한다.
     *
     * @param taskDto 사용자가 입력한 데이터
     * @return 저장된 할 일
     */
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
     * @throws TaskInvalidFormatException 데이터의 유효성 검증을 통과하지 못한 경우
     */
    private void validateTaskDto(TaskDto taskDto) {
        if (taskDto.getTitle() == null || "".equals(taskDto.getTitle())) {
            throw new TaskInvalidFormatException("title은 필수로 입력해야 합니다.");
        }
    }
}
