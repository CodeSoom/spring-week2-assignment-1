package com.codesoom.assignment.services;

import com.codesoom.assignment.mappers.TaskMapper;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskDTO;
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
    private TaskMapper mapper;

    public TaskService(TaskMapper mapper){
        this.mapper = mapper;
    }

    public List<Task> getAllTask(){
        return new ArrayList<>(tasks.values());
    }

    public Task getTaskById(long id){
        return tasks.get(id);
    }

    public void createTask(TaskDTO taskDTO){
        long nextId = nextTaskId();
        tasks.put(nextId , mapper.toNewTask(nextId , taskDTO));
    }

    public void updateTask(long id , TaskDTO taskDTO){
        tasks.replace(id , mapper.toNewTask(id , taskDTO));
    }

    public void deleteTask(long id){
        tasks.remove(id);
    }

    private Long nextTaskId(){
        return taskId.getAndIncrement();
    }
}
