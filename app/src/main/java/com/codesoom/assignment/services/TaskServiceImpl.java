package com.codesoom.assignment.services;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.domains.TaskDto;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * {@link TaskService}를 상속받아 할 일의 CRUD를 구현합니다.
 */
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(repository.getTasks().values());
    }

    /**
     * 새로운 할 일을 추가한 뒤 결과를 반환한다.
     *
     * @param taskDto 사용자가 입력한 정보
     * @return 저장된 할 일
     */
    @Override
    public Task addTask(TaskDto taskDto) {
        validateTaskDto(taskDto);

        Task newTask = taskDto.toTask(repository.generateId());
        return repository.save(newTask);
    }

    /**
     * id로 할 일을 찾아 반환한다.
     *
     * @param id 요청받은 id
     * @return 요청받은 id로 찾은 할 일
     * @throws NoSuchElementException id와 일치하는 값이 없을 때
     */
    @Override
    public Task findTaskById(Long id) {
        final Task task = repository.findById(id);
        if (task == null) {
            throw new NoSuchElementException("요청하신 id와 일치하는 값이 없습니다.");
        }
        return task;
    }

    /**
     * 할 일의 제목을 수정한 뒤 결과를 반환한다.
     *
     * @param id 요청받은 id
     * @param taskDto 수정할 데이터를 담은 객체
     * @return 수정이 완료된 할 일
     */
    @Override
    public Task updateTaskById(Long id, TaskDto taskDto) {
        validateTaskDto(taskDto);

        final Task task = findTaskById(id);
        task.updateTitle(taskDto.getTitle());

        repository.update(task.getId(), task);

        return task;
    }

    /** id로 할 일을 삭제한다. */
    @Override
    public void deleteTaskById(Long id) {
        final Task task = findTaskById(id);
        repository.remove(task.getId());
    }

    /**
     *  할 일을 추가하거나 수정할 때 유효성 검사를 처리합니다.
     *
     *  @throws IllegalArgumentException 할 일이 null 이거나 제목을 입력하지 않을 경우
     */
    private void validateTaskDto(TaskDto taskDto) {
        if (taskDto == null) {
            throw new IllegalArgumentException("유효하지 않은 형식입니다.");
        }
        if (taskDto.getTitle() == null || "".equals(taskDto.getTitle())) {
            throw new IllegalArgumentException("title은 필수로 입력해야 합니다.");
        }
    }

}
