package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TaskRepositoryImpl implements TaskRepository{
    private final ConcurrentHashMap<Long, Task> tasks = new ConcurrentHashMap<>();

    public List<Task> findAll(){
        return new ArrayList<>(tasks.values());
    }

    public ResponseEntity<Task> readTask(Long id) {
        return tasks.containsKey(id)?
                new ResponseEntity<>(tasks.get(id), HttpStatus.OK) : new ResponseEntity<>(new Task(), HttpStatus.NOT_FOUND);
    }

    public Task createTask(Task task) {
        Task newTask = new Task(Generate.id(), task.getTitle());
        tasks.put(newTask.getId(), newTask);
        return newTask;
    }

    public ResponseEntity<Task> updateTask(Long id, Task task){
       if(tasks.containsKey(id)){
           Task updateTask = tasks.get(id).newTitle(task.getTitle());
           tasks.put(id,updateTask);
           return new ResponseEntity<>(updateTask, HttpStatus.OK);
       }
        return new ResponseEntity<>(new Task(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Task> deleteTask(Long id){
        if(tasks.containsKey(id)){
            tasks.remove(id);
            return new ResponseEntity<>(tasks.get(id), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new Task(), HttpStatus.NOT_FOUND);
    }


}
