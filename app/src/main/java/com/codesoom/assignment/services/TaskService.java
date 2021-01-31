package com.codesoom.assignment.services;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repositories.TaskRepository;
import com.codesoom.assignment.utils.IdGenerator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 할 일들과 관련된 비즈니스 로직을 담당합니다.
 *
 * @see TaskRepository
 * @see IdGenerator
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final IdGenerator idGenerator;

    public TaskService(TaskRepository taskRepository, IdGenerator idGenerator) {
        this.taskRepository = taskRepository;
        this.idGenerator = idGenerator;
    }

    /**
     * 모든 할 일을 리턴합니다.
     */
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    /**
     * 주어진 할 일을 저장한 뒤, 저장된 할 일을 리턴합니다.
     *
     * @param task 저장 하고자 하는 할 일
     * @return 저장 된 할 일
     */
    public Task addTask(Task task) {
        Long newId = idGenerator.generateId();
        task.setId(newId);

        return taskRepository.save(task);
    }

    /**
     * 주어진 id에 해당하는 할 일을 찾아 리턴하거나 찾지 못한다면 TaskNotFoundException 예외가 발생합니다.
     *
     * @param id 찾고자 하는 할 일의 id
     * @return 주어진 id에 해당하는 할 일
     * @throws TaskNotFoundException 주어진 id에 해당하는 할 일을 찾지 못했을 때 발생하는 예외
     */
    public Task getTask(Long id) {
        Optional<Task> task = taskRepository.findOne(id);

        return task.orElseThrow(() ->
                new TaskNotFoundException("존재하지 않는 task id가 주어졌으므로 task를 찾을 수 없습니다. 문제의 id = " + id));
    }

    /**
     * 주어진 id에 해당하는 할 일을 찾아 수정하거나 찾지 못한다면 TaskNotFoundException 예외가 발생합니다.
     *
     * @param id 수정하고자 하는 할 일의 id
     * @return 수정된 할 일
     * @throws TaskNotFoundException 주어진 id에 해당하는 할 일을 찾지 못했을 때 발생하는 예외
     */
    public Task updateTask(Long id, Task newTask) {
        Task task = taskRepository.findOne(id).orElseThrow(() ->
                new TaskNotFoundException("존재하지 않는 task id가 주어졌으므로 task를 수정할 수 없습니다. 문제의 id = " + id));

        task.update(newTask);

        return taskRepository.save(task);
    }

    /**
     * 주어진 id에 해당하는 할 일을 찾아 삭제하거나 찾지 못한다면 TaskNotFoundException 예외가 발생합니다.
     *
     * @param id 삭제하고자 하는 할 일의 id
     * @throws TaskNotFoundException 주어진 id에 해당하는 할 일을 찾지 못했을 때 발생하는 예외
     */
    public void deleteTask(Long id) {
        if (!isExist(id)) {
            throw new TaskNotFoundException("존재하지 않는 task id가 주어졌으므로 task를 삭제할 수 없습니다. 문제의 id = " + id);
        }

        taskRepository.delete(id);
    }

    /**
     * 주어진 id에 해당하는 할 일이 존재한다면 true, 존재하지 않는다면 false를 리턴합니다.
     *
     * @param id 찾고자 하는 할 일의 id
     */
    private boolean isExist(Long id) {
        return taskRepository.findOne(id).isPresent();
    }

}
