package com.codesoom.assignment.services;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.domains.TaskList;
import com.codesoom.assignment.enums.ExceptionMessages;
import com.codesoom.assignment.networks.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskList taskList;

    public TaskService() {
        this.taskList = TaskList.getTaskList();
    }

    public BaseResponse<List<Task>> readTasks() {
        return new BaseResponse<>(HttpStatus.OK, taskList.getTasks());
    }

    public BaseResponse<Task> readTask(Long taskId) {
        Task selected = taskList.getTask(taskId);

        throwErrorIfTaskIsNull(selected);

        return new BaseResponse<>(HttpStatus.OK, selected);
    }


    public BaseResponse<Task> addTask(Task newTask) {
        return new BaseResponse<>(HttpStatus.CREATED, taskList.addTask(newTask));
    }


    public BaseResponse<Task> editTask(Long taskId, Task task) {
        Task selected = taskList.getTask(taskId);

        throwErrorIfTaskIsNull(selected);

        return new BaseResponse<>(HttpStatus.OK, selected.editTaskTitle(task.getTitle()));
    }

    public BaseResponse deleteTask(Long taskId) {
        Task selected = taskList.getTask(taskId);

        throwErrorIfTaskIsNull(selected);

        taskList.deleteTask(taskId);

        return new BaseResponse<>(HttpStatus.NO_CONTENT);
    }

    public void throwErrorIfTaskIsNull(Task task) {
        if (task == null) {
            throw new RuntimeException(ExceptionMessages.TASK_NOT_FOUND.getMessage());
        }
    }

}
