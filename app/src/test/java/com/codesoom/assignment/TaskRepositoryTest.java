package com.codesoom.assignment;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.junit.jupiter.api.*;


public class TaskRepositoryTest {

    TaskRepository taskRepository = new TaskRepository();

    @AfterEach
    public void afterEach() {
        taskRepository.clearTaskStore();
    }

    @Nested
    @DisplayName("size() 메서드는")
    class Describe_size {
        @Nested
        @DisplayName("할 일을 등록하면")
        class Context_with_task {
            String title1 = "Listening";
            String title2 = "Playing";
            Task task1 = new Task(0L, title1);
            Task task2 = new Task(1L, title2);

            @Test
            @DisplayName("할 일이 저장된 목록 개수만큼 숫자를 리턴한다.")
            void It_return_number_of_task() {
                taskRepository.createTask(0L, task1);
                taskRepository.createTask(1L, task2);
                Assertions.assertEquals(2, taskRepository.getTasks().size());
            }
        }
    }

}
