package com.codesoom.assignment.Repository;

import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TaskRepository {
    private final HashMap<Long, Task> tasks = new HashMap<>();
    private final TaskIDRepository taskIDManager;
    public TaskRepository(TaskIDRepository taskIDManager){
        this.taskIDManager = taskIDManager;
    }

    public List<Task> getAllTask(){
        return new ArrayList<>(tasks.values());
    }

    public Task getKeyTask(Long id){
        if(tasks.containsKey(id)){
            return tasks.get(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid URL");
    }

    public Task addTask(Task task){
        task.setId(taskIDManager.generateID());
//        task.setId(generateID());
        tasks.put(task.getId(), task);
        return task;
    }

    public Task modifyTask(Task task){

        if(tasks.containsKey(task.getId())) {
            Task newTask = tasks.get(task.getId());
            newTask.setTitle(task.getTitle());
            tasks.replace(task.getId(), newTask);
            return newTask;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid URL");
    }

    public void removeTask(Long id){
        if(tasks.containsKey(id)){
            tasks.remove(id);
            return ;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid URL");
    }
}

