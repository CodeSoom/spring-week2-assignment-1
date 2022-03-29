package com.codesoom.assignment.services;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.domains.TaskList;
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
        Task selectedTask = taskList.getTask(taskId);

        if (selectedTask == null) {
            return new BaseResponse<>(HttpStatus.NOT_FOUND);
        }

        return new BaseResponse<>(HttpStatus.OK, selectedTask);
    }


    public BaseResponse<Task> addTask(Task newTask) {
        return new BaseResponse<>(HttpStatus.CREATED, taskList.addTask(newTask));
    }


    public BaseResponse<Task> editTask(Long taskId, Task task) {
        Task selected = taskList.getTask(taskId);

        if (selected == null) {
            return new BaseResponse<>(HttpStatus.NOT_FOUND);
        }

        return new BaseResponse<>(HttpStatus.OK, selected.editTaskTitle(task.getTitle()));
    }

    public BaseResponse deleteTask(Long taskId) {
        Task selected = taskList.getTask(taskId);

        if (selected == null) {
            return new BaseResponse<>(HttpStatus.NOT_FOUND);
        }

        taskList.deleteTask(taskId);

        return new BaseResponse<>(HttpStatus.NO_CONTENT);
    }

}
