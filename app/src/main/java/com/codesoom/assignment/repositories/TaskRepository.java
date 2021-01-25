package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TaskRepository {

    private Map<Long, Task> taskMap = new ConcurrentHashMap<>();

    public List<Task> findAll() {
        return new ArrayList<>(taskMap.values());
    }

}
