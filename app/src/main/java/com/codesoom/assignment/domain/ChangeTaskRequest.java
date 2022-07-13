package com.codesoom.assignment.domain;

/**
 * 사용자가 입력한 작업을 변경할 수 있는 데이터를 가지고 있습니다.
 */
public class ChangeTaskRequest {
    private Long taskId;
    private String title;

    public Long getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }
}
