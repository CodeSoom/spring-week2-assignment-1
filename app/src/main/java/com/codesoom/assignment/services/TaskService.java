package com.codesoom.assignment.services;

import com.codesoom.assignment.domains.Task;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks(){
        return tasks;
    }
    public Task createTask(Task requestTask){
        requestTask.setId(getNewId());
        tasks.add(requestTask);
        sortTasks();
        return requestTask;
    }

    public Task updateTask(int id, Task requestTask) {
        Task task = getTask(id);
        if (task != null) {
            task.setTitle(requestTask.getTitle());
        }
        return task;
    }

    public void deleteTask(int id) {
        tasks.removeIf(obj -> obj.getId() == id);
        sortTasks();
    }

    public Task getTask(int id){
        return tasks.stream()
                    .filter(obj -> obj.getId() == id)
                    .findFirst()
                    .orElse(null);
    }

    public int getNewId(){
        int newId = 1;
        if(tasks.size() != 0){
            newId = tasks.get(tasks.size()-1).getId()+1;
        }
        return newId;
    }

    public void sortTasks(){
        tasks.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));
    }

}
