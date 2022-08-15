package com.codesoom.assignment.mappers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskDTO;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task toNewTask(Long taskId , TaskDTO taskDTO){
        return new Task(taskId , taskDTO.getTitle());
    }
}
