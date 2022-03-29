package com.codesoom.assignment.services;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.networks.BaseResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    void init() {
        taskService = new TaskService();
    }

    @Test
    @DisplayName("할일 목록 조회 > 할일이 없을 때 빈목록 반환")
    void readEmptyTasks() {
        BaseResponse<List<Task>> res = taskService.readTasks();
        List<Task> tasks = res.getBody();

        assertTrue(tasks.isEmpty());
    }

    @Test
    @DisplayName("할일 목록 조회 > 할일이 있을 때 할일 리스트 반환")
    void readTasks() {
        //TODO
    }

    @Test
    @DisplayName("할일 단건 조회 > path 의 id에 해당하는 task 내용 응답")
    void readSingleTask() {

    }

    @Test
    @DisplayName("할일 단건 조회 > path 의 id가 0 이하일 때, 오류 응답")
    void readSingleTask_throwErrorIfNotValidId() {

    }

    @Test
    @DisplayName("할일 생성 > 할일 리스트에 신규항목 추가 > 목록에서 확인")
    void addNewTask() {

    }

    @Test
    @DisplayName("할일 생성 > task title 형식이 안맞을 때 오류 응답 : 문자가 아니거나 비어있으면 안됨")
    void addNewTask_throwErrorIfNotValidTitle() {

    }

    @Test
    @DisplayName("할일 제목 수정 > path id 에 맞는 할일 조회 > 제목 수정")
    void editTaskTitle() {

    }

    @Test
    @DisplayName("할일 제목 수정 > path id 에 맞는 할일을 찾을 수 없을 때 오류 응답")
    void editTaskTitle_throwErrorIfNoMatchedTask() {

    }

    @Test
    @DisplayName("할일 제목 수정 > 수정하려는 제목값이 적절하지 않을 때 오류 응답")
    void editTaskTitle_throwErrorIfNotValidTitle() {

    }

    @Test
    @DisplayName("할일 삭제 > id에 맞는 할일 조회 후 삭제")
    void deleteSingleTask() {

    }

    @Test
    @DisplayName("할일 삭제 > path id에 맞는 할일을 찾을 수 없을 때 오류 응답")
    void deleteSingleTask_throwErrorIfNoMatchedTask() {

    }


}
