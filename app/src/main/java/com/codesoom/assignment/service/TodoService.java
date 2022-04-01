package com.codesoom.assignment.service;

import com.codesoom.assignment.common.exception.TaskNotFoundException;
import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TodoRepository;

import java.util.List;

/**
 * {@link Task}
 * Controller에서 요청들을 받아서 Task에 대한 저장, 수정, 삭제, 조회 등의 작업을 수행한다.
 */
public class TodoService {

    private TodoRepository todoRepository = new TodoRepository();

    public List<Task> findAllTasks() {
        return todoRepository.findAllTasks();
    }


    public Task findTaskById(Long id) {
        return todoRepository.findTaskById(id)
                .orElseThrow(TaskNotFoundException::new);
    }


    public Task saveTask(TaskDto taskDto) {
        Task task = taskDto.toModel();
        return todoRepository.save(task);
    }

    public Task updateTask(Long id, TaskDto requestTaskInfo) {

        Task task = this.findTaskById(id);
        task.setTitle(requestTaskInfo.getTitle());
        return task;
    }

    public void deleteTask(Long id) {
        Task task = this.findTaskById(id);
        todoRepository.deleteTask(task);
    }

}

