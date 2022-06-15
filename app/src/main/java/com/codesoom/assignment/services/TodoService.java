package com.codesoom.assignment.services;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Service;
import repository.TodoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository = new TodoRepository();

    public Task create(Task task){
        todoRepository.save(task);
        return task;
    }

    public List<Task> findTasks() {
        return todoRepository.findAll();
    }

    public Task findTask(Long id) {
        Optional<Task> foundTask = todoRepository.findById(id);
        if(foundTask.isEmpty()){
            //Todo 404
        }
        return foundTask.get();
    }

    public Task updateTask(Task task) {
        Optional<Task> foundTask = todoRepository.findById(task.getId());
        if(foundTask.isEmpty()){
            //Todo 404
        }
        return todoRepository.update(task);
    }

    public Task deleteTask(Long id) {
        Optional<Task> foundTask = todoRepository.findById(id);
        if(foundTask.isEmpty()){
            //Todo 404
        }
        todoRepository.deleteById(foundTask.get().getId());
        return foundTask.get();
    }
}
