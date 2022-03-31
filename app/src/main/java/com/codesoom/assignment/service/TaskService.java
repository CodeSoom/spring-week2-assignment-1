package com.codesoom.assignment.service;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.dto.TaskEditDto;
import com.codesoom.assignment.dto.TaskSaveDto;
import com.codesoom.assignment.dto.TaskViewDto;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 할일에 대한 요청을 처리합니다.
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * 할 일 목록을 변환해 리턴합니다.
     * @see TaskViewDto
     */
    public List<TaskViewDto> getTaskDtoList() {
        Map<Long, Task> tasks = taskRepository.findAll();

        return tasks.values().stream()
                .map(TaskViewDto::from)
                .collect(Collectors.toList());
    }

    /**
     * 할 일을 생성하고 생성된 할 일을 리턴합니다.
     * @param taskSaveDto 생성에 필요한 데이터
     */
    public Task save(final TaskSaveDto taskSaveDto) {
        Task task = taskSaveDto.toEntity();
        taskRepository.save(taskSaveDto.toEntity());
        return task;
    }

    /**
     * 주어진 아이디와 일치하는 할일 을 {@code Optional}로 래핑해서 리턴합니다.
     * <p>일치하는게 없다면 비어있는 {@code Optional}을 리턴 합니다.</p>
     * @param taskId 할 일의 고유 아이디
     */
    public Optional<Task> getTask(final Long taskId) {
        Task task = taskRepository.findById(taskId);
        if (task == null) {
            return Optional.empty();
        }
        return Optional.of(task);
    }

    /**
     * 할 일을 삭제합니다.
     * @param taskId 할 일의 고유 아이디
     */
    public void delete(final Long taskId) {
        taskRepository.deleteById(taskId);
    }

    /**
     * 할 일을 대체합니다.
     * @param task        대체 대상인 할 일
     * @param taskEditDto 대체될 데이터
     */
    public void replaceTask(final Task task, final TaskEditDto taskEditDto) {
        task.setTitle(taskEditDto.getTitle());
    }

    /**
     * 할 일을 부분적으로 수정합니다.
     *
     * @param task        수정될 할 일
     * @param taskEditDto 수정될 데이터
     */
    public void updateTask(final Task task, final TaskEditDto taskEditDto) {
        if (StringUtils.hasText(taskEditDto.getTitle())) {
            task.setTitle(taskEditDto.getTitle());
        }
    }
}
