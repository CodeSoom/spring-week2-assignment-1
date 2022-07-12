package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TaskRepositoryTest {
    TaskRepository taskRepository = new TaskRepository() {
        private Long id = 0L;
        private final Map<Long, Task> taskMap = new HashMap<>();

        @Override
        public Task get(Long taskId) {
            return null;
        }

        @Override
        public Task add(String title) {
            return null;
        }

        @Override
        public void remove(Long taskId) {

        }
    };

    @Test
    @DisplayName("taskId를 받았지만 같은 id를 가진 Task가 존재하지 않을 때, get 메서드가 null을 리턴한다.")
    void getTestWhenNotFoundTaskId() {
        assertNull(taskRepository.get(0L));
    }

    @Test
    @DisplayName("title을 입력 받았을 때, add 메서드가 title, id을 가진 Task를 생성하고 리턴한다.")
    void addTestWhenInputTitle() {
        assertEquals(new Task(0L, "BJP"), taskRepository.add("BJP"));
    }

    @Test
    @DisplayName("입력 받은 taskId와 같은 id를 가진 Task가 존재하면, get 메서드가 해당 Task를 리턴한다.")
    void getTestWhenFoundTaskId() {
        taskRepository.add("BJP");

        assertEquals(new Task(0L, "BJP"), taskRepository.get(0L));
    }
    @Test
    @DisplayName("입력 받은 taskId와 같은 id를 가진 Task가 있을 때, remove 메서드가 해당 Task를 제거한다.")
    void removeTestWhenInputIdEqualTaskId() {
        taskRepository.add("BJP");
        taskRepository.remove(0L);
        assertNull(taskRepository.get(0L));
    }
}
