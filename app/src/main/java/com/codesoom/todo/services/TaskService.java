package com.codesoom.todo.services;

import com.codesoom.todo.domain.Task;
import com.codesoom.todo.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

public class TaskService {
    private final TaskRepository taskRepository = new TaskRepository();


    public Task addTask(Task task) {
        taskRepository.add(task);
        return task;
    }

    public Optional<Task> showTask(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> showTasks() {
        return taskRepository.findAll();
    }

    // TODO: Javadoc
    public Optional<Task> editTaskTitle(Task newTask) {
        return taskRepository.edit(newTask);
    }

    // Q: delete 의 경우는 키가 없을 수도 있기 리포지토리 단에서 Optional 을 리턴하게 됩니다. 만약 이렇게 하게 된다면 서비스에서 Nested Optional 을 사용하게 되는 문제가 발생하는데 어떻게 해결하는게 좋을까요?
    // TODO: Javadoc
    public Optional<Task> removeTask(Long id) {
        return taskRepository.findById(id).flatMap(taskRepository::delete);
    }
}
