package com.codesoom.todo.services;

import com.codesoom.todo.controllers.TaskNotFoundException;
import com.codesoom.todo.domain.Task;
import com.codesoom.todo.repository.TaskRepository;

import java.util.List;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public Task addTask(Task task) {
        taskRepository.add(task);
        return task;
    }

    /**
     * id 를 받아서, 해당 id 를 가지는 태스크를 리턴한다.
     * @param id 보여줄 태스크의 id
     * @return 인자로 받은 id 를 가지고 있는 태스크
     * @exception TaskNotFoundException 인자로 받은 taskId 를 가지고 있는 태스크가 없다면 던진다.
     */
    public Task showTask(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    /**
     * 현재 등록된 모든 태스크를 리턴한다
     * @return 태스크의 리스트
     */
    public List<Task> showTasks() {
        return taskRepository.findAll();
    }

    /**
     * 새로 수정할 태스크를 받아, 같은 id 를 가지는 태스크가 존재한다면, 수정한다.
     * 존재하지 않는다면, 에러를 던진다.
     * @param newTask 새로 수정할 태스크
     * @return 수정한 태스크
     * @exception TaskNotFoundException 인자로 받은 taskId 를 가지고 있는 태스크가 없다면 던진다.
     */
    public Task editTaskTitle(Task newTask) {
        taskRepository.findById(newTask.getId()).orElseThrow(() -> new TaskNotFoundException(newTask));
        return taskRepository.edit(newTask);
    }

    /**
     * 삭제할 태스크의 id 를 받아서, 해당 id 가 존재한다면, 삭제한다.
     * 존재하지 않는다면, 에러를 던진다.
     * @param id 수정할 태스크의 id
     * @exception TaskNotFoundException 인자로 받은 taskId 를 가지고 있는 태스크가 없다면 던진다.
     */
    public void removeTask(Long id) {
        taskRepository.findById(id).flatMap(taskRepository::delete).orElseThrow(() -> new TaskNotFoundException(id));
    }
}
