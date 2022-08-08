package com.codesoom.assignment.services;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    private Map<Long , Task> tasks = new ConcurrentHashMap<>();
    private AtomicLong taskId = new AtomicLong(1L);

    public List<Task> getAllTask(){
        return new ArrayList<>(tasks.values());
    }

    public Task getTaskById(long id){
        return tasks.get(id);
    }

    public void createTask(Task newTask){
        long nextId = nextTaskId();
        newTask.setId(nextId);
        tasks.put(nextId , newTask);
    }

    public void updateTask(long id , Task newTask){
        newTask.setId(id);
        tasks.replace(id , newTask);
    }

    public void deleteTask(long id){
        tasks.remove(id);
    }

    private Long nextTaskId(){
        return taskId.getAndIncrement();
    }
}
