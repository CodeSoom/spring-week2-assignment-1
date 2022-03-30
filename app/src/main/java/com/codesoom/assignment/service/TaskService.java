package com.codesoom.assignment.service;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.dto.TaskEditDto;
import com.codesoom.assignment.dto.TaskSaveDto;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * 모든 Task를 TaskDto로 변환해 {@code List<TaskDto>}로 리턴합니다.
     * @return 모든 Task 를 TaskDto 로 변환한 {@code List<TaskDto>}
     */
    public List<TaskDto> getTaskDtoList() {
        Map<Long, Task> tasks = taskRepository.findAll();
        return tasks.values().stream()
                .map(TaskDto::from)
                .collect(Collectors.toList());
    }

    /**
     * 새로운 Task를 저장합니다.
     * @param taskSaveDto 생성할 Task 데이터
     * @return 생성한 Task를 변환한 TaskDto (수정 에정)
     */
    public TaskDto save(final TaskSaveDto taskSaveDto) {
        Task task = new Task(taskSaveDto.getTitle());
        taskRepository.save(task);
        return TaskDto.from(task);
    }

    /**
     * 일치하는?? Task 를 리턴합니다.
     * <p>
     * 존재 하지 않다면 비어있는 Optional  를 리턴 합니다.
     * @param taskId Task 아이디
     * @return 조회 대상이 있다면 {@code Optional.of(Task)}, 없다면 빈 {@code Optional}
     */
    public Optional<Task> getTask(final Long taskId) {
        Task task = taskRepository.findById(taskId);
        if (task == null) {
            return Optional.empty();
        }
        return Optional.of(task);
    }

    /**
     * Task를 삭제 합니다.
     * @param taskId Task 아이디
     */
    public void delete(final Long taskId) {
        taskRepository.deleteById(taskId);
    }

    /**
     * Task를 대체합니다.
     * @param task 변경 대상 Task
     * @param taskEditDto 대체할 Task 데이터
     */
    public void replaceTask(final Task task, final TaskEditDto taskEditDto) {
        task.setTitle(taskEditDto.getTitle());
    }

    /**
     * Task를 부분적으로 수정합니다.
     * @param task 변경 대상 Task
     * @param taskEditDto 수정할 Task 데이터
     */
    public void updateTask(final Task task, final TaskEditDto taskEditDto) {
        if (StringUtils.hasText(taskEditDto.getTitle())) {
            task.setTitle(taskEditDto.getTitle());
        }
    }
}
