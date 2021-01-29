package com.codesoom.assignment.task;

import com.codesoom.assignment.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {

    public List<Task> tasks = new ArrayList<>();
    public Long id = 1L;

    public Long nextId(){
        return id++;
    }

    public Task findTaskById(Long id) throws ResourceNotFoundException {
        for (Task task : tasks){
            if (task.getId() == id)
                return task;
        }
        throw new ResourceNotFoundException("no task id : " + id);
    }

}
