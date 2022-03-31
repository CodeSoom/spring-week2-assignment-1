package com.codesoom.assignment.service;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.dto.TaskViewDto;
import com.codesoom.assignment.dto.TaskEditDto;
import com.codesoom.assignment.dto.TaskSaveDto;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO - TaskService에 대한 Javadoc 작성
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * 모든 {@link Task}를 {@link TaskViewDto}로 변환해 {@code List}로 리턴합니다.
     * @return 모든 {@link Task} 를 {@link TaskViewDto} 로 변환한 {@code List}
     */
    public List<TaskViewDto> getTaskDtoList() {
        Map<Long, Task> tasks = taskRepository.findAll();
        return tasks.values().stream()
                .map(TaskViewDto::from)
                .collect(Collectors.toList());
    }

    /**
     * 새로운 {@link Task}를 저장하고 {@link TaskDto}로 변환해 리턴합니다.
     * @param taskSaveDto 생성에 필요한 {@link Task} 데이터
     * @return 생성한 {@link Task}를 변환한 {@link TaskDto}
     */
    public TaskViewDto save(final TaskSaveDto taskSaveDto) {
        Task task = taskSaveDto.toEntity();
        taskRepository.save(task);
        return TaskViewDto.from(task);
    }

    /**
     * taskId와 일치하는 {@link Task}를 리턴 하고, 일치하는게 없다면 비어있는 Optional을 리턴 합니다.
     * </p>
     * @param taskId {@link Task#id}
     * @return 일치하는 {@link Task}가 있다면 Optional<{@link Task}>, 없다면 빈 {@code Optional}
     */
    public Optional<Task> getTask(final Long taskId) {
        Task task = taskRepository.findById(taskId);
        if (task == null) {
            return Optional.empty();
        }
        return Optional.of(task);
    }

    /**
     * {@link Task}를 삭제 합니다.
     * @param taskId {@link Task#id}
     */
    public void delete(final Long taskId) {
        taskRepository.deleteById(taskId);
    }

    /**
     * {@link Task}를 대체합니다.
     * @param task 대체 대상 {@link Task}
     * @param taskEditDto 대체될 데이터
     * @see TaskEditDto
     */
    public void replaceTask(final Task task, final TaskEditDto taskEditDto) {
        task.setTitle(taskEditDto.getTitle());
    }

    /**
     * {@link Task}를 부분적으로 수정합니다.
     * @param task 수정 대상 {@link Task}
     * @param taskEditDto 수정될 데이터
     * @see TaskEditDto
     */
    public void updateTask(final Task task, final TaskEditDto taskEditDto) {
        if (StringUtils.hasText(taskEditDto.getTitle())) {
            task.setTitle(taskEditDto.getTitle());
        }
    }
}
