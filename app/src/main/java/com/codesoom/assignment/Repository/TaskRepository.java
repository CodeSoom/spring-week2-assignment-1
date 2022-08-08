package com.codesoom.assignment.Repository;

import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class TaskRepository {
    private final HashMap<Long, Task> tasks = new HashMap<>();
    private final TaskIDRepository taskIDManager;
    public TaskRepository(TaskIDRepository taskIDManager){
        this.taskIDManager = taskIDManager;
    }

    /**
     * 해당 함수는 존재하는 사용자가 입력한 순서대로
     * 저장된 모든 Task를 저장한 List를 반환함
     *
     * @return List<Task>
     */
    public List<Task> getAllTask(){
        List<Task> allTasks = new ArrayList<>(tasks.values());
        Collections.sort(allTasks, new TaskComparator());
        return allTasks;
    }

    public Task getKeyTask(Long id){
        if(tasks.containsKey(id)){
            return tasks.get(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid URL");
    }

    public Task addTask(Task task){
        task.setId(taskIDManager.generateID());
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

    /**
     * Task의 ID 오름차순으로 정렬
     *
     */
    private class TaskComparator implements Comparator<Task> {
        @Override
        public int compare(Task t1, Task t2) {
            if(t1.getId() < t2.getId()){
                return -1;
            }
            else if(t1.getId() > t2.getId()){
                return 1;
            }
            return 0;
        }
    }
}

