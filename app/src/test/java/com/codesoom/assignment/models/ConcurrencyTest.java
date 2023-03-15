package com.codesoom.assignment.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@DisplayName("멀티쓰레드환경에서 9만개의 객체를 생성할 때")
class ConcurrencyTest {

    class ThreadTest implements Callable<Integer> {
        public final TaskList taskList;
        public final TaskListLegacy taskListLegacy;

        public ThreadTest(TaskList taskList, TaskListLegacy taskListLegacy) {
            this.taskList = taskList;
            this.taskListLegacy = taskListLegacy;
        }

        @Override
        public Integer call() {
            Task task = new Task();
            taskListLegacy.add(task);
            taskList.add(task);
            return Integer.parseInt("1");
        }
    }

    @Test
    @DisplayName("스레드 세이프는 90만 논스레드세이프는 값이 일치하지 않다.")
    public void ConcurrencyTest() throws InterruptedException {
        //given
        TaskList taskList = new TaskList();
        TaskListLegacy taskListLegacy = new TaskListLegacy();
        List<ThreadTest> threadList = createThreadList(taskList, taskListLegacy);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //when
        for (int i = 0; i < 300000; i++) {
            executorService.invokeAll(threadList);
        }

        int intValue = taskListLegacy.getId();
        int atomicInteger = taskList.getId();

        Assertions.assertAll(
                () -> Assertions.assertEquals(atomicInteger, 900000, "아이디값이 90만개 일치"),
                () -> Assertions.assertNotEquals(intValue, 900000, "아이디값이 90만개 불일치"),
                () -> Assertions.assertEquals(taskList.getItems().size(), 900000, "리스트 사이즈가 90만개 일치"),
                () -> Assertions.assertNotEquals(taskListLegacy.getItems().size(), 900000, "리스트 사이즈가 90만개 불일치"));
    }

    public List<ThreadTest> createThreadList(TaskList taskList, TaskListLegacy taskListLegacy) {
        ThreadTest threadTest1 = new ThreadTest(taskList, taskListLegacy);
        ThreadTest threadTest2 = new ThreadTest(taskList, taskListLegacy);
        ThreadTest threadTest3 = new ThreadTest(taskList, taskListLegacy);

        return List.of(threadTest1, threadTest2, threadTest3);
    }
}
