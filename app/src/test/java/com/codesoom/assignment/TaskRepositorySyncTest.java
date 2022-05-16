package com.codesoom.assignment;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.Title;
import com.codesoom.assignment.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskRepositorySyncTest {

    private static final int THREAD_COUNT = 1000;

    @Test
    void save() throws InterruptedException {
        List<Thread> threadList = new ArrayList<>();
        TaskRepository taskRepository = new TaskRepository();

        for (int i = 0; i < THREAD_COUNT; i++) {
            threadList.add(new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                taskRepository.save(new Title("TITLE"));
            }));
        }

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for (Thread r : threadList) r.start();
        for (Thread r : threadList) r.join();

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());

        List<Task> tasks = taskRepository.findAll();
        assertEquals(tasks.size(), THREAD_COUNT);
    }
}
