package com.codesoom.assignment.service;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.repository.TaskMapRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Service
public class TaskServiceTest {
    private final TaskService taskService = new TaskService(new TaskMapRepository());

    @Test
    @DisplayName("Task를 저장하고 있는 저장소가 주어지고 입력 받은 taskId와 같은 id를 가진 Task가 존재할 때, 해당 Task의 title을 입력 받은 title로 변경하고 리턴한다.")
    void putTestWhenTaskWithTheSameIdAsTheTaskIdEnteredExists() {
        taskService.createTask("BJP1");
        taskService.createTask("BJP2");

        Task modifiedTask = taskService.modifyTask(1L, "변경됨");

        assertEquals(new Task(1L, "변경됨"), modifiedTask);
    }
}
