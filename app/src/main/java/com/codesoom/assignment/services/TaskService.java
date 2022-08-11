package com.codesoom.assignment.services;

import com.codesoom.assignment.mappers.TaskMapper;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TaskService implements TaskServiceInterface{
    private Map<Long , Task> tasks = new ConcurrentHashMap<>();
    private TaskMapper mapper;
    private IdGenerator gen;

    public TaskService(TaskMapper mapper , IdGenerator gen){
        this.mapper = mapper;
        this.gen = gen;
    }

    @Override
    public List<Task> getAllTask(){
        return new ArrayList<>(tasks.values());
    }

    @Override
    public Task getTaskById(Long id) {
        return tasks.get(id);
    }

    @Override
    public void createTask(TaskDTO taskDTO){
        Long nextId = (Long) gen.generate();
        tasks.put(nextId , mapper.toNewTask(nextId , taskDTO));
    }

    @Override
    public Task updateTask(Long id, TaskDTO taskDTO) {
        return tasks.replace(id , mapper.toNewTask(id , taskDTO));
    }

    @Override
    public Task deleteTask(Long id) {
        return tasks.remove(id);
    }
}
