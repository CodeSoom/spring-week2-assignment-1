package com.codesoom.assignment.service;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.exception.DuplicateException;
import com.codesoom.assignment.exception.NotEnoughException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class TodoService {
    private final IdService idService = new IdService();

    public final List<Task> tasks = new ArrayList<>();


    public List<Task> readAllTask() {
        return tasks;
    }

    public Task createTask(Task task) {
//        blankDuplicateExceptionHandling(task);
        task.changeId(idService.generateId());
        tasks.add(task);
        return task;
    }


    public void deleteOneTask(Long id) {
        tasks.remove(findById(id));
    }

    public Task updateTask(Task task, Long id) {
        tasks.get(findById(id)).changeTitle(task.getTitle());
        return task;
    }

//    private void blankDuplicateExceptionHandling(Task task) {
//        if (task.getTitle().isBlank()) {
//            throw new NotEnoughException("Please Input task text");
//        }
//
//        for (Task taskList : tasks) {
//            if (taskList.getTitle().equals(task.getTitle())) {
//                throw new DuplicateException("같은 할 일이 있습니다.");
//            }
//        }
//    }


    private int findById(Long id) {
        return IntStream.range(0, tasks.size())
                .filter(i -> tasks.get(i).getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

}


