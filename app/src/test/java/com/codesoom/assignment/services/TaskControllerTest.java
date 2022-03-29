package com.codesoom.assignment.services;

import com.codesoom.assignment.controllers.TaskController;
import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.networks.BaseResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TaskControllerTest {

    private TaskController taskController;

    @BeforeEach
    void init() {
        TaskService taskService = new TaskService();
        taskController = new TaskController(taskService);
    }

    @Test
    @DisplayName("할일 목록 조회 > 할일이 없을 때 빈목록 반환")
    void readEmptyTasks() {
        BaseResponse<List<Task>> res = taskController.readTasks();
        List<Task> tasks = res.getBody();

        assertTrue(tasks.isEmpty());
    }

    @Test
    @DisplayName("할일 목록 조회 > 할일이 있을 때 할일 리스트 반환")
    void readTasks() {
        //given
        taskController.addTask(new Task(1L, "코드숨 2주차 과제하기"));

        //when
        BaseResponse<List<Task>> response = taskController.readTasks();
        List<Task> tasks = response.getBody();

        //then
        assertEquals(tasks.size(), 1L);
        assertEquals(tasks.get(0).getTitle(), "코드숨 2주차 과제하기");
    }

    @Test
    @DisplayName("할일 단건 조회 > path 의 id에 해당하는 task 내용 응답")
    void readSingleTask() {
        //given
        taskController.addTask(new Task(1L, "코드숨 2주차 과제하기"));

        //when
        BaseResponse<Task> response = taskController.readTask(1L);
        Task task = response.getBody();

        //then
        assertEquals(task.getId(), 1L);
        assertEquals(task.getTitle(), "코드숨 2주차 과제하기");
    }

    @Test
    @DisplayName("할일 단건 조회 > path 의 id가 0 이하일 때, 오류 응답")
    void readSingleTask_throwErrorIfNotValidId() {
        assertThrows(Exception.class, () -> {
            taskController.readTask(-1L);
        });
    }

    @Test
    @DisplayName("할일 생성 > 할일 리스트에 신규항목 추가 > 목록에서 확인")
    void addNewTask() {
        //given
        BaseResponse<List<Task>> readTaskResponse = taskController.readTasks();
        List<Task> tasks = readTaskResponse.getBody();

        //when
        BaseResponse<Task> addTaskResponse = taskController.addTask(new Task("코드숨 과제하기"));
        Task addedTask = addTaskResponse.getBody();

        //then
        assertEquals(tasks.get(0), addedTask);
    }

    @Test
    @DisplayName("할일 생성 > task title 형식이 안맞을 때 오류 응답 : 문자가 아니거나 비어있으면 안됨")
    void addNewTask_throwErrorIfNotValidTitle() {
        assertThrows(Exception.class, () -> {
            taskController.addTask(new Task());
        });
    }

    @Test
    @DisplayName("할일 제목 수정 > path id 에 맞는 할일 조회 > 제목 수정")
    void editTaskTitle() {
        //given
        BaseResponse<List<Task>> readTaskResponse = taskController.readTasks();
        List<Task> tasks = readTaskResponse.getBody();

        addNewTask();

        //when
        BaseResponse<Task> response = taskController.editTask(1L, new Task("코드숨 과제 리펙토링하기"));
        Task editedTask = response.getBody();

        //then
        assertEquals(tasks.get(0).getTitle(), editedTask.getTitle());
    }

    @Test
    @DisplayName("할일 제목 수정 > path id 에 맞는 할일을 찾을 수 없을 때 오류 응답")
    void editTaskTitle_throwErrorIfNoMatchedTask() {
        addNewTask();

        assertThrows(Exception.class, () -> {
            taskController.editTask(1L, new Task("코드숨 과제 리펙토링하기"));
        });
    }

    @Test
    @DisplayName("할일 제목 수정 > 수정하려는 제목값이 적절하지 않을 때 오류 응답")
    void editTaskTitle_throwErrorIfNotValidTitle() {
        addNewTask();

        assertThrows(Exception.class, () -> {
            taskController.editTask(1L, new Task(""));
        });
    }

    @Test
    @DisplayName("할일 삭제 > id에 맞는 할일 조회 후 삭제")
    void deleteSingleTask() {
        //given
        addNewTask();

        //when
        BaseResponse response = taskController.deleteTask(1L);
        int statusCode = response.getStatusCode();

        //then
        assertEquals(statusCode, 204);
    }

    @Test
    @DisplayName("할일 삭제 > path id에 맞는 할일을 찾을 수 없을 때 오류 응답")
    void deleteSingleTask_throwErrorIfNoMatchedTask() {
        //given
        addNewTask();

        assertThrows(Exception.class, () -> {
            taskController.deleteTask(1L);
        });
    }

}
