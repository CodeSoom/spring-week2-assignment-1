package com.codesoom.assignment.domain;

import java.util.List;

public interface TaskService {
    Task registerTodo(TaskCommand.RegisterTodoRequest request);
    Task modifyTodoTitle(TaskCommand.ModifyTodoTitleRequest request);
    List<Task> completeTask(TaskCommand.CompleteTodoRequest request);
    List<Task> taskList();
}
