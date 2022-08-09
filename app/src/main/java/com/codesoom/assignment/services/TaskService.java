package com.codesoom.assignment.services;

import com.codesoom.assignment.mappers.TaskMapper;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TaskService implements TaskServiceInterface{
    private Map<Long , Task> tasks = new ConcurrentHashMap<>();
    private TaskMapper mapper;
    private TaskIdGenerator gen;

    public TaskService(TaskMapper mapper , TaskIdGenerator gen){
        this.mapper = mapper;
        this.gen = gen;
    }

    @Override
    public List<Task> getAllTask(){
        return new ArrayList<>(tasks.values());
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    @Override
    public void createTask(TaskDTO taskDTO){
        Long nextId = gen.getNextId();
        tasks.put(nextId , mapper.toNewTask(nextId , taskDTO));
    }

    @Override
    public Optional<Task> updateTask(Long id, TaskDTO taskDTO) {
        return Optional.ofNullable(tasks.replace(id , mapper.toNewTask(id , taskDTO)));
    }

    @Override
    public Optional<Task> deleteTask(Long id) {
        return Optional.ofNullable(tasks.remove(id));
    }
}
